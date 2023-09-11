package domain.comunidad;


import domain.Persistencia.EntidadPersistente;
import domain.Persistencia.FormaNotificacionConverter;
import domain.Persistencia.LocalTimeConverter;
import domain.Persistencia.MedioNotificacionConverter;
import domain.geolocalizacion.GestorGeolocalizacion;
import domain.notificaciones.formaDeNotificacion.FormaNotificacion;
import domain.notificaciones.medioDeNotificaciones.MedioNotificacion;
import domain.servicios.*;
import domain.services.geoRef.entidades.Localidad;
import domain.services.geoRef.entidades.Municipio;
import domain.services.geoRef.entidades.Provincia;
import domain.servicios.Incidente;
import domain.generadorRankings.GeneradorRanking;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Getter
@Entity
@Table
public class Miembro extends EntidadPersistente {
  @Column
  private String nombre;

  @Column
  private String apellido;

  @Column
  private String correoElectronico;

  @Column
  private String telefono;

  @ManyToMany
  private List<Comunidad> comunidadesPertenecientes;

  @ManyToMany
  private List<Entidad> entidadesAsociadas;

  @ElementCollection
  @Enumerated(EnumType.STRING)
  @CollectionTable(name = "servicios_miembro", joinColumns = @JoinColumn(name = "miembro_id",referencedColumnName = "id"))
  private List<TipoDeServicio> serviciosAsociados;

  @Embedded
  private Provincia localizacionProvincia;

  @Embedded
  private Municipio localizacionMunicipio;

  @Embedded
  private Localidad localizacionDepartamento;

  @Convert(converter = MedioNotificacionConverter.class)
  @Column(columnDefinition = "VARCHAR(20)")
  private MedioNotificacion medioDeNotificacion;

  @Convert(converter = FormaNotificacionConverter.class)
  @Column(columnDefinition = "VARCHAR(20)")
  private FormaNotificacion formaNotificacion;

  @Embedded
  private Ubicacion ubicacionActual;

  @Transient
  private List<Incidente> incidentesDeInteresPropio;

  @ElementCollection
  @CollectionTable(name = "roles_servicios", joinColumns = @JoinColumn(name = "entity_id"))
  @MapKeyEnumerated(EnumType.STRING) // Anotación para el tipo de clave (EnumType.STRING)
  @MapKeyColumn(name = "tipoServicio")
  @Column(name = "rol")
  private Map<TipoDeServicio, Rol> rolesServicios = new HashMap<>();




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
    comunidadesPertenecientes.forEach(unaComunidad -> incidentesAbiertosDeLasComunidades.addAll(unaComunidad.getIncidentesAbiertos()));

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
        rolesServicios.put(clave, Rol.AFECTADO);
      } else {
        rolesServicios.put(clave, Rol.OBSERVADOR);
      }
    }
  }

  public void cambiarRolManualmente(TipoDeServicio tipoDeServicio, Rol rol) {
    rolesServicios.put(tipoDeServicio, rol);
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
