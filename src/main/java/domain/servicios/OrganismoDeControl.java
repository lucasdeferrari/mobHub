package domain.servicios;

import domain.Rankings.GeneradorRanking;
import domain.Rankings.Ranking;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public class OrganismoDeControl {

    private String nombre;
    private List<EntidadPrestadora> entidadadesPrestadoras;
    private List<TipoDeServicio> serviciosAControlar;
    private Ranking criterio;

    public void eliminarEntidadPrestadora(EntidadPrestadora entidad) {
        entidadadesPrestadoras.remove(entidad);
    }
    public void agregarEntidadPrestadora(EntidadPrestadora entidad) {
        entidadadesPrestadoras.add(entidad);
    }
    public List<Entidad> obtenerInforme(){
        return GeneradorRanking.devolverInformeOrganismo(entidadadesPrestadoras, criterio);
    }
}
