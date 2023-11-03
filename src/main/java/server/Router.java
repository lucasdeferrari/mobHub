package server;

import componentesExternos.geoRef.entidades.ListadoDeLocalidades;
import componentesExternos.geoRef.entidades.ListadoDeMunicipios;
import componentesExternos.geoRef.interfaces.ServicioGeoRef;
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
        Server.app().before( ctx -> {
          //  String ruta = ctx.path();
         //  if (ctx.sessionAttribute("id") == null && (!"/inicio".equals(ruta) || !"/crear-cuenta".equals(ruta))) {
           //     ctx.redirect("/inicio");
         //   }
        });

        Server.app().error(404, ctx -> {
            //Asi funciona sin imagen
            //ctx.status(404).result("Pagina no encontrada");
            //hay que agregar la ruta de la imagen de 404 en 404.hbs
            ctx.render("404.hbs");
        });
        Server.app().get("/obtener-municipios", ctx -> {
            String provincia_id = ctx.queryParam("provincia");
            int provinciaId = Integer.parseInt(provincia_id);
            ServicioGeoRef servicioGeoref = ServicioGeoRef.instancia();
           ListadoDeMunicipios municipios = servicioGeoref.listadoDeMunicipiosDeProvincia(provinciaId);
            ctx.json(municipios.municipios);
        });

        Server.app().get("/obtener-localidades", ctx -> {
            String municipio_id = ctx.queryParam("municipio");
            int municipioId = Integer.parseInt(municipio_id);
            ServicioGeoRef servicioGeoref = ServicioGeoRef.instancia();
            ListadoDeLocalidades localidades = servicioGeoref.listadoDeLocalidadesDeMunicipio(municipioId);
            ctx.json(localidades.localidades);
        });
        Server.app().routes(() -> {

            get("/home", ((InicioDeSesionController) FactoryController.controller("InicioSesion"))::showHome);

            get("/incidentes/reportar", ((IncidentesController) FactoryController.controller("Incidentes"))::create);
            post("/incidentes/reportar", ((IncidentesController) FactoryController.controller("Incidentes"))::save);

            get("/incidentes/{id}", ((IncidentesController) FactoryController.controller("Incidentes"))::show);
            // post("/incidentes/{id}", ((IncidentesController) FactoryController.controller("Incidentes"))::update);

            get("/incidentes", ((IncidentesController) FactoryController.controller("Incidentes"))::index);

            get("/inicio", ((InicioDeSesionController) FactoryController.controller("InicioSesion"))::index);
            post("/inicio", ((InicioDeSesionController) FactoryController.controller("InicioSesion"))::iniciarSesion);

            get("/crear-cuenta", ((InicioDeSesionController) FactoryController.controller("InicioSesion"))::vista);
            post("/crear-cuenta", ((InicioDeSesionController) FactoryController.controller("InicioSesion"))::save);

            get("/olvidar-contrasenia", ((InicioDeSesionController) FactoryController.controller("InicioSesion"))::olvidarContrasenia);

            post("/cerrar-incidentes", ((IncidentesController) FactoryController.controller("Incidentes"))::recibirIncidentesCerrados);

            post("/sugerir-cierre-incidente/{id}", ((IncidentesController) FactoryController.controller("Incidentes"))::sugerirCierreIncidente);

            get("/rankings", ((RankingsController) FactoryController.controller("Rankings"))::show);

            get("/portalCargaDeDatos", ((EntidadesPrestadorasController) FactoryController.controller("EntidadesPrestadoras"))::index);

            post("/portalCargaDeDatos", ((EntidadesPrestadorasController) FactoryController.controller("EntidadesPrestadoras"))::save);

            //post("/portalCargaDeDatos",((OrganismosDeControlController) FactoryController.controller("OrganismosDeControl"))::save);
            get("/usuarios", ((MiembrosController) FactoryController.controller("MiembrosYUsuarios"))::show);
            post("/validarUsuarios", ((MiembrosController) FactoryController.controller("MiembrosYUsuarios"))::recibirUsuariosValidados);

            get("/completar-datos", ((MiembrosController) FactoryController.controller("MiembrosYUsuarios"))::mostrarVistaDatosExtra); //los de municipio y localidades estan mas arriba
            post("/completar-datos", ((MiembrosController) FactoryController.controller("MiembrosYUsuarios"))::guardarDatosExtra);



            get("/agregarUsuario",((MiembrosController) FactoryController.controller("MiembrosYUsuarios"))::index);
            post("/agregarUsuario", ((MiembrosController) FactoryController.controller("MiembrosYUsuarios"))::save);

        });
    }
}