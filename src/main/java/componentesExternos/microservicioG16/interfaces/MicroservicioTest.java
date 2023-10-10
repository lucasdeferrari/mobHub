package componentesExternos.microservicioG16.interfaces;

import domain.entidades.comunidad.Comunidad;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class MicroservicioTest {

    @Test
    public void mainTest() {

        MicroservicioG16 fusionComunidades = new MicroservicioG16();
        Comunidad comunidad = new Comunidad();
        comunidad.setNombre("comunidad1");

        Comunidad comunidad2 = new Comunidad();
        comunidad2.setNombre("comunidad2");

        List<Comunidad> comunidades = new ArrayList<>();
        comunidades.add(comunidad);
        comunidades.add(comunidad2);



        List<Comunidad> comunidadesRetornadas = fusionComunidades.sendCommunitiesToApi(comunidad, comunidades);

        if (comunidadesRetornadas != null) {
            System.out.println("Comunidades retornadas por el servidor:");
            for (Comunidad c : comunidadesRetornadas) {
                System.out.println(c.getNombre());
            }
        }

    }

}
