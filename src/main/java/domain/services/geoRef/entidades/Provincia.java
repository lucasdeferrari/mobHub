package domain.services.geoRef.entidades;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Provincia {
  public int id;
  public String nombre;

  public int id() {
    return id;
  }
}
