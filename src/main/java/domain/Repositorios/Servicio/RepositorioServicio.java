package domain.Repositorios.Servicio;


import domain.entidades.servicios.Servicio;

import java.util.List;

public class RepositorioServicio implements ServicioCRUD{

    @Override
    public Servicio guardar(Servicio servicio) {
        entityManager().persist(servicio);
        return servicio;
    }

    @Override
    public Servicio buscarPorId(Long id) {
        return entityManager().find(Servicio.class, id);
    }

    @Override
    public List<Servicio> buscarTodos() {
        return entityManager().createQuery("SELECT e FROM Entidad e", Servicio.class)
                .getResultList();
    }

    @Override
    public void actualizar(Servicio servicio) {
        entityManager().merge(servicio);
    }

    @Override
    public void eliminar(Long id) {
        Servicio servicio = entityManager().find(Servicio.class, id);
        if (servicio != null) {
            entityManager().remove(servicio);
        }
    }
}
