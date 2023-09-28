package domain.entidades.comunidad;


import domain.Persistencia.EntidadPersistente;
import domain.Persistencia.FormaNotificacionConverter;
import domain.Persistencia.LocalTimeConverter;
import domain.Persistencia.MedioNotificacionConverter;
import domain.entidades.geoRef.entidades.Localidad;
import domain.entidades.geoRef.entidades.Municipio;
import domain.entidades.geoRef.entidades.Provincia;
import domain.entidades.geolocalizacion.GestorGeolocalizacion;
import domain.entidades.notificaciones.formaDeNotificacion.FormaNotificacion;
import domain.entidades.notificaciones.medioDeNotificaciones.MedioNotificacion;
import domain.entidades.servicios.*;
import domain.entidades.servicios.Incidente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Getter
@Entity
@Table
public class Miembro extends EntidadPersistente {
  @Column
  @Setter
  private String nombre;

  @Column
  private String apellido;

  @Column
  private String correoElectronico;

  @Column
  private String telefono;

  @Transient
  @Getter
  private Map<Comunidad, RolComunidad> comunidadesPertenecientes = new HashMap<>();

  @ManyToMany
  private List<Entidad> entidadesAsociadas;

  @ElementCollection
  @Enumerated(EnumType.STRING)
  @CollectionTable(name = "servicios_miembro", joinColumns = @JoinColumn(name = "miembro_id",referencedColumnName = "id"))
  @Column(unique = true)
  private List<TipoDeServicio> serviciosAsociados;

  @Embedded
  private Provincia localizacionProvincia;

  @Embedded
  private Municipio localizacionMunicipio;

  @Embedded
  private Localidad localizacionDepartamento;

  @Convert(converter = MedioNotificacionConverter.class)
  @Column(columnDefinition = "VARCHAR(20)")
  @Setter
  private MedioNotificacion medioDeNotificacion;

  @Convert(converter = FormaNotificacionConverter.class)
  @Column(columnDefinition = "VARCHAR(20)")
  @Setter
  private FormaNotificacion formaNotificacion;

  @Embedded
  private Ubicacion ubicacionActual;

  @Transient
  private List<Incidente> incidentesDeInteresPropio;

  @Transient
  private Map<TipoDeServicio, RolServicio> rolesServicios = new HashMap<>();




  //private List<Rol_Servicio> rolXServicio = new ArrayList<>();

  @Convert(converter = LocalTimeConverter.class)
  @Column(columnDefinition = "TIME")
  private LocalTime horarioElegido;

  public Miembro() {

  }

  // hacer bien los OneToOne y el ManyToMany todo
  // pensar si estan bien todas las cosas que estan transient todo

  //private Rol rol; // se puede cambiar entre roles con el setter

  public void setUbicacionActual(Ubicacion ubicacionActual) {
    this.ubicacionActual = ubicacionActual;
    List<Incidente> incidentesAbiertosDeLasComunidades = new ArrayList<>();
    comunidadesPertenecientes.forEach((unaComunidad, unRol) -> incidentesAbiertosDeLasComunidades.addAll(unaComunidad.getIncidentesAbiertos()));

    GestorGeolocalizacion gestorGeolocalizacion = new GestorGeolocalizacion();
    gestorGeolocalizacion.incidentesCercaDelMiembro(Miembro.this, incidentesAbiertosDeLasComunidades);
  }

  public Miembro(String nombre, String apellido, String correoElectronico, String telefono) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.correoElectronico = correoElectronico;
    this.telefono = telefono;
    //this.comunidadesPertenecientes = comunidadesPertenecientes; // cambiar
    //this.entidadesAsociadas = entidadesAsociadas;// cambiar
    //this.serviciosAsociados = serviciosAsociados;// cambiar
  }

  // SE LLAMA A ESTA FUNCION LUEGO DE CREAR UN MIEMBRO.
  public void definirRoles() {

    for (TipoDeServicio clave : rolesServicios.keySet()) {
      if (serviciosAsociados.contains(clave)) {
        rolesServicios.put(clave, RolServicio.AFECTADO);
      } else {
        rolesServicios.put(clave, RolServicio.OBSERVADOR);
      }
    }
  }

  public void cambiarRolManualmente(TipoDeServicio tipoDeServicio, RolServicio rolServicio) {
    rolesServicios.put(tipoDeServicio, rolServicio);
  }

  public Boolean esAdminEn(Comunidad comunidad) {
    return comunidad.esAdmin(this);
  }

  public List<Servicio> serviciosDeInteres() {

    // Hay que filtrar todas las entidades por las que le interesan al usuario. Luego, dentro de esas
    // entidades ascociadas hay que filtrar por los servicios asociados. Por ultimo, hay que filtrar
    // los servicios que tienen problemas dentro de los que le interesan.

    List<Servicio> serviciosConProblemasConRepetidos = new ArrayList<>();
    List<Servicio> serviciosQueInteresan = new ArrayList<>();
    for (Entidad entidad : entidadesAsociadas) {
      serviciosQueInteresan.addAll(entidad.conseguirServiciosConProblemasDe(serviciosAsociados));// aca
    }

    serviciosConProblemasConRepetidos.addAll(serviciosQueInteresan);
    Set<Servicio> conjunto = new HashSet<>(serviciosConProblemasConRepetidos);
    List<Servicio> serviciosConProblemasSinRepetidos = new ArrayList<>(conjunto);
    return serviciosConProblemasSinRepetidos;
  }

  public void informarIncidente(Establecimiento establecimiento, Entidad entidad, Servicio servicio, String descripcion)
  {
    FactoryIncidente factoryIncidente = FactoryIncidente.getInstance();
    factoryIncidente.crearIncidente(comunidadesPertenecientes, this, servicio, establecimiento, entidad, descripcion);
  }

  public void cerrarIncidente(Incidente incidente, Comunidad comunidad) {
    incidente.setQuienCerro(this);
    incidente.setFechaHoraCierre(LocalDateTime.now());
    comunidad.cerrarIncidente(incidente, this);
  }

  @Transient
  private Timer timer;

  //public void main(String[] args) {
    //ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    // Programa la tarea para que se ejecute en la hora exacta
    //executor.schedule(() -> {
      //timer = new Timer();
      //timer.schedule(task, 0, 30);
    //}, 0, TimeUnit.MINUTES);

    // Cierra el executor después de ejecutar la tarea
   // executor.shutdown();
 // }

  public Boolean leInteresaElIncidente(Incidente unIncidente) {
    List<Servicio> serviciosDeInteres = this.serviciosDeInteres();

    return serviciosDeInteres.contains(unIncidente.getServicio());
  }

  public Boolean esLaHora() {
    return LocalTime.now() == horarioElegido;
  }

 // TimerTask task = new TimerTask() {
  //  public void run() {
   //   List<Incidente> incidentesAbiertosDeLasComunidades = new ArrayList<>();
  //    comunidadesPertenecientes.forEach(unaComunidad -> incidentesAbiertosDeLasComunidades.addAll(unaComunidad.getIncidentesAbiertos()));

   //   GestorGeolocalizacion.incidentesCercaDelMiembro(Miembro.this, incidentesAbiertosDeLasComunidades);
  //  }
 // };


}
