package domain.servicios;

import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ServicioPublico {
  private List<Linea> lineas;
  private Transporte tipoTransporte;


  public void agregarLinea(Linea linea){

  }

  public void eliminarLinea(Linea linea) {
    lineas.remove(linea);
  }

}
