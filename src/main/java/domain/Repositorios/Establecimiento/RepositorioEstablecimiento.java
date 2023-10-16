package domain.Repositorios.Establecimiento;

import domain.entidades.servicios.Establecimiento;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioEstablecimiento implements EstablecimientoCRUD {

    @Override
    public Establecimiento guardar(Establecimiento establecimiento) {
        EntityTransaction tx = entityManager().getTransaction();
        if(!tx.isActive())
            tx.begin();

        entityManager().persist(establecimiento);
        tx.commit();

        return establecimiento;
    }

    @Override
    public Establecimiento buscarPorId(Long id) {
        return entityManager().find(Establecimiento.class, id);
    }

    @Override
    public List<Establecimiento> buscarTodos() {
        return entityManager().createQuery("SELECT e FROM Establecimiento e", Establecimiento.class)
                .getResultList();
    }

    @Override
    public void actualizar(Establecimiento establecimiento) {
        entityManager().merge(establecimiento);
    }

    @Override
    public void eliminar(Long id) {
        Establecimiento establecimiento = entityManager().find(Establecimiento.class, id);
        if (establecimiento != null) {
            entityManager().remove(establecimiento);
        }
    }
}
