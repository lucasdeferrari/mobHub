package domain.comunidad;

import domain.Persistencia.EntidadPersistente;
import domain.notificaciones.NotificarAperturaAMiembros;
import domain.notificaciones.NotificarCierreAMiembros;
import domain.servicios.Establecimiento;
import domain.servicios.Incidente;
import domain.servicios.Servicio;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.formula.eval.UnaryMinusEval;

import javax.persistence.*;

import javax.persistence.*;
import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table
public class Comunidad extends EntidadPersistente {

  @Column
  private String nombre;

  @Column
  private String descripcion;

  @ManyToMany
  private List<Miembro> miembros;

  @ManyToMany()
  @JoinTable(name = "administrador_comunidad")
  private List<Miembro> administradores;

  @OneToMany(mappedBy = "comunidad")
  private List<Incidente> incidentesAbiertos;

  // hacer bien el ManyToMany todo

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

