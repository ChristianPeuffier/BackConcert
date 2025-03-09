package fr.istic.taa.jaxrs.service.business;

import fr.istic.taa.jaxrs.dao.business.UtilisateurDAO;
import fr.istic.taa.jaxrs.domain.Utilisateur;
import fr.istic.taa.jaxrs.dto.UtilisateurDTO;
import fr.istic.taa.jaxrs.service.generic.AbstractService;
import fr.istic.taa.jaxrs.utils.PasswordUtil;


import java.util.ArrayList;
import java.util.List;

public class UtilisateurService extends AbstractService<Long, Utilisateur> {

    private final UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
    /**
     * Constructor.
     */
    public UtilisateurService() {
        super(new UtilisateurDAO());
    }

    /**
     * Get an Utilisateur by its id.
     * @param id the id of the Utilisateur
     * @return the created Utilisateur DTO
     */
    public UtilisateurDTO getUtilisateurById(final Long id) {
        Utilisateur utilisateur = findOne(id);
        if (utilisateur != null) {
            return new UtilisateurDTO(utilisateur);
        }
        return null;
    }

    /**
     * Get all Utilisateurs.
     * @return the list of Utilisateur DTO
     */
    public List<UtilisateurDTO> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = findAll();
        List<UtilisateurDTO> utilisateurDTOs = new ArrayList<>();
        for (Utilisateur utilisateur : utilisateurs) {
            new UtilisateurDTO(utilisateur);
            utilisateurDTOs.add(new UtilisateurDTO(utilisateur));
        }
        return utilisateurDTOs;
    }

    /**
     * Create an Utilisateur.
     * @param utilisateur the Utilisateur to create
     */
    public String saveUser(final Utilisateur utilisateur) {
        // Vérification de l'existence de l'email

        if (utilisateurDAO.emailExist(utilisateur.getEmail())) {
            return "L'email est déjà utilisé";
        }

        // Hachage du mot de passe et sauvegarde de l'utilisateur
        String hashedPassword = PasswordUtil.hashPassword(utilisateur.getPassword());
        utilisateur.setPassword(hashedPassword);
        save(utilisateur);
        return "Utilisateur créé avec succès";
    }

    /**
     * Get an Utilisateur by its email.
     * @param email the email of the Utilisateur
     * @return the created Utilisateur DTO
     */
    public UtilisateurDTO getUtilisateurByEmail(final String email) {
        Utilisateur user = utilisateurDAO.findByEmail(email);
        if (user != null) {
            return new UtilisateurDTO(user);
        }
        return null;
    }

    /**
     * Check if password is correct.
     * @param email the email of the Utilisateur
     * @param password the password to check
     * @return true if password is correct, false otherwise
     */
    public boolean checkPassword(final String email, final String password) {
        Utilisateur utilisateur = utilisateurDAO.findByEmail(email);
        return PasswordUtil.checkPassword(password, utilisateur.getPassword());
    }


}
