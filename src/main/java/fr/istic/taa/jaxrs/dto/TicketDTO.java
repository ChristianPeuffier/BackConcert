package fr.istic.taa.jaxrs.dto;

import fr.istic.taa.jaxrs.domain.StatutTicket;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.Utilisateur;

import java.sql.Date;

public class TicketDTO {

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
     * User.
     */
    private UtilisateurDTO utilisateur;

    /**
     * Constructor.
     * @param ticket Ticket.
     */
    public TicketDTO(final Ticket ticket) {
        this.statut = ticket.getStatut();
        this.date = ticket.getDateAchat();
        this.lieu = ticket.getEvenement().getLieu();
        this.utilisateur = new UtilisateurDTO(ticket.getUtilisateur());

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
}
