package server;

import controllers.*;
import domain.entidades.comunidad.RolComunidad;
import domain.entidades.signin.RolUsuario;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Router {

    public static void init() {
        Server.app().get("/saluda", ctx -> {
            ctx.result("Hola "
                    + ctx.queryParam("nombre")
                    + ", " + ctx.sessionAttribute("item1")
            );
        });
        Server.app().get("/saludo-para/{nombre}", ctx -> ctx.result("Hola "
                + ctx.pathParam("nombre")
        ));

        Server.app().error(404, ctx -> {
            //Asi funciona sin imagen
            //ctx.status(404).result("Pagina no encontrada");
            //hay que agregar la ruta de la imagen de 404 en 404.hbs
            ctx.render("404.hbs");
        });

        Server.app().routes(() -> {

            get("/home", ((InicioDeSesionController)FactoryController.controller("InicioSesion"))::showHome);

            get("/incidentes/reportar", ((IncidentesController)FactoryController.controller("Incidentes"))::create);
            post("/incidentes/reportar", ((IncidentesController)FactoryController.controller("Incidentes"))::save);

            get("/incidentes/{id}", ((IncidentesController) FactoryController.controller("Incidentes"))::show);
           // post("/incidentes/{id}", ((IncidentesController) FactoryController.controller("Incidentes"))::update);

            get("/incidentes", ((IncidentesController)FactoryController.controller("Incidentes"))::index);

            get("/inicio", ((InicioDeSesionController)FactoryController.controller("InicioSesion"))::index);
            post("/inicio", ((InicioDeSesionController)FactoryController.controller("InicioSesion"))::iniciarSesion);

            get("/crear-cuenta",((InicioDeSesionController) FactoryController.controller("InicioSesion"))::vista);
            post("/crear-cuenta",((InicioDeSesionController) FactoryController.controller("InicioSesion"))::save);

            get("/olvidar-contrasenia",((InicioDeSesionController) FactoryController.controller("InicioSesion"))::olvidarContrasenia);

            post("/cerrar-incidentes",((IncidentesController) FactoryController.controller("Incidentes"))::recibirIncidentesCerrados);

            get("/rankings", ((RankingsController)FactoryController.controller("Rankings"))::show);

            get("/portalCargaDeDatos", ((EntidadesPrestadorasController) FactoryController.controller("EntidadesPrestadoras"))::index);

            post("/portalCargaDeDatos", ((EntidadesPrestadorasController) FactoryController.controller("EntidadesPrestadoras"))::save);

            //post("/portalCargaDeDatos",((OrganismosDeControlController) FactoryController.controller("OrganismosDeControl"))::save);


            get("/agregarUsuario",((MiembrosController) FactoryController.controller("miembro"))::index);

            post("/agregarUsuario",
                    ((MiembrosController) FactoryController.controller("miembro"))::save);
            // get("entidadesYOrganismos", ())

            //get("servicios", ((ServiciosController) FactoryController.controller("Servicios"))::index);
            //get("servicios/crear", ((ServiciosController) FactoryController.controller("Servicios"))::create);
            //get("servicios/{id}", ((ServiciosController) FactoryController.controller("Servicios"))::show);
            //get("servicios/{id}/editar", ((ServiciosController) FactoryController.controller("Servicios"))::edit);
           // post("servicios/{id}", ((ServiciosController) FactoryController.controller("Servicios"))::update);
            //post("servicios", ((ServiciosController) FactoryController.controller("Servicios"))::save);
            //delete("servicios/{id}", ((ServiciosController) FactoryController.controller("Servicios"))::delete);

            path("servicios/{id}/tareas", () -> {
               // get(((TareasController) FactoryController.controller("Tareas"))::index);
                //TODO
            });
        });
    }
}