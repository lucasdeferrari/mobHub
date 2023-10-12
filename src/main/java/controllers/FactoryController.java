package controllers;


import domain.Repositorios.Comunidad.RepositorioComunidad;
import domain.Repositorios.Entidad.RepositorioEntidad;
import domain.Repositorios.EntidadPrestadora.RepositorioEntidadPrestadora;
import domain.Repositorios.Establecimiento.RepositorioEstablecimiento;
import domain.Repositorios.Incidente.RepositorioIncidente;
import domain.Repositorios.Miembro.RepositorioMiembro;
import domain.Repositorios.OrganismoDeControl.RepositorioOrganismoDeControl;
import domain.Repositorios.Servicio.RepositorioServicio;
import domain.Repositorios.Usuario.RepositorioDeUsuarios;

public class FactoryController {
    public static Object controller(String nombre) {
        Object controller = null;
        switch (nombre) {

            case "Servicios": controller = new ServiciosController(new RepositorioServicio()); break;
            case "Incidentes": controller = new IncidentesController(new RepositorioIncidente(), new RepositorioComunidad(), new RepositorioServicio(), new RepositorioEstablecimiento(), new RepositorioMiembro()); break;
            case "Miembros": controller = new MiembrosController(new RepositorioMiembro()); break;
            case "Comunidades": controller = new ComunidadesController(new RepositorioComunidad()); break;
            case "Entidades": controller = new EntidadesController(new RepositorioEntidad()); break;
            case "Establecimientos": controller = new EstablecimientosController(new RepositorioEstablecimiento()); break;
            case "OrganismosDeControl": controller = new OrganismosDeControlController(new RepositorioOrganismoDeControl()); break;
            case "EntidadesPrestadoras": controller = new EntidadesPrestadorasController(new RepositorioEntidadPrestadora()); break;
            case "InicioSesion": controller =  new InicioDeSesionController(new RepositorioDeUsuarios());break;

        }

        return controller;
    }
}
