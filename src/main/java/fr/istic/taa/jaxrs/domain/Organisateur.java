package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.List;

@Entity
@DiscriminatorValue("organisateur")
public class Organisateur extends Utilisateur implements Serializable {

    @OneToMany(mappedBy = "organisateur")
    private List<Evenement> evenements;


    public Organisateur() {
        super();
    }
}
