package fr.istic.taa.jaxrs;

import fr.istic.taa.jaxrs.dao.business.EvenementDAO;
import fr.istic.taa.jaxrs.dao.business.OrganisateurDAO;
import fr.istic.taa.jaxrs.dao.business.UtilisateurDAO;
import fr.istic.taa.jaxrs.dao.generic.EntityManagerHelper;
import fr.istic.taa.jaxrs.domain.Evenement;
import fr.istic.taa.jaxrs.domain.Organisateur;
import fr.istic.taa.jaxrs.domain.Utilisateur;

import java.sql.Date;
import java.time.ZonedDateTime;

public final class JpaTest {

    /**
     * constructor.
     **/
    private JpaTest() {
    }

    /**
     * @param args the command line arguments
     **/
    public static void main(final String[] args) {

        try {



        } catch (Exception e) {
            e.printStackTrace();
        }

        EntityManagerHelper.closeEntityManagerFactory();
        System.out.println(".. done");
    }
}
