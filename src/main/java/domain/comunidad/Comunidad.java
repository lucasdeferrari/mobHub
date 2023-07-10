package domain.comunidad;

import domain.notificaciones.notificacion.NotificacionApertura;
import domain.notificaciones.notificacion.NotificacionCierre;
import domain.servicios.Establecimiento;
import domain.servicios.Incidente;
import domain.servicios.Servicio;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class Comunidad {
  private String nombre;
  private String descripcion;
  private List<Miembro> miembros;
  private List<Miembro> administradores;
  private List<Incidente> incidentesAbiertos; //todo ver si va o no


  public void agregarMiembro(Miembro miembro) {
    miembros.add(miembro);
  }

  public void eliminarMiembro(Miembro miembro) {
    miembros.remove(miembro);
  }

  public Boolean esAdmin(Miembro miembro) {
    return administradores.contains(miembro);
  }

  public void agregarServicio(Servicio servicio, Establecimiento establecimiento) {
    establecimiento.agregarServicio(servicio);
  }

  public void agregarIncidente(Incidente unIncidente, Miembro miembroQueAbrio) {
    this.notificarAperturaAMiembros(unIncidente, miembroQueAbrio);
    incidentesAbiertos.add(unIncidente);

  }

  public void cerrarIncidente(Incidente unIncidente, Miembro miembroQueAbrio) {
    this.notificarCierreAMiembros(unIncidente, miembroQueAbrio);
    incidentesAbiertos.remove(unIncidente);
  }



  public boolean existeIncidenteReportado(Servicio servicio) {
    return incidentesAbiertos.stream().anyMatch(unIncidente -> unIncidente.tieneEsteServicio(servicio) );
  }


}
