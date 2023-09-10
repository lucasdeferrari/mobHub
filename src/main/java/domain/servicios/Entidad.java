package domain.servicios;

import java.util.ArrayList;
import java.util.List;

import domain.services.geoRef.entidades.Departamento;
import domain.services.geoRef.entidades.Municipio;
import domain.services.geoRef.entidades.Provincia;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "entidad")
@Getter
@Setter
public class Entidad {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column
  private String nombre;
  @Transient
  public List<Establecimiento> establecimientos;
  @Transient
  private Provincia localizacionProvincia;
  @Transient
  private Municipio localizacionMunicipio;
  @Transient
  private Departamento localizacionDepartamento;
  @Column
  private String tipoEntidad;

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


