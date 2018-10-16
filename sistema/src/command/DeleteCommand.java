/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;


import static init.Main.entityManagerFactory;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import model.Pessoa;
import spark.Request;
import spark.Response;

/**
 *
 * @author iapereira
 */
public class DeleteCommand extends Command {

    public DeleteCommand(Request request, Response response) {
        super(request, response);
        
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Pessoa p = entityManager.find(Pessoa.class, Integer.parseInt(request.params(":id")));
        entityManager.remove(p);
        entityManager.getTransaction().commit();
        entityManager.close();
        response.redirect("/");
    }

}
