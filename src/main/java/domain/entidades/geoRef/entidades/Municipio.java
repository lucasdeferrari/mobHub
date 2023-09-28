package domain.entidades.geoRef.entidades;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Municipio {
  @Transient
  public int id;
  @Column(name="municipio")
  public String nombre;
}

// ESTAS COSAS DEBERIAN SER ENTIDADES ?? TODO
