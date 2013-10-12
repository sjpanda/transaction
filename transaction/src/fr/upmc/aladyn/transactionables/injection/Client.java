package fr.upmc.aladyn.transactionables.injection;

import java.io.IOException;

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
	public void mistake(){
		try {
			System.out.println("Client : mistake : old compte : " + getCompte());
			Compte c = new Compte(this);
			c.setSomme(1000);
			this.compte = c;
			System.out.println("Client : mistake : new compte : " + getCompte());
			throw new IOException();
		} catch (IOException e){
		}
	}
}
