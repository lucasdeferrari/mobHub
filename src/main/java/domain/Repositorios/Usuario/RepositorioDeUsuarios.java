package domain.Repositorios.Usuario;

import domain.entidades.signin.Usuario;
import java.util.List;


public class RepositorioDeUsuarios implements UsuarioCRUD {

    public RepositorioDeUsuarios() {}

    @Override
    public Usuario guardar(Usuario usuario) {
        entityManager().persist(usuario);
        return usuario;
    }

    @Override
    public Usuario buscarPorNombre(String nombreUsuario) {
        return entityManager().find(Usuario.class,nombreUsuario);
    }

    @Override
    public List<Usuario> buscarTodos() {
        return entityManager().createQuery("SELECT e FROM Entidad e", Usuario.class)
                .getResultList();
    }

    @Override
    public void actualizar(Usuario usuario) {
        entityManager().merge(usuario);
    }

    @Override
    public void eliminar(String nombreUsuario) {
        Usuario usuario = entityManager().find(Usuario.class, nombreUsuario);
        if (usuario != null) {
            entityManager().remove(usuario);
        }
    }
}
