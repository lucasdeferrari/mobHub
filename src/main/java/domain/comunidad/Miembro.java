package domain.comunidad;


import domain.servicios.Entidad;
import domain.servicios.Localizacion;
import domain.servicios.Servicio;

import java.util.List;

public class Miembro {
  private String nombre;
  private String apellido;
  private String correoElectronico;
  private List<Comunidad> comunidadesPertenecientes;
  private List<Entidad> entidadesAsociadas;
  private List<Servicio> serviciosAsociados;
  private Localizacion localizacion;

  public Boolean esAdminEn(Comunidad comunidad) {
    return comunidad.esAdmin(this);
  }

  public List<Servicio> serviciosDeInteres() {

    List<Servicio> serviciosConProblemas= new ArrayList<>();
    //me fijo en las entidadesAsociadas el establecimiento de cada una
    //filtro los establecimientos por la ubicacion y localizacion
    //me fijo los servicios de cada establecimiento elegido por ubicacion, que matcheen los serviciosAsociados
    //filtro los servicios por los que tengan estado denegado

    // return entidadesAsociadas.stream()
    //        .filter(unServicio -> unServicio.tieneIncidente())
    //        .collect(Collectors.toList());

    List<Entidad> entidadesDeInteres = entidadesAsociadas.stream().forEach(unaEntidad -> unaEntidad.agregarServiciosConProblemasA(serviciosConProblemas));


    return entidadesDeInteres.stream().filter();
    // interseccion


  }

}

