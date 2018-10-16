package command;


import static init.Main.entityManagerFactory;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import model.Pessoa;
import spark.Request;
import spark.Response;

/**
 * @author iapereira
 */
public class DeleteMultipleCommand extends Command {

    public DeleteMultipleCommand(Request request, Response response) throws SQLException {
        super(request, response);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        if (request.queryMap("id").hasValue()) {
            String vetId[] = request.queryMap("id").values(); // pega todos os valores que vieram do html que possuem o mesmo nome (no caso id)
            if (vetId.length > 0) {
                for (String id : vetId) {
                    entityManager.getTransaction().begin();
                    Pessoa p = entityManager.find(Pessoa.class, Integer.parseInt(id));
                    entityManager.remove(p);
                    entityManager.getTransaction().commit();
                }
                response.redirect("/");
            }
        } else {
            response.redirect("/");
        }
        entityManager.close();
    }
}
