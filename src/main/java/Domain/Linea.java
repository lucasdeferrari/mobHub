package Domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Linea {
  private String nombre;
  private Estacion estacionDeOrigen;
  private Estacion estacionDeDestino;
  private List<Estacion> conjuntoDeEstaciones;
  private Estado estado;

  public void eliminarEstacion(Estacion estacion) {
    conjuntoDeEstaciones.add(estacion);
  }

  public void agregarEstacion(Estacion estacion) {
    conjuntoDeEstaciones.add(estacion);
  }

}
