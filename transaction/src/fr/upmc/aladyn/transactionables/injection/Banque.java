package fr.upmc.aladyn.transactionables.injection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import fr.upmc.aladyn.transactionables.annotations.Transactionable;

/**
 * @author Abdoul Diallo
 * @author Jing Shu
 */
@Transactionable
public class Banque {
	
	private String nom;
	private ArrayList<Compte> compte;
	
	/**
	 * @param nom le nom de la banque
	 */
	public Banque(String nom){
		this.compte = new ArrayList<Compte>();
		this.nom = nom;
	}

	/**
	 * @return le nom de la banque
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom le nom de la banque
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * @return la liste des comptes
	 */
	public ArrayList<Compte> getCompte(){
		return compte;
	}

	/**
	 * @param compte une liste de comptes
	 */
	public void setCompte(ArrayList<Compte> compte){
		this.compte = compte;
	}
	
	/**
	 * @param compte un compte à ajouter dans la banque
	 * @throws Exception
	 */
	@Transactionable
	public void add(Compte compte) throws Exception{
			setNom("BNP");
			
			if(this.compte.size() > 0){
				System.out.println("==============");
				displayFields();
				System.out.println("==============");
				throw new Exception();
			} else {
				this.compte.add(compte);
				compte.getClient().mistake();
			}
	}
	
	/**
	 * @param compte un compte à retirer de la banque
	 * @return true si la suppresion a eu lieu, false si la suppresion échoue
	 */
	public boolean remove(Compte compte){
		return this.compte.remove(compte);
	}

	public void displayFields(){
		Class<?> claz = this.getClass();
		Field[] fields = claz.getDeclaredFields();
		for(Field f : fields){
			try {
				System.out.println("Field : " + Modifier.toString(f.getModifiers()) + " " + f.getType().getName() + " " + f.getName() + " " + f.get(this));
			} catch (IllegalArgumentException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
		}
	}
}
