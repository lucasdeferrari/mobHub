package domain.servicios;

import lombok.Getter;

@Getter
public class Ubicacion {
  private Float latitud;
  private Float longitud;

  public Ubicacion(Float latitud, Float longitud) {
    this.latitud = latitud;
    this.longitud = longitud;
  }
}
