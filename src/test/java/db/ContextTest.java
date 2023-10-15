package db;


import domain.Repositorios.Comunidad.RepositorioComunidad;
import domain.Repositorios.Miembro.RepositorioMiembro;
import domain.Repositorios.Usuario.RepositorioDeUsuarios;
import domain.entidades.comunidad.Comunidad;
import domain.entidades.comunidad.Miembro;
import domain.entidades.comunidad.RolComunidad;
import domain.entidades.notificaciones.formaDeNotificacion.AlertarSinApuro;
import domain.entidades.notificaciones.medioDeNotificaciones.AdapterWhatsApp;
import domain.entidades.signin.Usuario;
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
    Usuario usuario1 = new Usuario();
    usuario1.setNombreUsuario("nombre");
    usuario1.setContrasenia("contrasenia");
    usuario1.setEmail("email@gmail.com");
    usuario1.setNombre("julio");
    usuario1.setApellido("perez");


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
    RepositorioDeUsuarios repoUsuarios = new RepositorioDeUsuarios();

    withTransaction(() -> {
      Usuario usuarioGuardado1 = repoUsuarios.guardar(usuario1);

      Comunidad comunidadGuardada3 = repositorioComunidad.guardar(comunidad3);
      Comunidad comunidadGuardada4 = repositorioComunidad.guardar(comunidad4);

      Miembro miembroGuardado = repositorioMiembro.guardar(miembro1);

    });
  }
}
