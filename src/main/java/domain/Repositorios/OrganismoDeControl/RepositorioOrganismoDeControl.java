package domain.Repositorios.OrganismoDeControl;


import domain.entidades.servicios.Entidad;
import domain.entidades.servicios.OrganismoDeControl;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioOrganismoDeControl implements OrganismoDeControlCRUD {

    @Override
    public OrganismoDeControl guardar(OrganismoDeControl organismoDeControl) {
        EntityTransaction tx = entityManager().getTransaction();
        if(!tx.isActive())
            tx.begin();

        entityManager().persist(organismoDeControl);
        tx.commit();

        return organismoDeControl;
    }

    @Override
    public OrganismoDeControl buscarPorId(Long id) {
        return entityManager().find(OrganismoDeControl.class, id);
    }

    @Override
    public List<OrganismoDeControl> buscarTodos() {
        return entityManager().createQuery("from " + OrganismoDeControl.class.getName()).getResultList();
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
