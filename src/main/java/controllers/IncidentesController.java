package controllers;

import domain.Repositorios.Incidente.RepositorioIncidente;
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

    public IncidentesController(RepositorioIncidente repositorioDeIncidentes) {
        this.repositorioIncidente = repositorioDeIncidentes;
    }

    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        List<Incidente> servicios = this.repositorioIncidente.buscarTodos();
        model.put("incidentes", servicios);
        context.render("incidentes.hbs", model);
    }

    @Override
    public void show(Context context) {
        Incidente incidente = (Incidente) this.repositorioIncidente.buscarPorId(Long.parseLong(context.pathParam("id")));
        Map<String, Object> model = new HashMap<>();
        model.put("incidente", incidente);
        context.render("incidentes/incidentes.hbs", model);
    }

    @Override
    public void create(Context context) {
        Incidente incidente = null;
        Map<String, Object> model = new HashMap<>();
        model.put("incidente", incidente);
        context.render("incidentes/incidente.hbs", model);
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
        context.render("incidentes/incidente.hbs", model);
    }

    @Override
    public void update(Context context) {
        Incidente incidente = (Incidente) this.repositorioIncidente.buscarPorId(Long.parseLong(context.pathParam("id")));
        this.asignarParametros(incidente, context);
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
    }
}

