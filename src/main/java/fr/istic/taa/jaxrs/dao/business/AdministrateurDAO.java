package fr.istic.taa.jaxrs.dao.business;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Administrateur;

public class    AdministrateurDAO extends AbstractJpaDao<Long, Administrateur> {

    /**
     * Constructor.
     */
    public AdministrateurDAO() {
        super(Administrateur.class);
    }
}
