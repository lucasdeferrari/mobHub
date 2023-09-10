package domain.servicios;

import java.util.List;

import domain.Persistencia.EntidadPersistente;
import domain.generadorRankings.GeneradorRanking;
import domain.Rankings.Ranking;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name="entidadPrestadora")
public class EntidadPrestadora extends EntidadPersistente {

    @Column
    private String nombre;
    @Transient
    private List<Entidad> entidades;
    // NO SE QUE RELACION IRIA ACA TODO
    private Ranking criterio;

    // FK AL ORG DE CONTROL ?? todo



    public void eliminarEntidad(Entidad entidad) {
        entidades.remove(entidad);
    }

    public void agregarEntidad(Entidad entidad) {
        entidades.add(entidad);
    }

    public void obtenerInforme(){
        GeneradorRanking generadorRanking = new GeneradorRanking();
        generadorRanking.devolverInformeEntidadPrestadora(this, criterio);
    }

}
