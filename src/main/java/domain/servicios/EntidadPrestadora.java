package domain.servicios;

import java.util.List;

import domain.Rankings.GeneradorRanking;
import domain.Rankings.Ranking;
import lombok.Getter;
import org.apache.poi.ss.usermodel.Workbook;

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

    public Workbook obtenerInforme(){
       return GeneradorRanking.devolverInformeEntidadPrestadora(this, criterio);
    }

}
