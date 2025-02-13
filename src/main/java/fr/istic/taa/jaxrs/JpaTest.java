package fr.istic.taa.jaxrs;


import fr.istic.taa.jaxrs.dao.business.EvenementDAO;
import fr.istic.taa.jaxrs.dao.business.OrganisateurDAO;
import fr.istic.taa.jaxrs.dao.business.UtilisateurDAO;
import fr.istic.taa.jaxrs.dao.generic.EntityManagerHelper;
import fr.istic.taa.jaxrs.domain.Evenement;
import fr.istic.taa.jaxrs.domain.Organisateur;
import fr.istic.taa.jaxrs.domain.Utilisateur;
import jakarta.persistence.EntityManager;

import java.sql.Date;
import java.time.ZonedDateTime;


public class JpaTest {


	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {

		try {

			EvenementDAO evenementDAO = new EvenementDAO();
			OrganisateurDAO organisateurDAO = new OrganisateurDAO();
			UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
			Organisateur organisateur = new Organisateur();
			Utilisateur utilisateur = new Utilisateur();
			Evenement evenement = new Evenement();

			utilisateur.setNom("Peuffier");
			utilisateur.setPrenom("Christian");
			utilisateur.setEmail("christian.peuffier@gmail.com");
			utilisateur.setPassword("password");
			utilisateurDAO.save(utilisateur);

			organisateur.setNom("Raulais");
			organisateur.setPrenom("Alexandre");
			organisateur.setEmail("AlexandreR0910@gmail.com");
			organisateur.setPassword("password");
			organisateurDAO.save(organisateur);


			evenement.setNom("Gims Tour");
			evenement.setLieu("Rennes");
			evenement.setDescription("Tournée de france de l'artiste Gims");
			evenement.setGenre("Pop et R&B");
			evenement.setDate(Date.valueOf(ZonedDateTime.now().toLocalDate()));
			evenement.setOrganisateur(organisateur);
			evenementDAO.save(evenement);
			organisateur.getEvenements().add(evenement);
			organisateurDAO.update(organisateur);



		} catch (Exception e) {
			e.printStackTrace();
		}

		EntityManagerHelper.closeEntityManagerFactory();
		System.out.println(".. done");
	}




}
