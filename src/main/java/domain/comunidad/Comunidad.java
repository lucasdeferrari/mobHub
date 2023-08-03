package domain.comunidad;

import domain.notificaciones.NotificarAperturaAMiembros;
import domain.notificaciones.NotificarCierreAMiembros;
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
  private List<Incidente> incidentesAbiertos;


  public void agregarMiembro(Miembro miembro) {miembros.add(miembro);}
  public void eliminarMiembro(Miembro miembro) {miembros.remove(miembro);}
  public Integer cantidadMiembro(){return miembros.size();}
  public Boolean esAdmin(Miembro miembro) {
    return administradores.contains(miembro);
  }

  public void agregarServicio(Servicio servicio, Establecimiento establecimiento) {
    establecimiento.agregarServicio(servicio);
  }

  public void agregarIncidente(Incidente unIncidente, Miembro miembroQueAbrio) {
    List<Miembro> miembrosALosQueLeInteresa = miembros.stream().filter(unMiembro -> unMiembro.leInteresaElIncidente(unIncidente)).collect(Collectors.toList());
    incidentesAbiertos.add(unIncidente);
    //para que no notifique al miembro que creo el incidente
    List <Miembro> listaSinElMiembro = miembrosALosQueLeInteresa.stream().filter(unMiembro -> unMiembro != miembroQueAbrio).collect(Collectors.toList());

    NotificarAperturaAMiembros notificador = new NotificarAperturaAMiembros();
    notificador.notificar(unIncidente, listaSinElMiembro);

  }

  public void cerrarIncidente(Incidente unIncidente, Miembro miembroQueCerro) {

    incidentesAbiertos.remove(unIncidente);
    List <Miembro> listaSinElMiembro = miembros.stream().filter(unMiembro -> unMiembro != miembroQueCerro).collect(Collectors.toList());

    NotificarCierreAMiembros notificador = new NotificarCierreAMiembros();
    notificador.notificar(unIncidente, listaSinElMiembro);
  }

}
