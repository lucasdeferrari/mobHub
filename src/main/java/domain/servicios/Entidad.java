package domain.servicios;

import java.util.ArrayList;
import java.util.List;

import domain.Persistencia.EntidadPersistente;
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
public class Entidad extends EntidadPersistente {

  @Column
  private String nombre;
  @Transient
  public List<Establecimiento> establecimientos;
  @ManyToOne
  private Provincia localizacionProvincia;
  @ManyToOne
  private Municipio localizacionMunicipio;
  @ManyToOne
  private Departamento localizacionDepartamento;
  @Column
  private String tipoEntidad;

  // FK A LA ENTIDAD PRESTADORA ?? todo
  // hacer bien los ManyToOne todo

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


