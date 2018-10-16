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
public class UpdateCommand extends Command {

    public UpdateCommand(Request request, Response response) {
        super(request, response);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Pessoa pessoa = entityManager.find(Pessoa.class, Integer.parseInt(request.queryParams("id")));
     
        pessoa.setNome(request.queryParams("nome"));
        pessoa.setEmail(request.queryParams("email"));
        pessoa.setTelefone(request.queryParams("telefone"));
        entityManager.getTransaction().commit();

        entityManager.close();
        response.redirect("/");

    }

}
