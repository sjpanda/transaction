package fr.upmc.aladyn.transactionables.metaobject;

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
	private ArrayList<Compte> comptes;
	
	/**
	 * @param nom le nom de la banque
	 */
	public Banque(String nom){
		this.comptes = new ArrayList<Compte>();
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
	public ArrayList<Compte> getComptes(){
		return comptes;
	}

	/**
	 * @param compte une liste de comptes
	 */
	public void setComptes(ArrayList<Compte> comptes){
		this.comptes = comptes;
	}
	
	/**
	 * @param compte un compte � ajouter dans la banque
	 * @throws Exception
	 */
	@Transactionable
	public void add(Compte compte) throws Exception{
		setNom("BNP");
		System.out.println("Change Banque name :  ");
		displayFields();
		
		if(this.comptes.size() > 0){
			System.out.println("Banque exception : ");
			throw new Exception();
		} else {
			System.out.println("Banque -> client exception : ");
			this.comptes.add(compte);
			compte.getClient().mistake();
		}
		System.out.println();
	}
	
	/**
	 * @param compte un compte � retirer de la banque
	 * @return true si la suppresion a eu lieu, false si la suppresion �choue
	 */
	public boolean remove(Compte compte){
		return this.comptes.remove(compte);
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
	
	public String toString(){
		this.setNom("toto");
		return "1223";
	}
}
