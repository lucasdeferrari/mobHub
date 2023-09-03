package domain.comunidad;

import domain.generadorRankings.GeneradorRanking;
import domain.servicios.Entidad;
import domain.servicios.Establecimiento;
import domain.servicios.Incidente;
import domain.servicios.Servicio;
import java.time.LocalDateTime;
import java.util.List;

// TODO HACERLO SINGLETON
// TODOS LOS STATIC soy luquitas y soy gay
public class FactoryIncidente {

    public static FactoryIncidente instance;

    private FactoryIncidente() {
    }

    public static FactoryIncidente getInstance() {
        if (instance == null) {
            instance = new FactoryIncidente();
        }
        return instance;
    }

    public void crearIncidente(List<Comunidad> comunidades, Miembro miembro, Servicio servicio, Establecimiento establecimiento, Entidad entidad, String descripcion) {
        for (Comunidad comunidad : comunidades) {
            Incidente incidente = new Incidente(
                    comunidad,
                    miembro,
                    servicio,
                    establecimiento,
                    entidad,
                    descripcion,
                    LocalDateTime.now(),
                    LocalDateTime.now()
            );
            comunidad.agregarIncidente(incidente, miembro);
            //GeneradorRanking.agregarIncidente(incidente);
        }
    }
}
