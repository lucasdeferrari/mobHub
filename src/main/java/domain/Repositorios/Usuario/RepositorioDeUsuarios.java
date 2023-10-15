package domain.Repositorios.Usuario;

import domain.entidades.signin.Usuario;
import java.util.List;


public class RepositorioDeUsuarios implements UsuarioCRUD {

    public RepositorioDeUsuarios() {}

    @Override
    public Usuario guardar(Usuario usuario) {
        EntityTransaction tx = entityManager().getTransaction();
        if(!tx.isActive())
            tx.begin();

        entityManager().persist(usuario);
        tx.commit();

        return usuario;

    }

    @Override
    public Usuario buscarPorId(int id) {
        return entityManager().find(Usuario.class,id);
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
