package domain.servicios;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Servicio {
  private TipoDeServicio nombre;
  private Estado estado;
  private String descripcion;

  public Servicio(TipoDeServicio nombre, Estado estado, String descripcion) {
    this.nombre = nombre;
    this.estado = estado;
    this.descripcion = descripcion;
  }

  public boolean estaDenegado() {
    return estado == Estado.DENEGADO;
  }

  public boolean esDeInteres(List<TipoDeServicio> serviciosAsociados) {
    List<Boolean> chequeo = new ArrayList<>();
    int i = 0;

    for (TipoDeServicio servicioAsociado : serviciosAsociados) {
      chequeo.set(i, servicioAsociado == nombre);
      i++;
    }

    for (boolean elemento : chequeo) {
      if (elemento) {
        return true;
      }
    }
    return false;
  }
}
