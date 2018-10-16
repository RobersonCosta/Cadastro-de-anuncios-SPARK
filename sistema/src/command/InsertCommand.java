/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import static init.Main.entityManagerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import model.Pessoa;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;

/**
 *
 * @author iapereira
 */
public class InsertCommand extends Command {

    public InsertCommand(Request request, Response response) {
        super(request, response);  
        
        Pessoa p = new Pessoa(request.queryParams("nome"), request.queryParams("email"), request.queryParams("telefone"));
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(p);
        entityManager.getTransaction().commit();
        entityManager.close();
        
        response.redirect("/");
    }    
    

}
