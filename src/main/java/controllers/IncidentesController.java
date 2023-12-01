package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Repositorios.Comunidad.RepositorioComunidad;
import domain.Repositorios.Establecimiento.RepositorioEstablecimiento;
import domain.Repositorios.Incidente.RepositorioIncidente;
import domain.Repositorios.Miembro.RepositorioMiembro;
import domain.Repositorios.Servicio.RepositorioServicio;
import domain.Repositorios.Usuario.RepositorioDeUsuarios;
import domain.entidades.comunidad.Comunidad;
import domain.entidades.comunidad.Miembro;
import domain.entidades.servicios.Establecimiento;
import domain.entidades.servicios.Incidente;
import domain.entidades.servicios.Servicio;
import domain.entidades.signin.RolUsuario;
import domain.entidades.signin.Usuario;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import server.utils.ICrudViewsHandler;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.UUID;
public class IncidentesController implements ICrudViewsHandler {
    private RepositorioIncidente repositorioIncidente;
    private RepositorioComunidad repositorioComunidad;
    private RepositorioEstablecimiento repositorioEstablecimiento;
    private RepositorioServicio repositorioServicio;
    private RepositorioMiembro repositorioMiembro;
    private RepositorioDeUsuarios repositorioDeUsuarios;


    public IncidentesController(RepositorioIncidente repositorioDeIncidentes, RepositorioComunidad repositorioComunidad, RepositorioServicio repositorioServicio, RepositorioEstablecimiento repositorioEstablecimiento, RepositorioMiembro repositorioMiembro, RepositorioDeUsuarios repositorioDeUsuarios) {
        this.repositorioIncidente = repositorioDeIncidentes;
        this.repositorioComunidad = repositorioComunidad;
        this.repositorioEstablecimiento = repositorioEstablecimiento;
        this.repositorioServicio = repositorioServicio;
        this.repositorioMiembro = repositorioMiembro;
        this.repositorioDeUsuarios = repositorioDeUsuarios;
    }

    @Override
    public void index(Context context) {
        Integer id2 = context.sessionAttribute("id");
        if (id2 == null) {
            context.redirect("/inicio");
            return;  // Asegúrate de salir del método después de redirigir
        }


        Map<String, Object> model = new HashMap<>();
        Integer id = context.sessionAttribute("id");
        Usuario usuario = repositorioDeUsuarios.buscarPorId(id);

        List<Incidente> incidentes = this.repositorioIncidente.buscarTodos();

        if(usuario.getRolUsuario().toString().equals("MIEMBRO")) {
            incidentes = incidentes.stream().filter(unIncidente -> unIncidente.estadoAbierto()).collect(Collectors.toList());
        }

        System.out.println("rol del usuario:" + usuario.getRolUsuario().toString());

        model.put("incidentes", incidentes);
        context.render("incidentes.hbs", model);
    }

    @Override
    public void show(Context context) {
        System.out.println(context.pathParam("id"));
        Incidente incidente = this.repositorioIncidente.buscarPorToken((context.pathParam("id")));
        //Servicio servicio = (Servicio) this.repositorioDeServicios.buscar(Long.parseLong(context.pathParam("id")));
        Map<String, Object> model = new HashMap<>();
        model.put("incidente", incidente);
        context.render("datosIncidente.hbs", model);

    }

    @Override
    public void create(Context context) {
        Integer id2 = context.sessionAttribute("id");
        if (id2 == null) {
            context.redirect("/inicio");
            return;  // Asegúrate de salir del método después de redirigir
        }


       Map<String, Object> model = new HashMap<>();

        List<Comunidad> comunidades = repositorioComunidad.buscarTodos();
        List<Servicio> servicios = repositorioServicio.buscarTodos();
       List<Establecimiento> establecimientos = repositorioEstablecimiento.buscarTodos();

        model.put("comunidades", comunidades);
        model.put("servicios", servicios);
        model.put("establecimientos", establecimientos);
        context.render("ReportarIncidente.hbs", model);
    }

    @Override
    public void save(Context context) {
        Incidente incidente = new Incidente();
        Integer userid = context.sessionAttribute("id");
        System.out.println(userid);
        Miembro miembro = this.repositorioMiembro.buscarPorId2((userid));//todo revisar que solo un miembro pueda reportar
        incidente.setQuienAbrio(miembro);
        this.asignarParametros(incidente, context);
        incidente.setToken(UUID.randomUUID().toString());
        this.repositorioIncidente.guardar(incidente);
        context.status(HttpStatus.CREATED);
        context.redirect("/incidentes/reportar");
    }

    @Override
    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {
        Incidente incidente = (Incidente) this.repositorioIncidente.buscarPorId(Long.parseLong(context.pathParam("id")));
        Miembro miembro = repositorioMiembro.buscarPorId(Long.parseLong(context.sessionAttribute("id")));
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
    public void recibirIncidentesCerrados(Context context) throws JsonProcessingException {
        String json = context.body();
        ObjectMapper objectMapper = new ObjectMapper();
        List<Integer> incidenteIds = objectMapper.readValue(json, new TypeReference<List<Integer>>() {});
        for (Integer id : incidenteIds) {
            Incidente incidente = repositorioIncidente.buscarPorId2(id);
            if (incidente != null) {
                context.sessionAttribute("id");
                Miembro miembro = repositorioMiembro.buscarPorId2(context.sessionAttribute("id"));
                miembro.cerrarIncidente(incidente);
                repositorioIncidente.actualizar(incidente);
            }
        }
    }


    private void asignarParametros(Incidente incidente, Context context) {

        incidente.setFechaHoraApertura(LocalDateTime.now());

        if(!Objects.equals(context.formParam("nombre"), "")) {
            incidente.setNombre(context.formParam("nombre"));
        }
        if(!Objects.equals(context.formParam("observaciones"), "")) {
            incidente.setObservaciones(context.formParam("observaciones"));
        }
        if(!Objects.equals(context.formParam("comunidad"), "")) {
            incidente.setComunidad(repositorioComunidad.buscarPorNombre(context.formParam("comunidad")));

        }
        if(!Objects.equals(context.formParam("establecimiento"), "")) {
            incidente.setEstablecimiento(repositorioEstablecimiento.buscarPorNombre(context.formParam("establecimiento")));
        }
        if(!Objects.equals(context.formParam("servicio"), "")) {
            incidente.setServicio(repositorioServicio.buscarPorNombre(context.formParam("servicio")));
        }

    }

    public void sugerirCierreIncidente(Context context) {
        System.out.println(context.pathParam("id"));

        String idStr = context.pathParam("id");
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            // Manejar una excepción si el valor no se puede convertir a int
            throw new RuntimeException();
        }

        Incidente incidente = this.repositorioIncidente.buscarPorId2(id);


    }


}

