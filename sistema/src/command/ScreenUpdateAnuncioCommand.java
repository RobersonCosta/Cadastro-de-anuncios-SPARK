package command;


import static init.Main.entityManagerFactory;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import model.Anuncio;
import model.Pessoa;
import spark.Request;
import spark.Response;

/**
 *
 * @author iapereira
 */
public class ScreenUpdateAnuncioCommand extends Command {

    public ScreenUpdateAnuncioCommand(Request request, Response response) {
        super(request, response);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Anuncio anuncio = entityManager.find(Anuncio.class, Integer.parseInt(request.params(":id")));
        entityManager.getTransaction().commit();

        map.put("descricao", anuncio.getDescricao());
        map.put("valor", anuncio.getValor());
        map.put("id", anuncio.getId());
        
        entityManager.close();
    }

}
