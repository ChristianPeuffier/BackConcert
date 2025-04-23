package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "evenement")
public class Evenement implements Serializable {

    /**
     * The maximum length for Columns in database.
     */
    private static final int MAX_LENGTH = 100;

    /**
     * The maximum length for Description Column in database.
     */
    private static final int MAX_LENGTH_DESCRIPTION = 1000;

    /**
     * The id attribute as an int.
     */
    @Id
    @GeneratedValue
    private int id;

    /**
     * The nom attribute as a String.
     */
    @Column(length = MAX_LENGTH, name = "nom")
    private String nom;

    /**
     * The date attribute as a Date.
     */
    @Temporal(TemporalType.DATE)
    private Date date;

    /**
     * The lieu attribute as a String.
     */
    @Column(name = "lieu")
    private String lieu;

    /**
     * The description attribute as a String.
     */
    @Column(length = MAX_LENGTH_DESCRIPTION, name = "description")
    private String description;

    /**
     * The organisateur attribute as an Organisateur.
     */
    @ManyToOne
    @JoinColumn(name = "organisateur_id")
    private Organisateur organisateur;

    /**
     * The artiste attribute as a String.
     */
    @Column(length = MAX_LENGTH, name = "artiste")
    private String artiste;

    /**
     * The genre attribute as a String.
     */
    @Column(length = MAX_LENGTH, name = "genre")
    private String genre;

    /**
     * Price of the event.
     */
    @Column(length = MAX_LENGTH,name = "price")
    private double price;

    /**
     * Number of tickets maximum.
     */
    @Column(length = MAX_LENGTH,name = "nbMax")
    private int nbMax;

    /**
     * Number of tickets sold.
     */
    @Column(length = MAX_LENGTH,name = "nbSold")
    private int nbSold;

    /**
     * Getter for the id attribute.
     *
     * @return the id attribute as an int.
     */
    public long getId() {
        return id;
    }

    /**
     * Setter for the id attribute.
     * @param paramId the id attribute to set.
     */
    public void setId(final int paramId) {
        this.id = paramId;
    }

    /**
     * Getter for the nom attribute.
     * @return the nom attribute as a String.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter for the nom attribute.
     * @param paramNom the nom attribute to set.
     */
    public void setNom(final String paramNom) {
        this.nom = paramNom;
    }

    /**
     * Getter for the date attribute.
     * @return the date attribute as a Date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Setter for the date attribute.
     * @param paramDate the date attribute to set.
     */
    public void setDate(final Date paramDate) {
        this.date = paramDate;
    }

    /**
     * Getter for the lieu attribute.
     * @return the lieu attribute as a String.
     */
    public String getLieu() {
        return lieu;
    }

    /**
     * Setter for the lieu attribute.
     * @param paramLieu the lieu attribute to set.
     */
    public void setLieu(final String paramLieu) {
        this.lieu = paramLieu;
    }

    /**
     * Getter for the description attribute.
     * @return the description attribute as a String.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for the description attribute.
     * @param paramDescription the description attribute to set.
     */
    public void setDescription(final String paramDescription) {
        this.description = paramDescription;
    }

    /**
     * Getter for the organisateur attribute.
     * @return the organisateur attribute as an Organisateur.
     */
    public Organisateur getOrganisateur() {
        return organisateur;
    }

    /**
     * Setter for the organisateur attribute.
     * @param paramOrganisateur the organisateur attribute to set.
     */
    public void setOrganisateur(final Organisateur paramOrganisateur) {
        this.organisateur = paramOrganisateur;
    }

    /**
     * Getter for the artiste attribute.
     * @return the artiste attribute as a String.
     */
    public String getArtiste() {
        return artiste;
    }

    /**
     * Setter for the artiste attribute.
     * @param paramArtiste the artiste attribute to set.
     */
    public void setArtiste(final String paramArtiste) {
        this.artiste = paramArtiste;
    }

    /**
     * Getter for the genre attribute.
     * @return the genre attribute as a String.
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Setter for the genre attribute.
     * @param paramGenre the genre attribute to set.
     */
    public void setGenre(final String paramGenre) {
        this.genre = paramGenre;
    }

    /**
     * Getter for the price attribute.
     * @return the price attribute as a double.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter for the price attribute.
     * @param paramPrice the price attribute to set.
     */

    public void setPrice(final double paramPrice) {
        this.price = paramPrice;
    }

    /**
     * Getter for the nbMax attribute.
     * @return the nbMax attribute as an int.
     */
    public int getNbMax() {
        return nbMax;
    }

    /**
     * Setter for the nbMax attribute.
     * @param paramNbMax the nbMax attribute to set.
     */
    public void setNbMax(final int paramNbMax) {
        this.nbMax = paramNbMax;
    }

    /**
     * Getter for the nbSold attribute.
     * @return the nbSold attribute as an int.
     */
    public int getNbSold() {
        return nbSold;
    }

    /**
     * Setter for the nbSold attribute.
     * @param paramNbSold the nbSold attribute to set.
     */
    public void setNbSold(final int paramNbSold) {
        this.nbSold = paramNbSold;
    }



}
