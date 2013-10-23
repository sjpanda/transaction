package fr.upmc.aladyn.transactionables.banque;

import static org.junit.Assert.*;

import org.junit.Test;

import banque.Client;
import banque.Compte;
import banque.Banque;
import banque.SaveRestore;


public class TestBanque {

	@Test
	public void testAjoutBanque() {
		// on cree notre banque
		Banque banque = new Banque("Test");

		//on cree notre client
		Client c1 = new Client("c1");

		//on cree notre compte 
		Compte cp1 = new Compte(c1);

		//on affecte au client c1 le compte cp1
		c1.setCompte(cp1);

		//la taille de la banque doit etre vide
		assertTrue(banque.getComptes().size() == 0);

		//on ajoute à la banque un compte
		try {
			banque.add(cp1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur lors de l'ajout de la banque cp1");
		}

		//la taille de la banque doit etre egale a 1
		assertTrue(banque.getComptes().size() == 1);

		/*************************************************************/

		//on rajoute un autre compte pour pouvoir lever une exception
		try {
			banque.add(new Compte(c1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur lors de l'ajout de la banque cp1");
		}

		//la taille de la banque doit etre egale a 1
		assertTrue(banque.getComptes().size() == 1);

		//le somme detenu dans le compte doit etre egale a 0
		assertTrue(cp1.getSomme() == 0.0);

		//on fait un retrait de 60 euros à partir de banque
		banque.retraitMensuelle(60.0);

		//le somme detenu dans le compte doit etre egale a 0
		assertTrue(cp1.getSomme() == -60.0);

		//on leve une exception
		banque.retraitMensuelle(100.0);

		//le somme detenu dans le compte doit etre egale a 0
		assertTrue(cp1.getSomme() == -60.0);

	}

	@Test
	public void testSaveRestaure() {
		//on sauvegarde avant
		
		//on cree notre client
		Client c1 = new Client("c1");

		//on cree notre compte 
		Compte cp1 = new Compte(c1);
		
		//on lui ajoute une somme
		cp1.crediter(100.0);
		assertTrue(cp1.getSomme() == 100.0);
		
		
		SaveRestore srb = new SaveRestore();
		srb.save(this);
		
		//on lui ajoute une somme
		cp1.crediter(100.0);
		assertTrue(cp1.getSomme() == 200.0);
		
		try {
			//on restaure
			srb.restore(this);
			assertTrue(cp1.getSomme() == 100.0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
