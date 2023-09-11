package domain.servicios;

import java.util.ArrayList;
import java.util.List;

import domain.Persistencia.EntidadPersistente;

import domain.services.geoRef.entidades.Localidad;
import domain.services.geoRef.entidades.Municipio;
import domain.services.geoRef.entidades.Provincia;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "entidad")
@Getter
@Setter
public class Entidad extends EntidadPersistente {

  @Column
  private String nombre;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  public List<Establecimiento> establecimientos;

  @Embedded
  private Provincia localizacionProvincia;
  @Embedded
  private Municipio localizacionMunicipio;
  @Embedded
  private Localidad localizacionLocalidad;
  @Column
  private String tipoEntidad;
  //@ManyToOne
  //@JoinColumn(name = "entidadPrestador_id", referencedColumnName = "id")
 // private EntidadPrestadora entidadPrestadora;

  // hacer bien los ManyToOne todo
  // CONVERTER PARA PROV, MUN, LOC todo

  public Entidad(String nombre) {
    this.nombre = nombre;
  }

  public Entidad() {

  }


  public void eliminarEstablecimiento(Establecimiento establecimiento) {
    establecimientos.remove(establecimiento);
  }

  public void agregarEstablecimiento(Establecimiento establecimiento) {
    establecimientos.add(establecimiento);
  }

  public List<Servicio> conseguirServiciosConProblemasDe(List<TipoDeServicio> serviciosAsociados) {
    List<Servicio> serviciosQueInteresan = new ArrayList<>();
    for(Establecimiento establecimiento : establecimientos) {
      serviciosQueInteresan.addAll(establecimiento.serviciosConProblemasDe(serviciosAsociados));
    }
    return serviciosQueInteresan;
  }

}


