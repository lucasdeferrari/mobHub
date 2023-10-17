package domain.entidades.comunidad;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import domain.Persistencia.EntidadPersistente;
import domain.entidades.notificaciones.NotificarAperturaAMiembros;
import domain.entidades.notificaciones.NotificarCierreAMiembros;
import domain.entidades.servicios.Establecimiento;
import domain.entidades.servicios.Incidente;
import domain.entidades.servicios.Servicio;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Comunidad extends EntidadPersistente {

  @Column
  @Setter
  @JsonProperty("nombre")
  private String nombre;

  @Column
  private String descripcion;

  @ElementCollection
  @CollectionTable(name = "comunidad_miembros", joinColumns = @JoinColumn(name = "comunidad_id"))
  @MapKeyJoinColumn(name = "miembro_id") // La clave del mapa será un Miembro
  @Enumerated(EnumType.STRING) // El valor del mapa será un RolComunidad
  @Column(name = "rol_comunidad")
  private Map<Miembro, RolComunidad> miembrosNuestro;


  @OneToMany(mappedBy = "comunidad")
  private List<Incidente> incidentesAbiertos;

  @Transient
  @JsonProperty("establecimientos")
  private List<Establecimiento> establecimientos;

  @Transient
  @JsonProperty("miembros")
  private List<Miembro> miembros;

  @Transient
  @JsonProperty("servicios")
  private List<Servicio> servicios;

  @Transient
  @JsonProperty("gradoDeConfianza")
  private Float gradoDeConfianza;

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
    System.out.println("antes del coso");
    incidentesAbiertos.forEach(unIncidente1 -> System.out.println(unIncidente1.getNombre()));

    Map<Miembro, RolComunidad> miembrosInteresados = miembrosNuestro.entrySet().stream()
            .filter(entry -> entry.getKey().leInteresaElIncidente(unIncidente))
            .collect(Collectors.toMap(
                    Map.Entry::getKey,   // Función para mapear a claves (en este caso, el Miembro)
                    Map.Entry::getValue  // Función para mapear a valores (en este caso, el Rol)
            ));    incidentesAbiertos.add(unIncidente);

    System.out.println("despues del coso");
    incidentesAbiertos.forEach(unIncidente1 -> System.out.println(unIncidente1.getNombre()));

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

  public Comunidad() {
    miembros = new ArrayList<>();
    servicios = new ArrayList<>();
    establecimientos = new ArrayList<>();
    incidentesAbiertos = new ArrayList<>();
    miembrosNuestro = new HashMap<>();
  }

}

