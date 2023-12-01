package controllers;

import domain.Repositorios.Miembro.RepositorioMiembro;
import domain.entidades.Rankings.MayorCantidadReportes;
import domain.entidades.Rankings.Ranking;
import domain.entidades.comunidad.Miembro;
import domain.entidades.comunidad.Miembro;
import domain.entidades.generadorRankings.GeneradorRanking;
import domain.entidades.servicios.Entidad;
import domain.entidades.servicios.Incidente;
import domain.entidades.servicios.Servicio;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import server.utils.ICrudViewsHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RankingsController implements ICrudViewsHandler{
    GeneradorRanking generador = GeneradorRanking.getInstance();

    @Override
    public void index(Context context){

    }
    @Override
    public void show(Context context) {
        Integer id2 = context.sessionAttribute("id");
        if (id2 == null) {
            context.redirect("/inicio");
            return;  // Asegúrate de salir del método después de redirigir
        }

        List<Entidad> ranking1 =  generador.getRankingMayorPromedioCierre();
        List<Entidad> ranking2 = generador.getRankingMayorCantidadReportes();
        List<Incidente> ranking3 = generador.getRankingMayorGradoImpacto();

        Map<String, Object> model = new HashMap<>();

        model.put("es_admin", context.sessionAttribute("es_admin"));
        model.put("ranking1", ranking1);
        model.put("ranking2", ranking2);
        model.put("ranking3", ranking3);
        context.render("rankings.hbs", model);
    }

    @Override
    public void create(Context context) {

    }
    @Override
    public void save(Context context) {

    }

    @Override
    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {

    }

    @Override
    public void delete(Context context) {

    }

    public List buscarPorId(Long id) {
        if (id == 0) {
            return GeneradorRanking.getInstance().getRankingMayorCantidadReportes();
        }
        else if (id == 1) {
            return GeneradorRanking.getInstance().getRankingMayorGradoImpacto();
        }
        else {
            return GeneradorRanking.getInstance().getRankingMayorPromedioCierre();
        }

    }

}

