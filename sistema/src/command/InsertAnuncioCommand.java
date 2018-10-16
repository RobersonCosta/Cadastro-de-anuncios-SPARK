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
import java.util.Map;
import javax.persistence.EntityManager;
import model.Anuncio;
import model.Pessoa;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;

/**
 *
 * @author iapereira
 */
public class InsertAnuncioCommand extends Command {

    public InsertAnuncioCommand(Request request, Response response) throws SQLException {
        super(request, response);       

        
        
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Pessoa p = entityManager.find(Pessoa.class, Integer.parseInt(request.queryParams("id_pessoa")));
        Anuncio anuncio = new Anuncio(0, p, request.queryParams("descricao"), request.queryParams("valor"));
        entityManager.persist(anuncio);
        entityManager.getTransaction().commit();
        entityManager.close();
        response.redirect("/listAnuncio/" + anuncio.getPessoa().getId());
    }

}
