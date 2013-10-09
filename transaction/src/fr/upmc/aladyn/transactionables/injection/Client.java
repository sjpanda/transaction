package fr.upmc.aladyn.transactionables.injection;

import fr.upmc.aladyn.transactionables.annotations.Transactionable;

/**
 * @author Abdoul Diallo
 * @author Jing Shu
 */
@Transactionable
public class Client {
	private String nom;
	private Compte compte;

	public Client(String n){
		this.nom = n;
	}
	
	public String getNom() {
		return nom;
	}
	
	public Compte getCompte(){
		return compte;
	}

	public void setCompte(Compte cp){
		this.compte = cp;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@Transactionable
	public void mistake() throws Exception{
			Compte c = new Compte(this);
			c.setSomme(1000);
			this.compte = c;
			throw new Exception();
	}
}
