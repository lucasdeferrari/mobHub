package domain.comunidad;


import domain.Persistencia.EntidadPersistente;
import domain.geolocalizacion.GestorGeolocalizacion;
import domain.notificaciones.formaDeNotificacion.FormaNotificacion;
import domain.notificaciones.medioDeNotificaciones.MedioNotificacion;
import domain.servicios.*;
import domain.services.geoRef.entidades.Departamento;
import domain.services.geoRef.entidades.Municipio;
import domain.services.geoRef.entidades.Provincia;
import domain.servicios.Incidente;
import domain.generadorRankings.GeneradorRanking;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
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
  @Transient
  private List<Entidad> entidadesAsociadas;
  @Transient
  private List<TipoDeServicio> serviciosAsociados;
  @OneToOne
  private Provincia localizacionProvincia;
  @OneToOne
  private Municipio localizacionMunicipio;
  @OneToOne
  private Departamento localizacionDepartamento;
  @OneToOne
  private MedioNotificacion medioDeNotificacion;
  @OneToOne
  private FormaNotificacion formaNotificacion;
  @OneToOne
  private Ubicacion ubicacionActual;
  @Transient
  private List<Incidente> incidentesDeInteresPropio;
  @Transient
  private List<Rol> rolesServicios;
  @Transient
  private LocalDateTime horarioElegido;

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

  public Miembro(String nombre, String apellido, String correoElectronico) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.correoElectronico = correoElectronico;
    //this.comunidadesPertenecientes = comunidadesPertenecientes; // cambiar
    //this.entidadesAsociadas = entidadesAsociadas;// cambiar
    //this.serviciosAsociados = serviciosAsociados;// cambiar
  }

  // SE LLAMA A ESTA FUNCION LUEGO DE CREAR UN MIEMBRO.
  public void definirRoles() {
    int indice = 0;

    for (Rol rol : rolesServicios) {
      if (serviciosAsociados.contains(indice)) {
        rolesServicios.set(indice, Rol.AFECTADO);
      } else {
        rolesServicios.set(indice, Rol.OBSERVADOR);
      }
      indice++;
    }
  }

  public void cambiarRolManualmente(TipoDeServicio tipoDeServicio, Rol rol) {
    rolesServicios.set(tipoDeServicio.ordinal(), rol);
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
    return LocalDateTime.now() == horarioElegido;
  }

 // TimerTask task = new TimerTask() {
  //  public void run() {
   //   List<Incidente> incidentesAbiertosDeLasComunidades = new ArrayList<>();
  //    comunidadesPertenecientes.forEach(unaComunidad -> incidentesAbiertosDeLasComunidades.addAll(unaComunidad.getIncidentesAbiertos()));

   //   GestorGeolocalizacion.incidentesCercaDelMiembro(Miembro.this, incidentesAbiertosDeLasComunidades);
  //  }
 // };


}
