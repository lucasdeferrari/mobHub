package domain.servicios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domain.services.geoRef.entidades.Departamento;
import domain.services.geoRef.entidades.Municipio;
import domain.services.geoRef.entidades.Provincia;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Entidad {

  private String nombre;
  public List<Establecimiento> establecimientos;
  private Provincia localizacionProvincia;
  private Municipio localizacionMunicipio;
  private Departamento localizacionDepartamento;
  private String tipoEntidad;
/*
  tipoEntidad ": " nombre
 */

  public Entidad(String nombre, List<Establecimiento> establecimientos) {
    this.nombre = nombre;
    this.establecimientos = establecimientos;

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


