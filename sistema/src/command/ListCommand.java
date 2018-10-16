/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;


import static init.Main.entityManagerFactory;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Pessoa;
import spark.Request;
import spark.Response;

/**
 *
 * @author iapereira
 */
public class ListCommand extends Command {

    public ListCommand(Request request, Response response) {
        super(request, response);
        List<Pessoa> pessoas;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String consulta = "select p from Pessoa p";
        TypedQuery<Pessoa> query = entityManager.createQuery(consulta, Pessoa.class);
        pessoas = query.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        if (pessoas.size() > 0) {
            map.put("pessoas", pessoas);
        }
    }

}
