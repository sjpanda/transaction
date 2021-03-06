package banque;

/**
 * @author Abdoul Diallo
 * @author Jing Shu
 */
public class Main {

	public static void main(String[] args) {
		Banque b = new Banque("LCL");
		Client c1 = new Client("Bob");
		Client c2 = new Client("Alice");
		Compte cp1 = new Compte(c1);
		Compte cp2 = new Compte(c2);
		c1.setCompte(cp1);
		c2.setCompte(cp2);
		System.out.println("Initial :  ");
		b.displayFields();
		System.out.println("Compte de Bob : " + c1.getCompte());
		System.out.println();
		
		System.out.println("First add :  ");
		try {
			b.add(cp1);
		} catch (Exception e) {}
		System.out.println("\nAfter first add : ");
		b.displayFields();
		System.out.println("Compte de Bob : " + c1.getCompte());
		System.out.println();
		
		System.out.println("Second add :  ");
		try {
			b.add(cp2);
		} catch (Exception e) {}
		System.out.println("\nAfter second add : ");
		b.displayFields();
		System.out.println("Compte de Bob : " + c1.getCompte());
		System.out.println();
	}

}
