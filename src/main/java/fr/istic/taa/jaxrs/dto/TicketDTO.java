package fr.istic.taa.jaxrs.dto;

import fr.istic.taa.jaxrs.domain.StatutTicket;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.Utilisateur;

import java.sql.Date;

public class TicketDTO {


    private StatutTicket statut;
    private Date date;
    private String lieu;
    private UtilisateurDTO utilisateur;

    public TicketDTO() {
    }

    public TicketDTO(Ticket ticket) {
        this.statut = ticket.getStatut();
        this.date = ticket.getDateAchat();
        this.lieu = ticket.getEvenement().getLieu();
        this.utilisateur = new UtilisateurDTO(ticket.getUtilisateur());

    }

    public void setStatut(StatutTicket statut) {
        this.statut = statut;
    }

    public StatutTicket getStatut() {
        return statut;
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

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = new UtilisateurDTO(utilisateur);
    }

    public UtilisateurDTO getUtilisateur() {
        return utilisateur;
    }
}
