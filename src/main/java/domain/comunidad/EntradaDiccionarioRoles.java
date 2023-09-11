package domain.comunidad;

import domain.Persistencia.EntidadPersistente;
import domain.servicios.TipoDeServicio;

import javax.persistence.*;

@Entity
public class EntradaDiccionarioRoles extends EntidadPersistente {
    @Enumerated(EnumType.STRING)
    private TipoDeServicio tipoServicio;
    @Enumerated(EnumType.STRING)
    private Rol rol;


}
