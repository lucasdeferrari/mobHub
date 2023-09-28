package domain.Repositorios.EntidadPrestadora;

import domain.entidades.servicios.EntidadPrestadora;

import java.util.List;

public class RepositorioEntidadPrestadora implements EntidadPrestadoraCRUD {

    @Override
    public EntidadPrestadora guardar(EntidadPrestadora entidadPrestadora) {
        entityManager().persist(entidadPrestadora);
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
