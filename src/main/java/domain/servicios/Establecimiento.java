package domain.servicios;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Establecimiento {
  private String nombre;
  private Ubicacion ubicacion;
  public List<Servicio> servicios;

  public Establecimiento(String nombre, Ubicacion ubicacion, List<Servicio> servicios) {
    this.nombre = nombre;
    this.ubicacion = ubicacion;
    this.servicios = servicios;
  }

  public void agregarServicio(Servicio servicio) {
    servicios.add(servicio);
  }

  public void eliminarServicio(Servicio servicio) {
    servicios.remove(servicio);
  }

  public void modificarPrestacion(Servicio servicio, Estado estado) {
    servicio.setEstado(estado);
  }

  public boolean tieneServiciosDenegados() {
   return servicios.stream().anyMatch(unServicio -> unServicio.estaDenegado());
  }

  public List<Servicio> serviciosConProblemasDe(List<TipoDeServicio> unosServicios) {
    List<Servicio> serviciosDenegados =  servicios.stream().filter(unServicio -> unServicio.estaDenegado()).collect(Collectors.toList());
    return serviciosDenegados.stream().filter(unServicio -> unosServicios.contains(unServicio.getNombre())).collect(Collectors.toList());
  } // devuelve una lista con los servicios que hay que agregar a los servicios con interes

}
