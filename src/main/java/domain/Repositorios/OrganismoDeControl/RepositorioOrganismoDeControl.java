package domain.Repositorios.OrganismoDeControl;


import domain.servicios.OrganismoDeControl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class RepositorioOrganismoDeControl implements OrganismoDeControlCRUD {

    @Override
    public OrganismoDeControl guardar(OrganismoDeControl organismoDeControl) {
        entityManager().persist(organismoDeControl);
        return organismoDeControl;
    }

    @Override
    public OrganismoDeControl buscarPorId(Long id) {
        return entityManager().find(OrganismoDeControl.class, id);
    }

    @Override
    public List<OrganismoDeControl> buscarTodos() {
        return entityManager().createQuery("SELECT e FROM Entidad e", OrganismoDeControl.class)
                .getResultList();
    }

    @Override
    public void actualizar(OrganismoDeControl organismoDeControl) {
        entityManager().merge(organismoDeControl);
    }

    @Override
    public void eliminar(Long id) {
        OrganismoDeControl organismoDeControl = entityManager().find(OrganismoDeControl.class, id);
        if (organismoDeControl != null) {
            entityManager().remove(organismoDeControl);
        }
    }
}
