package domain.comunidad;

import domain.notificaciones.NotificarAperturaAMiembros;
import domain.notificaciones.NotificarCierreAMiembros;
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
  private HashMap<Miembro, Rol> miembros;
  //private List<Miembro> miembros;
  private List<Miembro> administradores;
  private List<Incidente> incidentesAbiertos; //todo ver si va o no


  public void agregarMiembro(Miembro miembro, Rol rol) {
    miembros.put(miembro, rol);
  }
  public void cambiarRol(Miembro miembro, Rol nuevoRol){miembros.put(miembro, nuevoRol); }
  public void eliminarMiembro(Miembro miembro) {miembros.remove(miembro);}
  public Integer cantidadMiembro(){return miembros.size();}
  public Boolean esAdmin(Miembro miembro) {
    return administradores.contains(miembro);
  }

  public void agregarServicio(Servicio servicio, Establecimiento establecimiento) {
    establecimiento.agregarServicio(servicio);
  }

  public void agregarIncidente(Incidente unIncidente, Miembro miembroQueAbrio) {
    List<Miembro> listaMiembros = new ArrayList<>(miembros.keySet());
    List<Miembro> miembrosALosQueLeInteresa = listaMiembros.stream().filter(unMiembro -> unMiembro.leInteresaElIncidente(unIncidente)).collect(Collectors.toList());
    incidentesAbiertos.add(unIncidente);
    //para que no notifique al miembro que creo el incidente
    List <Miembro> listaSinElMiembro = miembrosALosQueLeInteresa.stream().filter(unMiembro -> unMiembro != miembroQueAbrio).collect(Collectors.toList());

    NotificarAperturaAMiembros.notificar(unIncidente, listaSinElMiembro);

  }

  public void cerrarIncidente(Incidente unIncidente, Miembro miembroQueCerro) {

    incidentesAbiertos.remove(unIncidente);
    List<Miembro> listaMiembros = new ArrayList<>(miembros.keySet());
    List <Miembro> listaSinElMiembro = listaMiembros.stream().filter(unMiembro -> unMiembro != miembroQueCerro).collect(Collectors.toList());

    NotificarCierreAMiembros.notificar(unIncidente, listaSinElMiembro);
  }



  public boolean existeIncidenteReportado(Servicio servicio) {
    return incidentesAbiertos.stream().anyMatch(unIncidente -> unIncidente.tieneEsteServicio(servicio) );
  }


}
