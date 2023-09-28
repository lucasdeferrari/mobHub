package domain.Repositorios.Entidad;

import domain.entidades.servicios.Entidad;

import java.util.List;

public class RepositorioEntidad implements EntidadCRUD {

    @Override
    public Entidad guardar(Entidad entidad) {
        entityManager().persist(entidad);
        return entidad;
    }

    @Override
    public Entidad buscarPorId(Long id) {
        return entityManager().find(Entidad.class, id);
    }

    @Override
    public List<Entidad> buscarTodos() {
        return entityManager().createQuery("SELECT e FROM Entidad e", Entidad.class)
                .getResultList();
    }

    @Override
    public void actualizar(Entidad entidad) {
        entityManager().merge(entidad);
    }

    @Override
    public void eliminar(Long id) {
        Entidad entidad = entityManager().find(Entidad.class, id);
        if (entidad != null) {
            entityManager().remove(entidad);
        }
    }
}
