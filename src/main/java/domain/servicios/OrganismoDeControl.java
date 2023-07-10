package domain.servicios;

import domain.Rankings.GeneradorRanking;
import domain.Rankings.Ranking;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public class OrganismoDeControl {

    private String nombre;
    private List<EntidadPrestadora> entidadadesPrestadoras;
    private List<TipoDeServicio> serviciosAControlar;

    public void eliminarEntidadPrestadora(EntidadPrestadora entidad) {
        entidadadesPrestadoras.remove(entidad);
    }
    public void agregarEntidadPrestadora(EntidadPrestadora entidad) {
        entidadadesPrestadoras.add(entidad);
    }
    public Workbook obtenerInforme(EntidadPrestadora prestadora){
        return prestadora.obtenerInforme();
    }
}
