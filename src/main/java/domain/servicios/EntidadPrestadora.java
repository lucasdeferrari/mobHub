package domain.servicios;

import java.util.List;
import lombok.Getter;

@Getter
public class EntidadPrestadora {

    private String nombre;
    private List<Entidad> entidades;



    public void eliminarEntidad(Entidad entidad) {
        entidades.remove(entidad);
    }

    public void agregarEntidad(Entidad entidad) {
        entidades.add(entidad);
    }


}
