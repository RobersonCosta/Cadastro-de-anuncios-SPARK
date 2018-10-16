package command;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import spark.Request;
import spark.Response;

/**
 *
 * @author iapereira
 */
public class ScreenInsertAnuncioCommand extends Command {

    public ScreenInsertAnuncioCommand(Request request, Response response) {
        super(request, response);
        map.put("id_pessoa", Integer.parseInt(request.params(":id_pessoa")));
    }
}
