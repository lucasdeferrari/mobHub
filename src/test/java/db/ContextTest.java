package db;


import domain.comunidad.Comunidad;
import domain.comunidad.ComunidadRepository;
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
    withTransaction(() -> {
    });
  }

  @Test
  void cualquierNombre() {
    ComunidadRepository comunidad = new ComunidadRepository();

    Comunidad comunidad1 = new Comunidad();
    comunidad1.setNombre("gonzalo69");
    
    comunidad.insert(comunidad1);
  }

}
