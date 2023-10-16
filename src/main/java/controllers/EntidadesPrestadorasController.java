package controllers;

import domain.Repositorios.EntidadPrestadora.RepositorioEntidadPrestadora;
import domain.entidades.servicios.EntidadPrestadora;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import server.utils.ICrudViewsHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class EntidadesPrestadorasController implements ICrudViewsHandler {
    private RepositorioEntidadPrestadora repositorioEntidadPrestadora;

    public EntidadesPrestadorasController(RepositorioEntidadPrestadora repositorio) {
        this.repositorioEntidadPrestadora = repositorio;
    }

    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        List<EntidadPrestadora> entidadPrestadoras = this.repositorioEntidadPrestadora.buscarTodos();
        model.put("entidadPrestadora", entidadPrestadoras);
        context.render("entidadPrestadora.hbs", model);
    }

    @Override
    public void show(Context context) {
        EntidadPrestadora entidadPrestadora = (EntidadPrestadora) this.repositorioEntidadPrestadora.buscarPorId(Long.parseLong(context.pathParam("id")));
        Map<String, Object> model = new HashMap<>();
        model.put("entidadPrestadora", entidadPrestadora);
        context.render("entidadPrestadora/entidadPrestadora.hbs", model);
    }

    @Override
    public void create(Context context) {
        EntidadPrestadora entidadPrestadora = null;
        Map<String, Object> model = new HashMap<>();
        model.put("entidadPrestadora", entidadPrestadora);
        context.render("entidadPrestadora.hbs", model);
    }

    @Override
    public void save(Context context) {
        EntidadPrestadora entidadPrestadora = new EntidadPrestadora();
        this.asignarParametros(entidadPrestadora, context);
        this.repositorioEntidadPrestadora.guardar(entidadPrestadora);
        context.status(HttpStatus.CREATED);
        context.redirect("/entidadPrestadora");
    }

    @Override
    public void edit(Context context) {
        EntidadPrestadora entidadPrestadora = (EntidadPrestadora) this.repositorioEntidadPrestadora.buscarPorId(Long.parseLong(context.pathParam("id")));
        Map<String, Object> model = new HashMap<>();
        model.put("entidadPrestadora", entidadPrestadora);
        context.render("entidadPrestadora/entidadPrestadora.hbs", model);

    }

    @Override
    public void update(Context context) {
        EntidadPrestadora entidadPrestadora = (EntidadPrestadora) this.repositorioEntidadPrestadora.buscarPorId(Long.parseLong(context.pathParam("id")));
        this.asignarParametros(entidadPrestadora, context);
        this.repositorioEntidadPrestadora.actualizar(entidadPrestadora);
        context.redirect("/entidadPrestadora");
    }

    @Override
    public void delete(Context context) {
        EntidadPrestadora entidadPrestadora = (EntidadPrestadora) this.repositorioEntidadPrestadora.buscarPorId(Long.parseLong(context.pathParam("id")));
        this.repositorioEntidadPrestadora.eliminar(Long.parseLong(context.pathParam("id")));
        context.redirect("/entidadPrestadora");
    }

    private void asignarParametros(EntidadPrestadora entidadPrestadora, Context context) {
        if(!Objects.equals(context.formParam("nombre"), "")) {
           // entidadPrestadora.setNombre(context.formParam("nombre")); //TODO
        }
    }
}

