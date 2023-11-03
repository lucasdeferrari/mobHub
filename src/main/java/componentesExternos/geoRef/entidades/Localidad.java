package componentesExternos.geoRef.entidades;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Localidad {
    @Transient
    public Long id;
    @Column(name = "localidad")
    public String nombre;
}
