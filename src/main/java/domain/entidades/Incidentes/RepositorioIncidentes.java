package domain.entidades.Incidentes;

import domain.entidades.comunidad.Comunidad;
import domain.entidades.comunidad.Miembro;
import domain.entidades.servicios.Incidente;

import java.time.LocalDateTime;
import java.util.List;


public class RepositorioIncidentes {
    public List<Comunidad> comunidades;
    public List<Incidente> incidentes;


    public List<Incidente> incidentesDe(Comunidad comunidad) {
        return incidentes;      //OBVIAMENTE ASI NO ES COMO FUNCIONARIA, ES SOLO PARA DAR
                                // UNA IDEA, ESTE METODO DEVUELVE LOS INCIDENTES DE LA COMUNIDAD.
    }

    public void agregarIncidente(Incidente incidente, List<Comunidad> comunidades) {

    }

    public void cerrarIncidente(Incidente incidente, List<Comunidad> comunidades, Miembro quienCerro, LocalDateTime fechaHoraCierre) {
        incidente.setQuienCerro(quienCerro);
        incidente.setFechaHoraCierre(fechaHoraCierre);
    }


}