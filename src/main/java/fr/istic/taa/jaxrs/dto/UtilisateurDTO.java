package fr.istic.taa.jaxrs.dto;

import fr.istic.taa.jaxrs.domain.Utilisateur;

public class UtilisateurDTO {

    /**
     * Name of the user.
     */
    private String nom;

    /**
     * First name of the user.
     */
    private String prenom;

    /**
     * Email of the user.
     */
    private String email;

    /**
     * Constructor.
     * @param utilisateur User.
     */
    public UtilisateurDTO(final Utilisateur utilisateur) {
        this.nom = utilisateur.getNom();
        this.prenom = utilisateur.getPrenom();
        this.email = utilisateur.getEmail();
    }

    /**
     * Setter for the name.
     * @param paramNom the name
     */
    public void setNom(final String paramNom) {
        this.nom = paramNom;
    }

    /**
     * Getter for the name.
     * @return the name
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter for the first name.
     * @param paramPrenom the first name
     */
    public void setPrenom(final String paramPrenom) {
        this.prenom = paramPrenom;
    }

    /**
     * Getter for the first name.
     * @return the first name
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Setter for the email.
     * @param paramEmail the email
     */
    public void setEmail(final String paramEmail) {
        this.email = paramEmail;
    }

    /**
     * Getter for the email.
     * @return the email
     */
    public String getEmail() {
        return email;
    }
}
