package fr.istic.taa.jaxrs.service.business;

import fr.istic.taa.jaxrs.dao.business.UtilisateurDAO;
import fr.istic.taa.jaxrs.domain.Utilisateur;
import fr.istic.taa.jaxrs.dto.UtilisateurDTO;
import fr.istic.taa.jaxrs.service.generic.AbstractService;


import java.util.ArrayList;
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

    public List<UtilisateurDTO> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = findAll();
        List<UtilisateurDTO> utilisateurDTOs = new ArrayList<>();
        for (Utilisateur utilisateur : utilisateurs) {
            new UtilisateurDTO(utilisateur);
            utilisateurDTOs.add(new UtilisateurDTO(utilisateur));
        }
        return utilisateurDTOs;
    }
}
