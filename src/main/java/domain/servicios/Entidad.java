package domain.servicios;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Entidad {
  private String nombre;
  public List<Establecimiento> establecimientos;
  private Localizacion localizacion;
  private List<Establecimiento> atributosExtra;


  public void eliminarEstablecimiento(Establecimiento establecimiento) {
    establecimientos.add(establecimiento);
  }

  public void agregarEstablecimiento(Establecimiento establecimiento) {
    establecimientos.add(establecimiento);
  }

  public Boolean establecimientosConProblemas() {
    return establecimientos.stream().anyMatch(unEstablecimiento -> unEstablecimiento.tieneServiciosDenegados());
  }

  public List<Servicio> conseguirServiciosConProblemasDe(List<TipoDeServicio> serviciosAsociados) {
    return establecimientos.stream().flatMap(unEstablecimiento -> unEstablecimiento.filtrarServiciosAsociadosConProblemas(serviciosAsociados))
            .collect(Collectors.toList());
  }
}


