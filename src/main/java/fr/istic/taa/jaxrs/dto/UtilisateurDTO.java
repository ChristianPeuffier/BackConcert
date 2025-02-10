package fr.istic.taa.jaxrs.dto;

import fr.istic.taa.jaxrs.domain.Utilisateur;

public class UtilisateurDTO {

    private String nom;
    private String prenom;
    private String email;

    public UtilisateurDTO(Utilisateur utilisateur) {
        this.nom = utilisateur.getNom();
        this.prenom = utilisateur.getPrenom();
        this.email = utilisateur.getEmail();
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
