package fr.istic.taa.jaxrs.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.istic.taa.jaxrs.domain.Evenement;
import java.sql.Date;

public class EvenementDTO {

    /**
     * Name of the event.
     */
    private String nom;
    /**
     * Description of the event.
     */
    private String description;
    /**
     * Date of the event.
     */
    private Date date;
    /**
     * Location of the event.
     */
    private String lieu;
    /**
     * Artiste of the event.
     */
    private String artiste;
    /**
     * Genre of the event.
     */
    private String genre;

    /**
     * Price of the event.
     */
    private double price;

    /**
     * ID of the event.
     */
    private Long idEvenement;

    /**
     * Nb places max of the event.
     */
    private int nbMax;

    /**
     * Nb places available of the event.
     */
    private int nbSold;


    /**
     * Constructor of the event DTO.
     * @param evenement the event.
     */
    public EvenementDTO(final Evenement evenement) {
        this.idEvenement = evenement.getId();
        this.nom = evenement.getNom();
        this.description = evenement.getDescription();
        this.date = Date.valueOf(evenement.getDate().toLocalDate());
        this.lieu = evenement.getLieu();
        this.artiste = evenement.getArtiste();
        this.genre = evenement.getGenre();
        this.price = evenement.getPrice();
        this.nbMax = evenement.getNbMax();
        this.nbSold = evenement.getNbSold();
    }

    /**
     * Setter for the name of the event.
     * @param paramNom the name of the event
     */
    public void setNom(final String paramNom) {
        this.nom = paramNom;
    }

    /**
     * Getter for the name of the event.
     * @return the name of the event
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter for the description of the event.
     * @param paramDescription the description of the event
     */
    public void setDescription(final String paramDescription) {
        this.description = paramDescription;
    }

    /**
     * Getter for the description of the event.
     * @return the description of the event
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for the date of the event. JsonFormat is used to specify the format of the date as Europe/Paris timezone.
     * @param paramDate the date of the event
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Paris")
    public void setDate(final Date paramDate) {
        this.date = paramDate;
    }

    /**
     * Getter for the date of the event. JsonFormat is used to specify the format of the date as Europe/Paris timezone.
     * @return the date of the event
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Paris")
    public Date getDate() {
        return date;
    }

    /**
     * Setter for the location of the event.
     * @param paramLieu the location of the event
     */
    public void setLieu(final String paramLieu) {
        this.lieu = paramLieu;
    }

    /**
     * Getter for the location of the event.
     * @return the location of the event
     */
    public String getLieu() {
        return lieu;
    }

    /**
     * Setter for the artiste of the event.
     * @param paramArtiste the artiste of the event
     */
    public void setArtiste(final String paramArtiste) {
        this.artiste = paramArtiste;
    }

    /**
     * Getter for the artiste of the event.
     * @return the artiste of the event
     */
    public String getArtiste() {
        return artiste;
    }

    /**
     * Setter for the genre of the event.
     * @param paramGenre the genre of the event
     */
    public void setGenre(final String paramGenre) {
        this.genre = paramGenre;
    }

    /**
     * Getter for the genre of the event.
     * @return the genre of the event
     */
    public String getGenre() {
        return genre;
    }

        /**
         * Setter for the price of the event.
         */
        public void setPrice(final double price) {
            this.price = price;
        }

    /**
     * Getter for the price of the event (raw price without formatting).
     * @return the raw price of the event
     */
    public double getPrice() {
        return price;
    }

    /**
     * Getter for the id of the event.
     *
     * @return the id of the event
     */
    public long getIdEvenement() {
        return idEvenement;
    }

    /**
     * Setter for the id of the event.
     * @param paramIdEvenement the id of the event
     */
    public void setIdEvenement(final Long paramIdEvenement) {
        this.idEvenement = paramIdEvenement;
    }

    /**
     * Getter for the nbMax of the event.
     * @return the nbMax of the event
     */
    public int getNbMax() {
        return nbMax;
    }

    /**
     * Setter for the nbMax of the event.
     * @param paramNbMax the nbMax of the event
     */
    public void setNbMax(final int paramNbMax) {
        this.nbMax = paramNbMax;
    }

    /**
     * Getter for the nbSold of the event.
     * @return the nbSold of the event
     */
    public int getNbSold() {
        return nbSold;
    }

    /**
     * Setter for the nbSold of the event.
     * @param paramNbSold the nbSold of the event
     */
    public void setNbSold(final int paramNbSold) {
        this.nbSold = paramNbSold;
    }

}
