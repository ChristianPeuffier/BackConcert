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

    @OneToMany(mappedBy = "organisateur")
    private List<Evenement> evenements;

    /**
     * Constructeur par défaut
     */
    public Organisateur() {
        super();
        this.evenements = new ArrayList<>();
    }

    /**
     * Getter de la liste des événements
     * @return la liste des événements
     */
    public List<Evenement> getEvenements() {
        return evenements;
    }

    /**
     * Setter de la liste des événements
     * @param evenements la liste des événements
     */
    public void setEvenements(List<Evenement> evenements) {
        this.evenements = evenements;
    }
}
