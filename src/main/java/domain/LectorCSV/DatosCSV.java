package domain.LectorCSV;

import lombok.Getter;

@Getter
public class DatosCSV {
    private String nombre;
    private String apellido;
    private String contacto;
    private String nombreOrganismo;


    public DatosCSV(String nombre, String apellido,String contacto, String nombreOrganismo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.contacto = contacto;
        this.nombreOrganismo = nombreOrganismo;
    }
}
