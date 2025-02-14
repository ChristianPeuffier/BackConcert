package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Entity
public class Stats implements Serializable {

    /**
     * The id attribute as an int.
     */
    @Id
    @GeneratedValue
    private int id;

    /**
     * The evenement attribute as an Evenement.
     */
    @ManyToOne
    @JoinColumn(name = "evenement_id")
    private Evenement evenement;

    /**
     * Getter for the number of tickets sold.
     * @return the number of tickets sold
     */
    public Evenement getEvenement() {
        return evenement;
    }

    /**
     * Setter for the number of tickets sold.
     * @param paramEvenement the number of tickets sold
     */
    public void setEvenement(final Evenement paramEvenement) {
        this.evenement = paramEvenement;
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
