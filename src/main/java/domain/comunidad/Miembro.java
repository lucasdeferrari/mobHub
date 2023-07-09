package domain.comunidad;


import domain.notificaciones.formaDeNotificacion.FormaNotificacion;
import domain.notificaciones.tipoDeNotificacion.Notificacion;
import domain.servicios.*;
import domain.services.geoRef.entidades.Departamento;
import domain.services.geoRef.entidades.Municipio;
import domain.services.geoRef.entidades.Provincia;
import domain.servicios.Incidente;

import java.time.LocalDateTime;
import java.util.*;

public class Miembro {
  private String nombre;
  private String apellido;
  private String correoElectronico;
  private List<Comunidad> comunidadesPertenecientes;
  public List<Entidad> entidadesAsociadas;
  private List<TipoDeServicio> serviciosAsociados;
  private Provincia localizacionProvincia;
  private Municipio localizacionMunicipio;
  private Departamento localizacionDepartamento;
  private FormaNotificacion formaNotificacion;


  public Miembro(String nombre, String apellido, String correoElectronico) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.correoElectronico = correoElectronico;
    //this.comunidadesPertenecientes = comunidadesPertenecientes; // cambiar
    //this.entidadesAsociadas = entidadesAsociadas;// cambiar
    //this.serviciosAsociados = serviciosAsociados;// cambiar
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


  public void informarIncidente(Establecimiento establecimiento, Entidad entidad, Servicio servicio) {
    for (Comunidad comunidad : comunidadesPertenecientes) {
      if (!comunidad.existeIncidenteReportado(servicio)) {
        Incidente incidente = new Incidente( //todo builder
                comunidadesPertenecientes,
                this,
                servicio,
                establecimiento,
                entidad,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        comunidad.agregarIncidente(incidente);
      }
      servicio.denegar();
    }
  }

  public void cerrarIncidente(Incidente incidente) {
    incidente.setQuienCerro(this);
    incidente.setFechaHoraCierre(LocalDateTime.now());
    comunidadesPertenecientes.forEach(unaComunidad -> unaComunidad.cerrarIncidente(incidente));
    incidente.ponerDisponible();
  }
  public void recibirNotificacion(Notificacion unaNotificacion) {
    formaNotificacion.notificar(unaNotificacion);
  };

}