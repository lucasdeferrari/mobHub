package domain.Repositorios.Comunidad;

import domain.entidades.comunidad.Comunidad;
import domain.entidades.servicios.Incidente;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RepositorioComunidad implements ComunidadCRUD {
    @PersistenceContext

    @Override
    public Comunidad guardar(Comunidad comunidad) {
        EntityTransaction tx = entityManager().getTransaction();
        if (!tx.isActive())
            tx.begin();

        entityManager().persist(comunidad);
        tx.commit();

        return comunidad;
    }

    @Override
    public Comunidad buscarPorId(Long id) {
        return entityManager().find(Comunidad.class, id);
    }

    @Override
    public List<Comunidad> buscarTodos() {
        return entityManager().createQuery("SELECT e FROM Comunidad e", Comunidad.class)
                .getResultList();
    }

    @Override
    public void actualizar(Comunidad comunidad) {
        entityManager().merge(comunidad);
    }

    @Override
    public void eliminar(Long id) {
        Comunidad comunidad = entityManager().find(Comunidad.class, id);
        if (comunidad != null) {
            entityManager().remove(comunidad);
        }
    }

    public Comunidad buscarPorNombre(String nombre) {
        return entityManager().createQuery("SELECT i FROM Comunidad i WHERE i.nombre = :nombre ", Comunidad.class)
                .getSingleResult();
    }
}