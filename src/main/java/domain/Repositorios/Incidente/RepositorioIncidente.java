package domain.Repositorios.Incidente;


import domain.servicios.Incidente;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class RepositorioIncidente implements IncidenteCRUD {

    @Override
    public Incidente guardar(Incidente incidente) {
        entityManager().persist(incidente);
        return incidente;
    }

    @Override
    public Incidente buscarPorId(Long id) {
        return entityManager().find(Incidente.class, id);
    }

    @Override
    public List<Incidente> buscarTodos() {
        return entityManager().createQuery("SELECT e FROM Entidad e", Incidente.class)
                .getResultList();
    }

    @Override
    public void actualizar(Incidente incidente) {
        entityManager().merge(incidente);
    }

    @Override
    public void eliminar(Long id) {
        Incidente incidente = entityManager().find(Incidente.class, id);
        if (incidente != null) {
            entityManager().remove(incidente);
        }
    }
}
