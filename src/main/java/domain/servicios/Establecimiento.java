package domain.servicios;

import java.util.List;

@Getter
@Setter
public class Establecimiento {
  private String nombre;
  private Ubicacion ubicacion;
  public List<Servicio> servicios;

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

  public List<Servicio> serviciosConProblemas(List<Servicio> unosServicios) {
    return unosServicios.stream().filter(unServicio -> unServicio.estaDenegado()).collect(Collectors.toList());
  }

  public List<Servicio> filtrarServiciosAsociadosConProblemas(List<TipoDeServicio> serviciosAsociados) {
    List<Servicio> serviciosQueInteresan = servicios.stream().filter(unServicio -> unServicio.esDeInteres(serviciosAsociados)).collect(Collectors.toList());
    return this.serviciosConProblemas(serviciosQueInteresan);
  }
}
