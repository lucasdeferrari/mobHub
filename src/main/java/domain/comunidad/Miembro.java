package domain.comunidad;


import domain.servicios.*;


import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import static domain.servicios.Estado.DENEGADO;

public class Miembro {
  private String nombre;
  private String apellido;
  private String correoElectronico;
  private List<Comunidad> comunidadesPertenecientes;
  private List<Entidad> entidadesAsociadas;
  private List<TipoDeServicio> serviciosAsociados;
  private Localizacion localizacion;

  public Boolean esAdminEn(Comunidad comunidad) {
    return comunidad.esAdmin(this);
  }

  public List<Servicio> serviciosDeInteres() {

    // Hay que filtrar todas las entidades por las que le interesan al usuario. Luego, dentro de esas
    // entidades ascociadas hay que filtrar por los servicios asociados. Por ultimo, hay que filtrar
    // los servicios que tienen problemas dentro de los que le interesan.

    List<Servicio> serviciosConProblemas= new ArrayList<>();

    for (Entidad en : entidadesAsociadas) {
      for(Establecimiento es : en.getEstablecimientos()){
        for(Servicio s : es.getServicios()) {
          if (serviciosAsociados.contains(s.getNombre()) && s.estaDenegado())
            serviciosConProblemas.add(s);
        }
      }
    }
    return serviciosConProblemas;

    //entidadesAsociadas.stream().forEach(unaEntidad -> unaEntidad.conseguirServiciosConProblemasDe(serviciosAsociados));


   // return serviciosDeInteres();
    // interseccion


  }

}

