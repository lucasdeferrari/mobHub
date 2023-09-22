package db;


import domain.Repositorios.Comunidad.RepositorioComunidad;
import domain.Repositorios.Miembro.RepositorioMiembro;
import domain.comunidad.Comunidad;
import domain.comunidad.Miembro;
import domain.comunidad.RolComunidad;
import domain.notificaciones.formaDeNotificacion.AlertarSinApuro;
import domain.notificaciones.medioDeNotificaciones.AdapterWhatsApp;
import io.github.flbulgarelli.jpa.extras.test.SimplePersistenceTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ContextTest implements SimplePersistenceTest {

  @Test
  void contextUp() {
    assertNotNull(entityManager());
  }

  @Test
  void contextUpWithTransaction() throws Exception {
    Comunidad comunidad3 = new Comunidad();
    comunidad3.setNombre("norberto3");
    comunidad3.setDescripcion("Descripción de la Comunidad 3");

    Comunidad comunidad4 = new Comunidad();
    comunidad4.setNombre("comunidad4");
    comunidad4.setDescripcion("Descripción de la Comunidad 4");

    AlertarSinApuro alertarSinApuro = new AlertarSinApuro();

    AdapterWhatsApp adapterWhatsApp = new AdapterWhatsApp();

    Miembro miembro1 = new Miembro();
    miembro1.setNombre("lucas");
    miembro1.setFormaNotificacion(alertarSinApuro);
    miembro1.setMedioDeNotificacion(adapterWhatsApp);

    comunidad3.agregarMiembro(miembro1, RolComunidad.AFECTADO);

    RepositorioComunidad repositorioComunidad = new RepositorioComunidad();
    RepositorioMiembro repositorioMiembro = new RepositorioMiembro();

    withTransaction(() -> {
      Comunidad comunidadGuardada3 = repositorioComunidad.guardar(comunidad3);
      Comunidad comunidadGuardada4 = repositorioComunidad.guardar(comunidad4);

      Miembro miembroGuardado = repositorioMiembro.guardar(miembro1);

    });
  }
}
