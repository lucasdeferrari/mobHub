package controllers;


public class FactoryController {
    public static Object controller(String nombre) {
        Object controller = null;
        switch (nombre) {

            case "Servicios": controller = new ServiciosController(new RepositorioDeServicios()); break;
            case "Incidentes": controller = new IncidentesController(new RepositorioDeServicios()); break;
            case "Miembros": controller = new MiembrosController(new RepositorioDeServicios()); break;
            case "Comunidad": controller = new ComunidadController(new RepositorioDeServicios()); break;
        }

        return controller;
    }
}
