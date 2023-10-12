package domain.Repositorios.Usuario;

import domain.entidades.comunidad.Miembro;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import domain.entidades.signin.Usuario;
import java.util.List;

public interface UsuarioCRUD extends WithSimplePersistenceUnit {

        Usuario guardar(Usuario usuario);
        Usuario buscarPorNombre(String nombreUsuario);
        List<Usuario> buscarTodos();
        void actualizar(Usuario usuario);
        void eliminar(String nombreUsuario);
}
