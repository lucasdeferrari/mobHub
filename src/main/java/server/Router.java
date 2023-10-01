package server;

import controllers.FactoryController;
import controllers.ServiciosController;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Router {

    public static void init() {
        Server.app().get("/", ctx -> {
            ctx.sessionAttribute("item1", "Cosa 1");
            ctx.result("Hola mundo");
        });
        Server.app().get("/saluda", ctx -> {
            ctx.result("Hola "
                    + ctx.queryParam("nombre")
                    + ", " + ctx.sessionAttribute("item1")
            );
        });
        Server.app().get("/saludo-para/{nombre}", ctx -> ctx.result("Hola "
                + ctx.pathParam("nombre")
        ));

        Server.app().routes(() -> {
            //get("/", (FactoryController.controller("InicioSesion")::show));
            get("crearUsuario", ((MiembrosController)FactoryController.controller(("Miembros")))::create);
            post("crearUsuario", ((MiembrosController)FactoryController.controller("Miembros"))::save);

            get("incidentes", ((IncidentesController)FactoryController.controller("Incidentes"))::index);

            get("incidentes/reportar", ((IncidentesController)FactoryController.controller("Incidentes"))::create);
            post("incidentes/reportar", ((IncidentesController)FactoryController.controller("Incidentes"))::save);

            get("incidentes/{id}", ((IncidentesController) FactoryController.controller("Incidentes"))::show);
            post("incidentes/{id}", ((IncidentesController) FactoryController.controller("Incidentes"))::update);
            get("rankings", ((RankingsController)FactoryController.controller("Rankings"))::show);

            get("entidadesYOrganismos", ())

            //get("servicios", ((ServiciosController) FactoryController.controller("Servicios"))::index);
            //get("servicios/crear", ((ServiciosController) FactoryController.controller("Servicios"))::create);
            //get("servicios/{id}", ((ServiciosController) FactoryController.controller("Servicios"))::show);
            //get("servicios/{id}/editar", ((ServiciosController) FactoryController.controller("Servicios"))::edit);
           // post("servicios/{id}", ((ServiciosController) FactoryController.controller("Servicios"))::update);
            //post("servicios", ((ServiciosController) FactoryController.controller("Servicios"))::save);
            //delete("servicios/{id}", ((ServiciosController) FactoryController.controller("Servicios"))::delete);

            path("servicios/{id}/tareas", () -> {
                get(((TareasController) FactoryController.controller("Tareas"))::index);
                //TODO
            });
        });
    }
}