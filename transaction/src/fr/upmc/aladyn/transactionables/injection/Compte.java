package fr.upmc.aladyn.transactionables.injection;

import fr.upmc.aladyn.transactionables.annotations.Transactionable;

/**
 * @author Abdoul Diallo
 * @author Jing Shu
 */
public class Compte {
	private double somme;
	private Client client;
	
	public Compte(Client c){
		this.somme = 0;
		this.client = c;
	}
	
	public double getSomme() {
		return somme;
	}
	public void setSomme(double somme) {
		this.somme = somme;
	}
	
	public Client getClient(){
		return client;
	}
	
	public void setClient(Client c){
		this.client = c;
	}
	
	@Transactionable
	public void debiter(double s) throws Exception{
		SaveRestore srb = new SaveRestore();
		srb.save(this);
		this.somme -= s;
		if(this.somme < -100.0){
			srb.restore(this);
			throw new Exception();
		}
	}
	
	public void crediter(double s){
		this.somme += s;
	}
	

	

}
