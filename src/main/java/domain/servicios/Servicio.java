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

  public void denegar() {
    setEstado(Estado.DENEGADO);
  }

  public void disponible() {setEstado(Estado.DISPONIBLE);}


}
