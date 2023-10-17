package db;


import domain.Repositorios.Comunidad.RepositorioComunidad;
import domain.Repositorios.Incidente.RepositorioIncidente;
import domain.Repositorios.Miembro.RepositorioMiembro;
import domain.Repositorios.Usuario.RepositorioDeUsuarios;
import domain.entidades.comunidad.Comunidad;
import domain.entidades.comunidad.Miembro;
import domain.entidades.comunidad.RolComunidad;
import domain.entidades.notificaciones.formaDeNotificacion.AlertarSinApuro;
import domain.entidades.notificaciones.medioDeNotificaciones.AdapterWhatsApp;
import domain.entidades.servicios.Entidad;
import domain.entidades.servicios.Establecimiento;
import domain.entidades.servicios.Incidente;
import domain.entidades.servicios.Servicio;
import domain.entidades.signin.Usuario;
import io.github.flbulgarelli.jpa.extras.test.SimplePersistenceTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ContextTest implements SimplePersistenceTest {

  @Test
  void contextUp() {
    assertNotNull(entityManager());
  }

  @Test
  void contextUpWithTransaction() throws Exception {
    AlertarSinApuro alertarSinApuro = new AlertarSinApuro();

    AdapterWhatsApp adapterWhatsApp = new AdapterWhatsApp();

    Miembro miembro1 = new Miembro();
    miembro1.setNombre("lucas");
    miembro1.setFormaNotificacion(alertarSinApuro);
    miembro1.setMedioDeNotificacion(adapterWhatsApp);

    Miembro miembro2 = new Miembro();
    miembro2.setNombre("admin");
    miembro2.setFormaNotificacion(alertarSinApuro);
    miembro2.setMedioDeNotificacion(adapterWhatsApp);

    Comunidad comunidad1 = new Comunidad();
    comunidad1.setNombre("comunidad 1");
    comunidad1.setDescripcion("descripcion comunidad 1");
    comunidad1.agregarMiembro(miembro1, RolComunidad.AFECTADO);
    comunidad1.agregarMiembro(miembro2, RolComunidad.ADMINISTRADOR);

    Usuario usuario1 = new Usuario();
    usuario1.setNombreUsuario("nombreUsuario");
    usuario1.setNombre("nombre");
    usuario1.setApellido("apellido");
    usuario1.setEmail("email@gmail.com");
    usuario1.setContrasenia("contrasenia");

    Establecimiento establecimiento1 = new Establecimiento();
    Entidad entidad1 = new Entidad();

    Servicio servicio1 = new Servicio();


    RepositorioComunidad repositorioComunidad = new RepositorioComunidad();
    RepositorioMiembro repositorioMiembro = new RepositorioMiembro();
    RepositorioDeUsuarios repositorioDeUsuarios = new RepositorioDeUsuarios();

    withTransaction(() -> {
      Miembro miembroGuardado = repositorioMiembro.guardar(miembro1);
      Miembro miembroGuardado2 = repositorioMiembro.guardar(miembro2);

      Usuario usuarioGuardado = repositorioDeUsuarios.guardar(usuario1);

      Comunidad comunidadGuardada1 = repositorioComunidad.guardar(comunidad1);

    });
  }
}