package fr.upmc.aladyn.transactionables.transaction_manuelle;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import fr.upmc.aladyn.transactionables.annotations.Transactionable;

/**
 * @author Abdoul Diallo
 * @author Jing Shu
 */
@Transactionable
public class Exemple {
	public int x = 10;
	protected String y = "ok";
	private boolean z = false;
	
	
	
	public static void main(String[] args){
		Exemple e = new Exemple();
		Class<?> claz = e.getClass();
		Field[] fields = claz.getDeclaredFields();
		for(Field f : fields){
			try {
				System.out.println("Field : " + Modifier.toString(f.getModifiers()) + " " + f.getType().getName() + " " + f.getName() + " " + f.get(e));
			} catch (IllegalArgumentException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
		}
		
		
	}
}
