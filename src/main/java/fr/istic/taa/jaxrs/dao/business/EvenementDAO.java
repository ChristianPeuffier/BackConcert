package fr.istic.taa.jaxrs.dao.business;


import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Evenement;

public class EvenementDAO extends AbstractJpaDao<Long, Evenement> {

    public EvenementDAO(){
        super(Evenement.class);
    }
}
