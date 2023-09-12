package domain.Repositorios.Miembro;

import domain.comunidad.Miembro;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class RepositorioMiembro implements MiembroCRUD {

    @Override
    public Miembro guardar(Miembro miembro) {
        entityManager().persist(miembro);
        return miembro;
    }

    @Override
    public Miembro buscarPorId(Long id) {
        return entityManager().find(Miembro.class, id);
    }

    @Override
    public List<Miembro> buscarTodos() {
        return entityManager().createQuery("SELECT e FROM Entidad e", Miembro.class)
                .getResultList();
    }

    @Override
    public void actualizar(Miembro miembro) {
        entityManager().merge(miembro);
    }

    @Override
    public void eliminar(Long id) {
        Miembro miembro = entityManager().find(Miembro.class, id);
        if (miembro != null) {
            entityManager().remove(miembro);
        }
    }
}
