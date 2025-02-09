package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.io.Serializable;

@Entity
@DiscriminatorValue("administrateur")
public class Administrateur extends Utilisateur implements Serializable {


    public Administrateur() {
        super();
    }
}
