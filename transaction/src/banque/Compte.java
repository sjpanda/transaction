package banque;

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
	
	public void debiter(int s){
		this.somme -= s;
	}
	
	public void crediter(int s){
		this.somme += s;
	}
	

}
