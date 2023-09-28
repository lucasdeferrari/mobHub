package controllers;

import domain.Repositorios.Miembro.RepositorioMiembro;
import domain.entidades.comunidad.Miembro;
import domain.entidades.comunidad.Miembro;
import domain.entidades.servicios.Servicio;
import io.javalin.http.Context;
import server.utils.ICrudViewsHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MiembrosController implements ICrudViewsHandler{
    private RepositorioMiembro repositorioMiembro
    public MiembrosController(RepositorioMiembro repositorio){
        this.repositorioMiembro = repositorio;
    }

    @Override
    public void index(Context context){
        Map<String, Object> model = new HashMap<>();
        List<Miembro> miembros = this.repositorioMiembro.buscarTodos();
        model.put("miembros", miembros);
        context.render("miembros.hbs", model);
    }
    @Override
    public void show(Context context) {
        Miembro miembro = (Miembro) this.repositorioMiembro.buscarPorId(Long.parseLong(context.pathParam("id")));
        Map<String, Object> model = new HashMap<>();
        model.put("miembro", miembro);
        context.render("miembros.hbs", model);
    }

    @Override
    public void create(Context context) {
        Miembro miembro= null;
        Map<String, Object> model = new HashMap<>();
        model.put("miembro", miembro);
        context.render("miembros.hbs", model);
    }
    
}
