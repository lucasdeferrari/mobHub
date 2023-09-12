package db;


import domain.Repositorios.Comunidad.RepositorioComunidad;
import domain.comunidad.Comunidad;
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

    RepositorioComunidad repositorioComunidad = new RepositorioComunidad();

    withTransaction(() -> {
      Comunidad comunidadGuardada3 = repositorioComunidad.guardar(comunidad3);
      Comunidad comunidadGuardada4 = repositorioComunidad.guardar(comunidad4);

    });
  }
}
