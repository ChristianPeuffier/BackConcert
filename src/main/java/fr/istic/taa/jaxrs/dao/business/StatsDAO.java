package fr.istic.taa.jaxrs.dao.business;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Stats;




public class StatsDAO extends AbstractJpaDao<Long, Stats> {

    /**
     * Constructor.
     */
    public StatsDAO() {
        super(Stats.class);
    }

}
