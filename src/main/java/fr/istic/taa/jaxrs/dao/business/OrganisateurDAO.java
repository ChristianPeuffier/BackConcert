package fr.istic.taa.jaxrs.dao.business;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Organisateur;


public class OrganisateurDAO extends AbstractJpaDao<Long, Organisateur> {

    /**
     * Constructor.
     */
    public OrganisateurDAO() {
        super(Organisateur.class);
    }
}
