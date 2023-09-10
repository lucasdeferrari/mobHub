package domain.servicios;

import domain.Persistencia.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;


@Entity
@Table
public class OrganismoDeControl extends EntidadPersistente {
    @Column
    private String nombre;
    @Transient
    private List<EntidadPrestadora> entidadadesPrestadoras;
    @Transient
    private List<TipoDeServicio> serviciosAControlar;

    //TODO

    public void eliminarEntidadPrestadora(EntidadPrestadora entidad) {
        entidadadesPrestadoras.remove(entidad);
    }
    public void agregarEntidadPrestadora(EntidadPrestadora entidad) {
        entidadadesPrestadoras.add(entidad);
    }
    public void obtenerInforme(EntidadPrestadora prestadora){
        prestadora.obtenerInforme();
    }
}
