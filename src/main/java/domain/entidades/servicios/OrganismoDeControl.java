package domain.entidades.servicios;

import domain.Persistencia.EntidadPersistente;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Entity
@Table
public class OrganismoDeControl extends EntidadPersistente {
    @Column
    private String nombre;

    @OneToMany(mappedBy = "organismoDeControl", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<EntidadPrestadora> entidadadesPrestadoras;


    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "tipoServicios_organismoDeControl", joinColumns = @JoinColumn(name = "organismoDeControl_id",referencedColumnName = "id"))
    @Column(name = "servicioAControlar", unique = true)
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
