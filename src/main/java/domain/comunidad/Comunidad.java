package domain.comunidad;

import domain.notificaciones.tipoDeNotificacion.NotificacionApertura;
import domain.servicios.Establecimiento;
import domain.servicios.Servicio;
import domain.servicios.Incidente;

import javax.management.Notification;
import java.util.List;

public class Comunidad {
  private String nombre;
  private String descripcion;
  private List<Miembro> miembros;
  private List<Miembro> administradores;
  private List<Incidente> incidentesAbiertos;


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

  public void agregarIncidente(Incidente unIncidente) {
    this.notificarAperturaAMiembros(unIncidente);
    incidentesAbiertos.add(unIncidente);

  }

  public void cerrarIncidente(Incidente unIncidente) {
    this.notificarCierreAMiembros(unIncidente);
    incidentesAbiertos.remove(unIncidente);
  }

  public void notificarAperturaAMiembros(Incidente unIncidente) {
    NotificacionApertura notificacion = new NotificacionApertura(unIncidente);
    notificacion.asunto();
    notificacion.cuerpo();

    miembros.forEach(unMiembro -> unMiembro.recibirNotificacion(notificacion));

    //TODO FACTORY

  }
  public void notificarCierreAMiembros(Incidente unIncidente) {


    //miembros.forEach(unMiembro -> unMiembro.recibirNotificacion(notificacion));
    //TODO FACTORY
  }

}
