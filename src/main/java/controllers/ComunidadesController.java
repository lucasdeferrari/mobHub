package controllers;


import domain.Repositorios.Comunidad.RepositorioComunidad;
import domain.entidades.comunidad.Comunidad;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import server.utils.ICrudViewsHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ComunidadesController implements ICrudViewsHandler {
    private RepositorioComunidad repositorioComunidad;

    public ComunidadesController(RepositorioComunidad repositorioDeComunidades) {
        this.repositorioComunidad = repositorioDeComunidades;
    }

    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        List<Comunidad> comunidades = this.repositorioComunidad.buscarTodos();
        model.put("comunidad", comunidades);
        context.render("comunidad.hbs", model);
    }

    @Override
    public void show(Context context) {
        Comunidad comunidad = (Comunidad) this.repositorioComunidad.buscarPorId(Long.parseLong(context.pathParam("id")));
        Map<String, Object> model = new HashMap<>();
        model.put("comunidad", comunidad);
        context.render("comunidad/comunidad.hbs", model);
    }

    @Override
    public void create(Context context) {
        Comunidad comunidad = null;
        Map<String, Object> model = new HashMap<>();
        model.put("comunidad", comunidad);
        context.render("comunidad/comunidad.hbs", model);
    }

    @Override
    public void save(Context context) {
        Comunidad comunidad = new Comunidad();
        this.asignarParametros(comunidad, context);
        this.repositorioComunidad.guardar(comunidad);
        context.status(HttpStatus.CREATED);
        context.redirect("/comunidad");
    }

    @Override
    public void edit(Context context) {
        Comunidad comunidad = (Comunidad) this.repositorioComunidad.buscarPorId(Long.parseLong(context.pathParam("id")));
        Map<String, Object> model = new HashMap<>();
        model.put("comunidad", comunidad);
        context.render("comunidad/comunidad.hbs", model);
    }

    @Override
    public void update(Context context) {
        Comunidad comunidad = (Comunidad) this.repositorioComunidad.buscarPorId(Long.parseLong(context.pathParam("id")));
        this.asignarParametros(comunidad, context);
        this.repositorioComunidad.actualizar(comunidad);
        context.redirect("/comunidad");
    }

    @Override
    public void delete(Context context) {
        Comunidad comunidad = (Comunidad) this.repositorioComunidad.buscarPorId(Long.parseLong(context.pathParam("id")));
        this.repositorioComunidad.eliminar(Long.parseLong(context.pathParam("id")));
        context.redirect("/comunidad");
    }

    private void asignarParametros(Comunidad comunidad, Context context) {
        if(!Objects.equals(context.formParam("nombre"), "")) {
            comunidad.setNombre(context.formParam("nombre"));
        }
    }
}

