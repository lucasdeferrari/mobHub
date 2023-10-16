package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Repositorios.Comunidad.RepositorioComunidad;
import domain.Repositorios.Establecimiento.RepositorioEstablecimiento;
import domain.Repositorios.Incidente.RepositorioIncidente;
import domain.Repositorios.Miembro.RepositorioMiembro;
import domain.Repositorios.Servicio.RepositorioServicio;
import domain.entidades.comunidad.Comunidad;
import domain.entidades.comunidad.Miembro;
import domain.entidades.servicios.Establecimiento;
import domain.entidades.servicios.Incidente;
import domain.entidades.servicios.Servicio;
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
        Incidente incidente = (Incidente) this.repositorioIncidente.buscarPorId(Long.parseLong(context.queryParam("id")));
        Map<String, Object> model = new HashMap<>();
        model.put("incidente", incidente);
        context.render("datosIncidente.hbs", model);
    }

    @Override
    public void create(Context context) {

       Map<String, Object> model = new HashMap<>();

        List<Comunidad> comunidades = repositorioComunidad.buscarTodos();
        List<Servicio> servicios = repositorioServicio.buscarTodos();
        List<Establecimiento> establecimientos = repositorioEstablecimiento.buscarTodos();

        model.put("comunidades", comunidades);
        model.put("servicios", servicios);
        model.put("establecimientos", establecimientos);
        context.render("ReportarIncidente.hbs");
    }

    @Override
    public void save(Context context) {
        Incidente incidente = new Incidente();
        String id_usuario = context.sessionAttribute("id");
        Miembro miembro = repositorioMiembro.buscarPorId(Long.parseLong(id_usuario)); //todo revisar que solo un miembro pueda reportar
        incidente.setQuienAbrio(miembro);
        this.asignarParametros(incidente, context);
        this.repositorioIncidente.guardar(incidente);
        context.status(HttpStatus.CREATED);
        context.redirect("/incidentes/reportar");
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
        context.sessionAttribute("id");
        Miembro miembro = repositorioMiembro.buscarPorId(Long.parseLong(context.formParam("id")));
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
            Incidente incidente = repositorioIncidente.buscarPorId(id.longValue());

            if (incidente != null) {
                context.sessionAttribute("id");
                Miembro miembro = repositorioMiembro.buscarPorId(Long.parseLong(context.formParam("id")));
                miembro.cerrarIncidente(incidente);
                repositorioIncidente.actualizar(incidente);
            }
        }

    }


    private void asignarParametros(Incidente incidente, Context context) {
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
            incidente.setServicio(repositorioServicio.buscarPorNombre("servicio"));
        }

    }


}

