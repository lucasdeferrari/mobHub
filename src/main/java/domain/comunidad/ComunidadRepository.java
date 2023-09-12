package domain.comunidad;


import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Repository
public class ComunidadRepository implements WithSimplePersistenceUnit{
    @PersistenceContext

    public List<Comunidad> findAll() {
        return entityManager().createQuery("select f from Comunidad f", Comunidad.class).getResultList();
    }
    public Comunidad find(int id) {
        return entityManager().find(Comunidad.class, id);
    }
    @Transactional
    public void delete(Comunidad comunidad) {
        entityManager().remove(entityManager().merge(comunidad));
    }
    @Transactional
    public void insert(Comunidad comunidad) {
        entityManager().persist(comunidad);
    }
    @Transactional
    public void actualizar(Comunidad comunidad) {
        entityManager().merge(comunidad);
    }

}
