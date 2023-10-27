package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Repositorios.Miembro.RepositorioMiembro;
import domain.Repositorios.Usuario.RepositorioDeUsuarios;
import domain.entidades.comunidad.Miembro;
import domain.entidades.comunidad.Miembro;
import domain.entidades.servicios.Incidente;
import domain.entidades.servicios.Servicio;
import domain.entidades.signin.RolUsuario;
import domain.entidades.signin.Usuario;
import io.javalin.config.JavalinConfig;
import org.jetbrains.annotations.NotNull;
import server.exceptions.AccessDeniedException;
import server.middleware.AuthMiddleware;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import server.utils.ICrudViewsHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MiembrosController implements ICrudViewsHandler{
    private RepositorioMiembro repositorioMiembro;
    private RepositorioDeUsuarios repositorioDeUsuarios;
    public MiembrosController(RepositorioMiembro repositorio, RepositorioDeUsuarios repo){
        this.repositorioMiembro = repositorio;
        this.repositorioDeUsuarios = repo;
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
        List<Usuario> usuarios = this.repositorioDeUsuarios.buscarTodos();
        Map<String, Object> model = new HashMap<>();
        model.put("usuarios", usuarios);
        context.render("filtroUsuarios.hbs", model);
    }

    @Override
    public void create(Context context) {
        Miembro miembro= null;
        Map<String, Object> model = new HashMap<>();
        model.put("miembro", miembro);
        context.render("miembro.hbs", model);
    }
    @Override
    public void save(@NotNull Context context) {
        //-------------------------------------
        RolUsuario rolUsuario = AuthMiddleware.getUserRoleType(context);
        //
        if(rolUsuario != RolUsuario.ADMINISTRADOR_PLATAFORMA) {
            throw new AccessDeniedException();
        }

            Miembro miembro = new Miembro();
            this.asignarParametros(miembro, context);
            this.repositorioMiembro.guardar(miembro);
            context.status(HttpStatus.CREATED);
            context.redirect("/miembro");

        //-------------------------------------

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

    public void recibirUsuariosValidados(Context context) throws JsonProcessingException {
        String json = context.body();
        ObjectMapper objectMapper = new ObjectMapper();

        class UsuarioModificado {
            public int id;
            public RolUsuario rol;
            public boolean validado;
        }
        List<UsuarioModificado> usuariosModificados = objectMapper.readValue(json, new TypeReference<List<UsuarioModificado>>() {});

        for (UsuarioModificado usuarioModificado : usuariosModificados) {
            Usuario usuario = repositorioDeUsuarios.buscarPorId2(usuarioModificado.id);
            usuario.setValidado(usuarioModificado.validado);
            usuario.setRolUsuario(usuarioModificado.rol);
            repositorioDeUsuarios.actualizar(usuario);
        }
    }


    private void asignarParametros(Miembro miembro, Context context) {
        if(!Objects.equals(context.formParam("nombre"), "")) {
            miembro.setNombre(context.formParam("nombre"));
        }
    }
}

