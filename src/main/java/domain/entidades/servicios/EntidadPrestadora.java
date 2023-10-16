package domain.entidades.servicios;

import java.util.List;

import domain.Persistencia.EntidadPersistente;
import domain.Persistencia.RankingConverter;
import domain.entidades.signin.RolUsuario;
import domain.entidades.generadorRankings.GeneradorRanking;
import domain.entidades.Rankings.Ranking;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name="entidadPrestadora")
public class EntidadPrestadora extends EntidadPersistente {

    @Column
    private String nombre;
    @OneToMany(mappedBy = "entidadPrestadora", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Entidad> entidades;
    // NO SE QUE RELACION IRIA ACA TODO

    @ManyToOne
    @JoinColumn(name="organismo_id", referencedColumnName = "id")
    private OrganismoDeControl organismoDeControl;

    @Convert(converter = RankingConverter.class)
    @Column(columnDefinition = "VARCHAR(35)")
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
