package domain.Repositorios.Miembro;

import domain.entidades.comunidad.Miembro;


import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioMiembro implements MiembroCRUD {

    @Override
    public Miembro guardar(Miembro miembro) {
        EntityTransaction tx = entityManager().getTransaction();
        if(!tx.isActive())
            tx.begin();

        entityManager().persist(miembro);
        tx.commit();

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
