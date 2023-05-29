package domain.servicios;

import java.util.List;
import java.util.Arrays;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Entidad {
  private String nombre;
  private List<Establecimiento> establecimientos;
  private TipoEntidad tipo;
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


  public Boolean tieneAtributosExtra(TipoEntidad tipo) {
    return ( tipo ==  TipoEntidad.FERROCARRIL || tipo == TipoEntidad.SUBTERRANEO);
  }

  public void instanciarAtributosExtra (Establecimiento estacionOrigen, Establecimiento estacionDestino) {
    if(this.tieneAtributosExtra(this.tipo)) {
      atributosExtra.add(estacionOrigen);
      atributosExtra.add(estacionDestino);}
else throw new Exception(message= "No se puede agregar una estacion de destino y origen ya que la entidad no es un medio de transporte");}

  public void agregarServiciosConProblemasA(List<Servicio> serviciosConProblemas) {

  }
}


