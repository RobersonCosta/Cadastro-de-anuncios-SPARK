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
public class ScreenUpdateCommand extends Command {

    public ScreenUpdateCommand(Request request, Response response) {
        super(request, response);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Pessoa pessoa = entityManager.find(Pessoa.class, Integer.parseInt(request.params(":id")));
        entityManager.getTransaction().commit();

        map.put("nome", pessoa.getNome());
        map.put("email", pessoa.getEmail());
        map.put("telefone", pessoa.getTelefone());
        map.put("id", pessoa.getId());

        entityManager.close();
    }

}
