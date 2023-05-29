package domain.servicios;

import java.util.List;

public class Establecimiento {
  private String nombre;
  private Ubicacion ubicacion;
  private List<Servicio> servicios;

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
}
