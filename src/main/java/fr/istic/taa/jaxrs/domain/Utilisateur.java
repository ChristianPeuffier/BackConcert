package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "utilisateur")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_utilisateur", discriminatorType = DiscriminatorType.STRING)
 public class Utilisateur implements Serializable {

   /**
    * The maximum length for Columns in database.
    */
   private static final int MAX_LENGTH = 100;

    /**
     * The id attribute as a long.
     */
    @Id
    @GeneratedValue
    private long id;

    /**
     * The nom attribute as a String.
     */
    @Column(length = MAX_LENGTH, name = "nom", nullable = false)
    private String nom;

    /**
     * The prenom attribute as a String.
     */
    @Size(min =8)
    @Column(length = MAX_LENGTH, name = "prenom", nullable = false)
    private String prenom;

    /**
     * The email attribute as a String.
     */
    @Email
    @Column(length = MAX_LENGTH, name = "email", nullable = false)
    private String email;

    /**
     * The password attribute as a String.
     */
    @Column(length = MAX_LENGTH, name = "password", nullable = false)
    private String password;

    /**
     * The list of tickets.
     */
    @OneToMany(mappedBy = "utilisateur")
    private List<Ticket> tickets;

    /**
     * The type of the user as a String.
     */
    @Column(name = "type_utilisateur", insertable = false, updatable = false)
    private String typeUtilisateur;


    /**
     * Default constructor.
     */
    public Utilisateur() {
        this.tickets = new ArrayList<>();
    }

    /**
     * Getter for the id.
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Setter for the id.
     * @param paramId the id
     */
    public void setId(final long paramId) {
        this.id = paramId;
    }

    /**
     * Getter for the nom.
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter for the nom.
     * @param paramNom the nom
     */
    public void setNom(final String paramNom) {
        this.nom = paramNom;
    }

    /**
     * Getter for the prenom.
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Setter for the prenom.
     * @param paramPrenom the prenom
     */
    public void setPrenom(final String paramPrenom) {
        this.prenom = paramPrenom;
    }

    /**
     * Getter for the email.
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for the email.
     * @param paramEmail the email
     */
    public void setEmail(final String paramEmail) {
        this.email = paramEmail;
    }

    /**
     * Getter for the password.
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for the password.
     * @param paramPassword the password
     */
    public void setPassword(final String paramPassword) {
        this.password = paramPassword;
    }

    /**
     * Getter for the tickets.
     * @return the tickets
     */
    public List<Ticket> getTickets() {
        return tickets;
    }

    /**
     * Setter for the tickets.
     * @param paramTickets the tickets
     */
    public void setTickets(final List<Ticket> paramTickets) {
        this.tickets = paramTickets;
    }
}
