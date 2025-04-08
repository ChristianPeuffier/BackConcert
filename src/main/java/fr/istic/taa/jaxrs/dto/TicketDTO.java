package fr.istic.taa.jaxrs.dto;

import fr.istic.taa.jaxrs.domain.StatutTicket;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.Utilisateur;

import java.sql.Date;

public class TicketDTO {

    /**
     * ID of the ticket.
     */
    private Long id;

    /**
     * Ticket status.
     */
    private StatutTicket statut;

    /**
     * Ticket purchase date.
     */
    private Date date;

    /**
     * Event location.
     */
    private String lieu;


    /**
     * Prix
     */
    private Double prix;

    /**
     * User.
     */
    private UtilisateurDTO utilisateur;

    /**
     * Constructor.
     * @param ticket Ticket.
     */
    public TicketDTO(final Ticket ticket) {
        this.id = (long) ticket.getId();
        this.statut = ticket.getStatut();
        this.date = ticket.getDateAchat();
        this.lieu = ticket.getEvenement().getLieu();
        this.utilisateur = new UtilisateurDTO(ticket.getUtilisateur());
        this.prix = ticket.getPrix();
    }

    /**
     * Setter for statut.
     * @param paramStatut Statut.
     */
    public void setStatut(final StatutTicket paramStatut) {
        this.statut = paramStatut;
    }

    /**
     * Getter for statut.
     * @return Statut.
     */
    public StatutTicket getStatut() {
        return statut;
    }

    /**
     * Setter for date.
     * @param paramDate Date.
     */
    public void setDate(final Date paramDate) {
        this.date = paramDate;
    }

    /**
     * Getter for date.
     * @return Date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Setter for lieu.
     * @param paramLieu Lieu.
     */
    public void setLieu(final String paramLieu) {
        this.lieu = paramLieu;
    }

    /**
     * Getter for lieu.
     * @return Lieu.
     */
    public String getLieu() {
        return lieu;
    }

    /**
     * Setter for utilisateur.
     * @param paramUtilisateur Utilisateur.
     */
    public void setUtilisateur(final Utilisateur paramUtilisateur) {
        this.utilisateur = new UtilisateurDTO(paramUtilisateur);
    }

    /**
     * Getter for utilisateur.
     * @return Utilisateur.
     */
    public UtilisateurDTO getUtilisateur() {
        return utilisateur;
    }

    /**
     * Getter for prix.
     * @return Prix.
     */
    public Double getPrix() {
        return prix;
    }

    /**
     * Setter for prix.
     * @param paramPrix Prix.
     */
    public void setPrix(final Double paramPrix) {
        this.prix = paramPrix;
    }

    /**
     * Getter for id.
     * @return ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for id.
     * @param paramId ID.
     */
    public void setId(final Long paramId) {
        this.id = paramId;
    }
}
