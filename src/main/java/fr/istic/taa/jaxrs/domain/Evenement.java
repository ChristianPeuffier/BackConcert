package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "evenement")
public class Evenement implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    @Column(length = 100, name = "nom")
    private String nom;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(length = 255, name = "lieu")
    private String lieu;

    @Column(length = 1000, name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "organisateur_id")
    private Organisateur organisateur;

    @Column(length = 100, name = "artiste")
    private String artiste;

    @Column(length = 100, name = "genre")
    private String genre;

}
