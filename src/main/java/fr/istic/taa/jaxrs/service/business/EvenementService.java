package fr.istic.taa.jaxrs.service.business;

import fr.istic.taa.jaxrs.dao.business.EvenementDAO;
import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Evenement;
import fr.istic.taa.jaxrs.dto.EvenementDTO;
import fr.istic.taa.jaxrs.service.generic.AbstractService;

import java.util.ArrayList;
import java.util.List;

public class EvenementService extends AbstractService<Long, Evenement> {

    /**
     * Constructor.
     */
    public EvenementService() {
        super(new EvenementDAO());
    }

    /**
     * Get an Evenement from the database by its id.
     * @param id the id of the Organisateur
     * @return the created Evenement DTO
     */
    public EvenementDTO getEvenementById(final Long id) {
        Evenement evenement = findOne(id);
        if (evenement != null) {
            return new EvenementDTO(evenement);
        }
        return null;
    }

    /**
     * Get all Evenements from the database.
     * @return the created Evenement
     */
    public List<EvenementDTO> getAllEvenements() {
        List<Evenement> evenements = findAll();
        List<EvenementDTO> evenementDTOs = new ArrayList<>();
        for (Evenement evenement : evenements) {
            new EvenementDTO(evenement);
            evenementDTOs.add(new EvenementDTO(evenement));
        }
        return evenementDTOs;
    }

    /**
     * Create an Evenement in the database.
     * @param evenement the Evenement to create
     */
    public void createEvenement(final Evenement evenement) {
        save(evenement);
    }

    /**
     * Update an Evenement in the database.
     * @param evenement the Evenement to update
     */
    public void updateEvenement(final Evenement evenement) {
        update(evenement);
    }

    /**
     * Delete an Evenement from the database.
     * @param evenement the Evenement to delete
     */
    public void deleteEvenement(final Evenement evenement) {
        delete(evenement);
    }
}
