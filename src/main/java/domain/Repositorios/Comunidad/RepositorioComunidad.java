package domain.Repositorios.Comunidad;

import domain.comunidad.Comunidad;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class RepositorioComunidad implements ComunidadCRUD {
    @PersistenceContext

    @Override
    public Comunidad guardar(Comunidad comunidad) {
        entityManager().persist(comunidad);
        return comunidad;
    }

    @Override
    public Comunidad buscarPorId(Long id) {
        return entityManager().find(Comunidad.class, id);
    }

    @Override
    public List<Comunidad> buscarTodos() {
        return entityManager().createQuery("SELECT e FROM Entidad e", Comunidad.class)
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
}
