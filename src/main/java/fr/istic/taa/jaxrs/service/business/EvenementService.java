package fr.istic.taa.jaxrs.service.business;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Evenement;
import fr.istic.taa.jaxrs.dto.EvenementDTO;

import java.util.ArrayList;
import java.util.List;

public class EvenementService extends AbstractJpaDao<Long, Evenement> {
    public EvenementService() {
        super(Evenement.class);
    }

    public EvenementDTO getEvenementById(Long id) {
        Evenement evenement = findOne(id);
        if (evenement != null) {
            return new EvenementDTO(evenement);
        }
        return null;
    }

    public List<EvenementDTO> getAllEvenements() {
        List<Evenement> evenements = findAll();
        List<EvenementDTO> evenementDTOs = new ArrayList<>();
        for (Evenement evenement : evenements) {
            new EvenementDTO(evenement);
            evenementDTOs.add(new EvenementDTO(evenement));
        }
        return evenementDTOs;
    }
}
