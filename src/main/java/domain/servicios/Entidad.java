package domain.servicios;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Entidad {
  private String nombre;
  public List<Establecimiento> establecimientos;
  private Localizacion localizacion;

  public Entidad(String nombre, List<Establecimiento> establecimientos, Localizacion localizacion) {
    this.nombre = nombre;
    this.establecimientos = establecimientos;
    this.localizacion = localizacion;
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


