package fr.upmc.aladyn.transactionables.injection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Abdoul Diallo
 * @author Jing Shu
 */
public class MainInjection {
	public static void main(String[] args) {
		Banque b = new Banque("LCL");
		Client c1 = new Client("Bob");
		Client c2 = new Client("Alice");
		Compte cp1 = new Compte(c1);
		Compte cp2 = new Compte(c2);
		c1.setCompte(cp1);
		c2.setCompte(cp2);
		
		b.displayFields();
		System.out.println(c1.getCompte());
		
		List<String> classes = new ArrayList<String>();
		classes.add("fr.upmc.aladyn.transactionables.injection.Banque");
		classes.add("fr.upmc.aladyn.transactionables.injection.Client");
		classes.add("fr.upmc.aladyn.transactionables.injection.Compte");
		Injection.injecter(classes);
		try {
			b.add(cp1);
			b.add(cp2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		b.displayFields();
		System.out.println(c1.getCompte());
	}

}
