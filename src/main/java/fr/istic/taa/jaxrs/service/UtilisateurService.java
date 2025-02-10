package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.business.UtilisateurDAO;
import fr.istic.taa.jaxrs.domain.Utilisateur;
import fr.istic.taa.jaxrs.dto.UtilisateurDTO;
import fr.istic.taa.jaxrs.service.generic.AbstractService;
import jakarta.persistence.EntityManager;

import java.util.List;

public class UtilisateurService extends AbstractService<Long, Utilisateur> {

    public UtilisateurService() {
        super(new UtilisateurDAO());
    }

    public UtilisateurDTO getUtilisateurById(Long id) {
        Utilisateur utilisateur = findOne(id);
        if (utilisateur != null) {
            return new UtilisateurDTO(utilisateur);
        }
        return null;
    }
}
