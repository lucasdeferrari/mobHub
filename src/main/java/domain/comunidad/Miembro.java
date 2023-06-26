package domain.comunidad;


import domain.servicios.*;
import domain.services.geoRef.entidades.Departamento;
import domain.services.geoRef.entidades.Municipio;
import domain.services.geoRef.entidades.Provincia;

import java.io.IOException;
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
    Incidente incidente = new Incidente(this.comunidadesPertenecientes, this, servicio, establecimiento, entidad, LocalDateTime.now());
    comunidadesPertenecientes.forEach(unaComunidad -> unaComunidad.agregarIncidente(incidente));
    servicio.denegar();
  }

//  public void recibirIncidente(Incidente unIncidente);

}