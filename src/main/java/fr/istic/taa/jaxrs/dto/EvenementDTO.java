package fr.istic.taa.jaxrs.dto;

import fr.istic.taa.jaxrs.domain.Evenement;

import java.sql.Date;

public class EvenementDTO {

    private String nom;
    private String description;
    private Date date;
    private String lieu;
    private String genre;


    public EvenementDTO(Evenement evenement) {
        this.nom = evenement.getNom();
        this.description = evenement.getDescription();
        this.date = evenement.getDate();
        this.lieu = evenement.getLieu();
        this.genre = evenement.getGenre();

    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getLieu() {
        return lieu;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }


}
