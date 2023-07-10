package domain.servicios;

import java.util.List;

import domain.generadorRankings.GeneradorRanking;
import domain.Rankings.Ranking;
import lombok.Getter;

@Getter
public class EntidadPrestadora {

    private String nombre;
    private List<Entidad> entidades;
    private Ranking criterio;


    public void eliminarEntidad(Entidad entidad) {
        entidades.remove(entidad);
    }

    public void agregarEntidad(Entidad entidad) {
        entidades.add(entidad);
    }

    public void obtenerInforme(){
        GeneradorRanking.devolverInformeEntidadPrestadora(this, criterio);
    }

}
