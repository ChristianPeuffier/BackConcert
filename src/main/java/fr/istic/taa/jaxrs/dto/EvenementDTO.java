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
     * Genre of the event.
     */
    private String genre;


    /**
     * Constructor of the event DTO.
     * @param evenement the event.
     */
    public EvenementDTO(final Evenement evenement) {
        this.nom = evenement.getNom();
        this.description = evenement.getDescription();
        this.date = Date.valueOf(evenement.getDate().toLocalDate());
        this.lieu = evenement.getLieu();
        this.genre = evenement.getGenre();

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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Paris")  // Vous pouvez choisir le format souhaité
    public void setDate(final Date paramDate) {
        this.date = paramDate;
    }

    /**
     * Getter for the date of the event. JsonFormat is used to specify the format of the date as Europe/Paris timezone.
     * @return the date of the event
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Paris")  // Vous pouvez choisir le format souhaité
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

}
