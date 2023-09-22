package domain.comunidad;

import domain.Persistencia.EntidadPersistente;
import domain.notificaciones.NotificarAperturaAMiembros;
import domain.notificaciones.NotificarCierreAMiembros;
import domain.servicios.Establecimiento;
import domain.servicios.Incidente;
import domain.servicios.Servicio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

  @ElementCollection
  @CollectionTable(name = "comunidad_miembros", joinColumns = @JoinColumn(name = "comunidad_id"))
  @MapKeyJoinColumn(name = "miembro_id") // La clave del mapa será un Miembro
  @Enumerated(EnumType.STRING) // El valor del mapa será un RolComunidad
  @Column(name = "rol_comunidad")
  private Map<Miembro, RolComunidad> miembros = new HashMap<>();


  @OneToMany(mappedBy = "comunidad")
  private List<Incidente> incidentesAbiertos;

  // hacer bien el ManyToMany todo

  public void agregarMiembro(Miembro miembro, RolComunidad rolComunidad) {
    miembros.put(miembro, rolComunidad);
    miembro.getComunidadesPertenecientes().put(this,rolComunidad);}
  public void eliminarMiembro(Miembro miembro) {miembros.remove(miembro);}
  public Integer cantidadMiembro(){return miembros.size();}


  public boolean esAdmin(Miembro miembro) {
    return miembros.entrySet().stream()
            .anyMatch(entry -> entry.getKey().equals(miembro) && entry.getValue() == RolComunidad.ADMINISTRADOR);
  }

  public void agregarServicio(Servicio servicio, Establecimiento establecimiento) {
    establecimiento.agregarServicio(servicio);
  }

  public void agregarIncidente(Incidente unIncidente, Miembro miembroQueAbrio) {
    Map<Miembro, RolComunidad> miembrosInteresados = miembros.entrySet().stream()
            .filter(entry -> entry.getKey().leInteresaElIncidente(unIncidente))
            .collect(Collectors.toMap(
                    Map.Entry::getKey,   // Función para mapear a claves (en este caso, el Miembro)
                    Map.Entry::getValue  // Función para mapear a valores (en este caso, el Rol)
            ));    incidentesAbiertos.add(unIncidente);

    //para que no notifique al miembro que creo el incidente
    Map<Miembro, RolComunidad> listaSinElMiembro = miembrosInteresados.entrySet().stream()
            .filter(entry -> entry.getKey() != miembroQueAbrio)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    NotificarAperturaAMiembros notificador = new NotificarAperturaAMiembros();
    notificador.notificar(unIncidente, listaSinElMiembro);

  }

  public void cerrarIncidente(Incidente unIncidente, Miembro miembroQueCerro) {

    incidentesAbiertos.remove(unIncidente);
    Map<Miembro, RolComunidad> listaSinElMiembro = miembros.entrySet().stream()
            .filter(entry -> !entry.getKey().equals(miembroQueCerro))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    NotificarCierreAMiembros notificador = new NotificarCierreAMiembros();
    notificador.notificar(unIncidente, listaSinElMiembro);
  }

}

