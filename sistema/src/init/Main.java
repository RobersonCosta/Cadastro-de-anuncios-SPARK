package init;

import command.DeleteMultipleCommand;
import command.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import static spark.Spark.*;
import spark.SparkBase;
import spark.TemplateViewRoute;
import spark.template.mustache.MustacheTemplateEngine;

/**
 * *
 * Classe que determina as rotas
 *
 * @author iapereira
 */
public class Main {

    public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sistemaPU");
    
    public static void main(String[] args) {

        staticFileLocation("/html"); 
        
        
        // index
        get("/", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new ListCommand(request, response).getMap(), "index.html");
            }
        }, new MustacheTemplateEngine());

        // delete
        get("/delete/:id", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new DeleteCommand(request, response), "");
            }
        }, new MustacheTemplateEngine());
        
        get("/deleteAnuncio/:id", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new DeleteAnuncioCommand(request, response), "");
            }
        }, new MustacheTemplateEngine());
        
        post("/delete_multiple", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                try {
                    return new ModelAndView(new DeleteMultipleCommand(request, response).getMap(), "message.html");
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        }, new MustacheTemplateEngine());

        // tela inserir      
        get("/screen_insert", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new Command(request, response), "screen_insert.html");
            }
        }, new MustacheTemplateEngine());

        post("/insert/", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new InsertCommand(request, response).getMap(), "message.html");
            }
        }, new MustacheTemplateEngine());
        
        get("/screen_insertAnuncio/:id_pessoa", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new ScreenInsertAnuncioCommand(request, response).getMap(), "screen_insertAnuncio.html");
            }
        }, new MustacheTemplateEngine());
        
        post("/insertAnuncio/", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                try {
                    return new ModelAndView(new InsertAnuncioCommand(request, response).getMap(), "message.html");
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        }, new MustacheTemplateEngine());

        // update
        get("/screen_update/:id", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new ScreenUpdateCommand(request, response).getMap(), "screen_update.html");
            }
        }, new MustacheTemplateEngine());

        post("/update/", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new UpdateCommand(request, response).getMap(), "message.html");
            }
        }, new MustacheTemplateEngine());
        
        post("/updateAnuncio/", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new UpdateAnuncioCommand(request, response).getMap(), "message.html");
            }
        }, new MustacheTemplateEngine());
        
        get("/listAnuncio/:id", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new ListAnuncioCommand(request, response).getMap(), "listar_anuncios.html");
            }
        }, new MustacheTemplateEngine());
        
        get("/screen_updateAnuncio/:id", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new ScreenUpdateAnuncioCommand(request, response).getMap(), "screen_updateAnuncio.html");
            }
        }, new MustacheTemplateEngine());

    }
}
