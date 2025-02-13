package fr.istic.taa.jaxrs.dao.business;


import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Utilisateur;

public class UtilisateurDAO extends AbstractJpaDao<Long, Utilisateur> {

    /**
     * Constructor.
     */
    public UtilisateurDAO() {
        super(Utilisateur.class);
    }


}
