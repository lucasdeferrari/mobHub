package domain.servicios;

import domain.Persistencia.EntidadPersistente;

import javax.persistence.*;
import java.util.List;


@Entity
@Table
public class OrganismoDeControl extends EntidadPersistente {
    @Column
    private String nombre;

    @OneToMany
    private List<EntidadPrestadora> entidadadesPrestadoras;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "servicios_organismoDeControl", joinColumns = @JoinColumn(name = "organismoDeControl_id",referencedColumnName = "id"))
    private List<TipoDeServicio> serviciosAControlar;
/*
    @ElementCollection(targetClass = EstadoPedido.class)
    @Enumerated(EnumType.STRING) // Puedes usar EnumType.ORDINAL si prefieres números enteros
    @CollectionTable(name = "estados_pedido", joinColumns = @JoinColumn(name = "pedido_id"))
    private List<EstadoPedido> estados;
 */
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
