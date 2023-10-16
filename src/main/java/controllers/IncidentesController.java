package controllers;

import domain.Repositorios.Comunidad.RepositorioComunidad;
import domain.Repositorios.Establecimiento.RepositorioEstablecimiento;
import domain.Repositorios.Incidente.RepositorioIncidente;
import domain.Repositorios.Miembro.RepositorioMiembro;
import domain.Repositorios.Servicio.RepositorioServicio;
import domain.entidades.comunidad.Comunidad;
import domain.entidades.comunidad.Miembro;
import domain.entidades.servicios.Incidente;
import domain.entidades.servicios.Servicio;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import server.utils.ICrudViewsHandler;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class IncidentesController implements ICrudViewsHandler {
    private RepositorioIncidente repositorioIncidente;
    private RepositorioComunidad repositorioComunidad;
    private RepositorioEstablecimiento repositorioEstablecimiento;
    private RepositorioServicio repositorioServicio;
    private RepositorioMiembro repositorioMiembro;


    public IncidentesController(RepositorioIncidente repositorioDeIncidentes, RepositorioComunidad repositorioComunidad, RepositorioServicio repositorioServicio, RepositorioEstablecimiento repositorioEstablecimiento, RepositorioMiembro repositorioMiembro) {
        this.repositorioIncidente = repositorioDeIncidentes;
        this.repositorioComunidad = repositorioComunidad;
        this.repositorioEstablecimiento = repositorioEstablecimiento;
        this.repositorioServicio = repositorioServicio;
        this.repositorioMiembro = repositorioMiembro;
    }

    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        List<Incidente> incidentes = this.repositorioIncidente.buscarTodos();
        model.put("incidentes", incidentes);
        context.render("incidentes.hbs", model);
    }

    @Override
    public void show(Context context) {
        Incidente incidente = (Incidente) this.repositorioIncidente.buscarPorId(Long.parseLong(context.pathParam("id")));
        Map<String, Object> model = new HashMap<>();
        model.put("incidente", incidente);
        context.render("incidentes.hbs", model);
    }

    @Override
    public void create(Context context) {
        Incidente incidente = null;
        Map<String, Object> model = new HashMap<>();
        model.put("incidente", incidente);
        context.render("incidentes.hbs", model);
    }

    @Override
    public void save(Context context) {
        Incidente incidente = new Incidente();
        this.asignarParametros(incidente, context);
        this.repositorioIncidente.guardar(incidente);
        context.status(HttpStatus.CREATED);
        context.redirect("/incidentes");
    }

    @Override
    public void edit(Context context) {
        Incidente incidente = (Incidente) this.repositorioIncidente.buscarPorId(Long.parseLong(context.pathParam("id")));
        Map<String, Object> model = new HashMap<>();
        model.put("incidente", incidente);
        context.render("incidentes.hbs", model);
    }

    @Override
    public void update(Context context) {
        Incidente incidente = (Incidente) this.repositorioIncidente.buscarPorId(Long.parseLong(context.pathParam("id")));
        context.sessionAttribute("nombreUsuario");
        Miembro miembro = repositorioMiembro.buscarPorId(Long.parseLong(context.formParam("nombreUsuario")));
        miembro.cerrarIncidente(incidente);
        this.repositorioIncidente.actualizar(incidente);
        context.redirect("/incidentes");
    }

    @Override
    public void delete(Context context) {
        Incidente incidente = (Incidente) this.repositorioIncidente.buscarPorId(Long.parseLong(context.pathParam("id")));
        this.repositorioIncidente.eliminar(Long.parseLong(context.pathParam("id")));
        context.redirect("/incidentes");
    }

    private void asignarParametros(Incidente incidente, Context context) {
        if(!Objects.equals(context.formParam("nombre"), "")) {
            incidente.setNombre(context.formParam("nombre"));
        }
        if(!Objects.equals(context.formParam("observaciones"), "")) {
            incidente.setObservaciones(context.formParam("observaciones"));
        }
        if(!Objects.equals(context.formParam("comunidad"), "")) {
            incidente.setComunidad(repositorioComunidad.buscarPorId(Long.parseLong(context.formParam("comunidad"))));
        }
        if(!Objects.equals(context.formParam("establecimiento"), "")) {
            incidente.setEstablecimiento(repositorioEstablecimiento.buscarPorId(Long.parseLong(context.formParam("establecimiento"))));
        }
        if(!Objects.equals(context.formParam("servicio"), "")) {
            incidente.setServicio(repositorioServicio.buscarPorId(Long.parseLong(context.formParam("servicio"))));
        }

    }

}

