package domain.servicios;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Servicio {
  private TipoDeServicio nombre;
  private Estado estado;
  private String descripcion;

  public boolean estaDenegado() {
    return estado == Estado.DENEGADO;
  }
}
