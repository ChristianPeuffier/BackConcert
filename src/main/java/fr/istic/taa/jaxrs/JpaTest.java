package fr.istic.taa.jaxrs;


import fr.istic.taa.jaxrs.dao.business.EvenementDAO;
import fr.istic.taa.jaxrs.dao.business.OrganisateurDAO;
import fr.istic.taa.jaxrs.dao.business.UtilisateurDAO;
import fr.istic.taa.jaxrs.dao.generic.EntityManagerHelper;
import fr.istic.taa.jaxrs.domain.Evenement;
import fr.istic.taa.jaxrs.domain.Organisateur;
import fr.istic.taa.jaxrs.domain.Utilisateur;
import jakarta.persistence.EntityManager;


public class JpaTest {


	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {

			EvenementDAO evenementDAO = new EvenementDAO();

			Organisateur organisateur = new Organisateur();
			organisateur.setNom("Raulais");
			organisateur.setPrenom("Alexandre");
			organisateur.setEmail("AlexandreR0910@gmail.com");
			organisateur.setPassword("password");
			OrganisateurDAO organisateurDAO = new OrganisateurDAO();
			organisateurDAO.save(organisateur);

			Evenement evenement = new Evenement();
			evenement.setNom("Gims Tour");
			evenement.setLieu("Rennes");
			evenement.setDescription("Tournée de france de l'artiste Gims");
			evenement.setGenre("Pop et R&B");
			evenement.setDate(java.sql.Date.valueOf("2022-06-01"));
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
