package controllers;

import domain.Repositorios.Miembro.RepositorioMiembro;
import domain.entidades.comunidad.Miembro;
import domain.entidades.comunidad.Miembro;
import domain.entidades.servicios.Servicio;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import server.utils.ICrudViewsHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MiembrosController implements ICrudViewsHandler{
    private RepositorioMiembro repositorioMiembro;
    public MiembrosController(RepositorioMiembro repositorio){
        this.repositorioMiembro = repositorio;
    }

    @Override
    public void index(Context context){
        Map<String, Object> model = new HashMap<>();
        List<Miembro> miembros = this.repositorioMiembro.buscarTodos();
        model.put("miembros", miembros);
        context.render("miembro.hbs", model);
    }
    @Override
    public void show(Context context) {
        Miembro miembro = (Miembro) this.repositorioMiembro.buscarPorId(Long.parseLong(context.pathParam("id")));
        Map<String, Object> model = new HashMap<>();
        model.put("miembro", miembro);
        context.render("miembro.hbs", model);
    }

    @Override
    public void create(Context context) {
        Miembro miembro= null;
        Map<String, Object> model = new HashMap<>();
        model.put("miembro", miembro);
        context.render("miembro.hbs", model);
    }
    @Override
    public void save(Context context) {
        Miembro miembro = new Miembro();
        this.asignarParametros(miembro, context);
        this.repositorioMiembro.guardar(miembro);
        context.status(HttpStatus.CREATED);
        context.redirect("/miembro");
    }

    @Override
    public void edit(Context context) {
        Miembro miembro = (Miembro) this.repositorioMiembro.buscarPorId(Long.parseLong(context.pathParam("id")));
        Map<String, Object> model = new HashMap<>();
        model.put("miembro", miembro);
        context.render("miembro/miembro.hbs", model);
    }

    @Override
    public void update(Context context) {
        Miembro miembro = (Miembro) this.repositorioMiembro.buscarPorId(Long.parseLong(context.pathParam("id")));
        this.asignarParametros(miembro, context);
        this.repositorioMiembro.actualizar(miembro);
        context.redirect("/miembro");
    }

    @Override
    public void delete(Context context) {
        Miembro miembro = (Miembro) this.repositorioMiembro.buscarPorId(Long.parseLong(context.pathParam("id")));
        this.repositorioMiembro.eliminar(Long.parseLong(context.pathParam("id")));
        context.redirect("/miembro");
    }

    private void asignarParametros(Miembro miembro, Context context) {
        if(!Objects.equals(context.formParam("nombre"), "")) {
            miembro.setNombre(context.formParam("nombre"));
        }
    }
}

