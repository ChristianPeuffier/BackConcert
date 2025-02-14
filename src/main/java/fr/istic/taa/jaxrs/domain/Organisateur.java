package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("organisateur")
public class Organisateur extends Utilisateur implements Serializable {

    /**
     * The list of events.
     */
    @OneToMany(mappedBy = "organisateur")
    private List<Evenement> evenements;

    /**
     * Default constructor.
     */
    public Organisateur() {
        super();
        this.evenements = new ArrayList<>();
    }

    /**
     * Getter for the list of events.
     * @return the list of events
     */
    public List<Evenement> getEvenements() {
        return evenements;
    }

    /**
     * Setter for the list of events.
     * @param paramEvenements the list of events
     */
    public void setEvenements(final List<Evenement> paramEvenements) {
        this.evenements = paramEvenements;
    }
}
