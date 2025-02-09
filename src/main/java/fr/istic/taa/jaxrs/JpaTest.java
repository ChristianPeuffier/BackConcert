package fr.istic.taa.jaxrs;


import fr.istic.taa.jaxrs.dao.business.UtilisateurDAO;
import fr.istic.taa.jaxrs.dao.generic.EntityManagerHelper;
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

			UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setNom("Alexis");
				utilisateur.setPrenom("Raulais");
				utilisateur.setEmail("Alexisr0910@gmail.com");
				utilisateur.setPassword("1234");
				utilisateurDAO.save(utilisateur);

		} catch (Exception e) {
			e.printStackTrace();
		}

		EntityManagerHelper.closeEntityManagerFactory();
		System.out.println(".. done");
	}




}
