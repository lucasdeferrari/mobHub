package domain.comunidad;


import domain.servicios.*;


import java.util.*;
import java.util.stream.Collectors;

import static domain.servicios.Estado.DENEGADO;

public class Miembro {
  private String nombre;
  private String apellido;
  private String correoElectronico;
  private List<Comunidad> comunidadesPertenecientes;
  public List<Entidad> entidadesAsociadas;
  private List<TipoDeServicio> serviciosAsociados;
  private Localizacion localizacion;

  public Miembro(String nombre, String apellido, String correoElectronico, List<Comunidad> comunidadesPertenecientes, List<Entidad> entidadesAsociadas, List<TipoDeServicio> serviciosAsociados, Localizacion localizacion) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.correoElectronico = correoElectronico;
    this.comunidadesPertenecientes = comunidadesPertenecientes;
    this.entidadesAsociadas = entidadesAsociadas;
    this.serviciosAsociados = serviciosAsociados;
    this.localizacion = localizacion;
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
    List<Servicio> serviciosConProblemasSinRepetidos = new ArrayList<> (conjunto);
    return serviciosConProblemasSinRepetidos;

//    for (Entidad entidad : entidadesAsociadas) {
//      for (Establecimiento establecimiento : entidad.getEstablecimientos()) {
//        for (Servicio servicio : establecimiento.getServicios()) {
//          if (serviciosAsociados.contains(servicio.getNombre()) && servicio.estaDenegado())
//            serviciosConProblemas.add(servicio);
//        }
//      }
//    }

    //entidadesAsociadas.stream().forEach(unaEntidad -> unaEntidad.conseguirServiciosConProblemasDe(serviciosAsociados));


    // return serviciosDeInteres();
    // interseccion


  }

}

