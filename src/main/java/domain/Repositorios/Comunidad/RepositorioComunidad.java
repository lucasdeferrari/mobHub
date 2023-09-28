package domain.Repositorios.Comunidad;

import domain.entidades.comunidad.Comunidad;
import org.springframework.stereotype.Repository;


import javax.persistence.PersistenceContext;
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
