package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.text.DateFormat;
import java.time.ZonedDateTime;

@Entity
@Table(name = "evenement")
public class Evenement implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    @Column(length = 100, name = "nom")
    private String nom;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(length = 255, name = "lieu")
    private String lieu;

    @Column(length = 1000, name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "organisateur_id")
    private Organisateur organisateur;

    @Column(length = 100, name = "artiste")
    private String artiste;

    @Column(length = 100, name = "genre")
    private String genre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Organisateur getOrganisateur() {
        return organisateur;
    }

    public void setOrganisateur(Organisateur organisateur) {
        this.organisateur = organisateur;
    }

    public String getArtiste() {
        return artiste;
    }

    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
