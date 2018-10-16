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
import model.Anuncio;
import model.Pessoa;
import spark.Request;
import spark.Response;

/**
 *
 * @author iapereira
 */
public class ListAnuncioCommand extends Command {

    public ListAnuncioCommand(Request request, Response response) {
        super(request, response);
        map.put("id_pessoa", Integer.parseInt(request.params(":id")));
        int idPessoa = Integer.parseInt(request.params(":id"));
        
        List<Anuncio> anuncios;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Pessoa p = entityManager.find(Pessoa.class, idPessoa);
        String consulta = "select a from Anuncio a where a.pessoa = :id";
        TypedQuery<Anuncio> query = entityManager.createQuery(consulta, Anuncio.class);
        query.setParameter("id", p);
        anuncios = query.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        if (anuncios.size() > 0) {
            map.put("anuncios", anuncios);
        }
    }
}
