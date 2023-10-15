package domain.Repositorios.EntidadPrestadora;

import domain.entidades.servicios.EntidadPrestadora;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioEntidadPrestadora implements EntidadPrestadoraCRUD {

    @Override
    public EntidadPrestadora guardar(EntidadPrestadora entidadPrestadora) {
        EntityTransaction tx = entityManager().getTransaction();
        if(!tx.isActive())
            tx.begin();

        entityManager().persist(entidadPrestadora);
        tx.commit();

        return entidadPrestadora;
    }

    @Override
    public EntidadPrestadora buscarPorId(Long id) {
        return entityManager().find(EntidadPrestadora.class, id);
    }

    @Override
    public List<EntidadPrestadora> buscarTodos() {
        return entityManager().createQuery("SELECT e FROM Entidad e", EntidadPrestadora.class)
                .getResultList();
    }

    @Override
    public void actualizar(EntidadPrestadora entidadPrestadora) {
        entityManager().merge(entidadPrestadora);
    }

    @Override
    public void eliminar(Long id) {
        EntidadPrestadora entidadPrestadora = entityManager().find(EntidadPrestadora.class, id);
        if (entidadPrestadora != null) {
            entityManager().remove(entidadPrestadora);
        }
    }
}
