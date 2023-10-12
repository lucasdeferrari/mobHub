package domain.entidades.comunidad;

import domain.Persistencia.EntidadPersistente;
import domain.entidades.notificaciones.NotificarAperturaAMiembros;
import domain.entidades.notificaciones.NotificarCierreAMiembros;
import domain.entidades.servicios.Establecimiento;
import domain.entidades.servicios.Incidente;
import domain.entidades.servicios.Servicio;
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
  @Setter
  private String nombre;

  @Column
  private String descripcion;

  @ElementCollection
  @CollectionTable(name = "comunidad_miembros", joinColumns = @JoinColumn(name = "comunidad_id"))
  @MapKeyJoinColumn(name = "miembro_id") // La clave del mapa será un Miembro
  @Enumerated(EnumType.STRING) // El valor del mapa será un RolComunidad
  @Column(name = "rol_comunidad")
  private Map<Miembro, RolComunidad> miembrosNuestro = new HashMap<>();


  @OneToMany(mappedBy = "comunidad")
  private List<Incidente> incidentesAbiertos = new ArrayList<>();

  @Transient
  private List<Establecimiento> establecimientos = new ArrayList<>();

  @Transient
  private List<Miembro> miembros = new ArrayList<>();

  @Transient
  private List<Servicio> servicios = new ArrayList<>();

  @Transient
  private Integer gradoDeConfianza;

  public void agregarMiembro(Miembro miembro, RolComunidad rolComunidad) {
    miembrosNuestro.put(miembro, rolComunidad);
    miembros.add(miembro);
    miembro.getComunidadesPertenecientes().put(this,rolComunidad);}
  public void eliminarMiembro(Miembro miembro) {
    miembros.remove(miembro);
    miembrosNuestro.remove(miembro);
  }
  public Integer cantidadMiembro(){return miembrosNuestro.size();}


  public boolean esAdmin(Miembro miembro) {
    return miembrosNuestro.entrySet().stream()
            .anyMatch(entry -> entry.getKey().equals(miembro) && entry.getValue() == RolComunidad.ADMINISTRADOR);
  }

  public void agregarServicio(Servicio servicio, Establecimiento establecimiento) {
    establecimiento.agregarServicio(servicio);
  }

  public void agregarServiciosParaAPI() {
    incidentesAbiertos.forEach(unIncidente -> servicios.add(unIncidente.getServicio()));
  }

  public void agregarEstablecimientosParaAPI() {
    incidentesAbiertos.forEach(unIncidente -> establecimientos.add(unIncidente.getEstablecimiento()));
  }

  public void agregarIncidente(Incidente unIncidente, Miembro miembroQueAbrio) {
    Map<Miembro, RolComunidad> miembrosInteresados = miembrosNuestro.entrySet().stream()
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
    Map<Miembro, RolComunidad> listaSinElMiembro = miembrosNuestro.entrySet().stream()
            .filter(entry -> !entry.getKey().equals(miembroQueCerro))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    NotificarCierreAMiembros notificador = new NotificarCierreAMiembros();
    notificador.notificar(unIncidente, listaSinElMiembro);
  }

}

