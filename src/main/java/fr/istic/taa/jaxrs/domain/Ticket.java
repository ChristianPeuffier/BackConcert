package fr.istic.taa.jaxrs.domain;

import fr.istic.taa.jaxrs.dto.UtilisateurDTO;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Temporal;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.TemporalType;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "ticket")
public class Ticket implements Serializable {

    /**
     * The maximum length for Columns in database.
     */
    private static final int MAX_LENGTH = 100;

    /**
     * The id attribute as an int.
     */
    @Id
    @GeneratedValue
    private int id;

    /**
     * The utilisateur attribute as an Utilisateur.
     */
    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    /**
     * The evenement attribute as an Evenement.
     */
    @ManyToOne
    @JoinColumn(name = "evenement_id")
    private Evenement evenement;

    /**
     * The prix attribute as a Double.
     */
    @Column(length = MAX_LENGTH, name = "prix")
    private Double prix;

    /**
     * The dateAchat attribute as a Date.
     */
    @Temporal(TemporalType.DATE)
    private Date dateAchat;

    /**
     * The statut attribute as a StatutTicket.
     */
    @Enumerated(EnumType.STRING)
    @Column(length = MAX_LENGTH, name = "statut")
    private StatutTicket statut;

    /**
     * Getter for the statut.
     * @return the statut
     */
    public StatutTicket getStatut() {
        return statut;
    }

    /**
     * Setter for the statut.
     * @param paramStatut the statut
     */
    public void setStatut(final StatutTicket paramStatut) {
        this.statut = paramStatut;
    }

    /**
     * Getter for the date of purchase.
     * @return the date of purchase
     */
    public Date getDateAchat() {
        return dateAchat;
    }

    /**
     * Setter for the date of purchase.
     * @param paramDateAchat the date of purchase
     */
    public void setDateAchat(final Date paramDateAchat) {
        this.dateAchat = paramDateAchat;
    }

    /**
     * Getter for the price.
     * @return the price
     */
    public Double getPrix() {
        return prix;
    }

    /**
     * Setter for the price.
     * @param paramPrix the price
     */
    public void setPrix(final Double paramPrix) {
        this.prix = paramPrix;
    }

    /**
     * Getter for the event.
     * @return the event
     */
    public Evenement getEvenement() {
        return evenement;
    }

    /**
     * Setter for the event.
     * @param paramEvenement the event
     */
    public void setEvenement(final Evenement paramEvenement) {
        this.evenement = paramEvenement;
    }

    /**
     * Getter for the user.
     * @return the user
     */
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    /**
     * Setter for the user.
     * @param paramUtilisateur the user
     */
    public void setUtilisateur(final Utilisateur paramUtilisateur) {
        this.utilisateur = paramUtilisateur;
    }

    /**
     * Getter for the id.
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for the id.
     * @param paramId the id
     */
    public void setId(final int paramId) {
        this.id = paramId;
    }
}
