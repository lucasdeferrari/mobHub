package controllers;

import domain.Repositorios.Miembro.RepositorioMiembro;
import domain.entidades.Rankings.MayorCantidadReportes;
import domain.entidades.Rankings.Ranking;
import domain.entidades.comunidad.Miembro;
import domain.entidades.comunidad.Miembro;
import domain.entidades.generadorRankings.GeneradorRanking;
import domain.entidades.servicios.Entidad;
import domain.entidades.servicios.Servicio;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import server.utils.ICrudViewsHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RankingsController implements ICrudViewsHandler{

    @Override
    public void index(Context context){

    }
    @Override
    public void show(Context context) {
        List<Entidad> ranking = this.buscarPorId(Long.parseLong(context.pathParam("id")));
        Map<String, Object> model = new HashMap<>();
        model.put("ranking", ranking);
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

    public List<Entidad> buscarPorId(Long id) {
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

