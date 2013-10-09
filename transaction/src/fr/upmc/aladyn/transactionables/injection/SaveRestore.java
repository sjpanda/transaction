package fr.upmc.aladyn.transactionables.injection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Abdoul Diallo
 * @author Jing Shu
 */
public class SaveRestore {
	Map<String, Object> olds;
	
	public SaveRestore(){
		olds = new HashMap<String, Object>();
	}
	
	public void save(Object obj){
		Class<?> claz = obj.getClass();
		Field[] fields = claz.getDeclaredFields();
		for(Field f : fields){
			try {
				String field = f.getName();
				Method m = claz.getDeclaredMethod(
						"get" + field.replaceFirst(field.substring(0, 1), field.substring(0, 1).toUpperCase()));
				olds.put(f.getName(), m.invoke(obj));
				//System.out.println("Field : " + Modifier.toString(f.getModifiers()) + " " + f.getType().getName() + " " + f.getName() + " " + f.get(this));
			} catch (IllegalArgumentException | IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void restore(Object obj){
		Class<?> claz = obj.getClass();
		Field[] fields = claz.getDeclaredFields();
		for(Field f : fields){
			try {
				String field = f.getName();
				Method m = claz.getDeclaredMethod(
						"set" + field.replaceFirst(field.substring(0, 1), field.substring(0, 1).toUpperCase()), 
						f.getType());
				m.invoke(obj, olds.get(field));
				//f.set(obj, olds.get(f.getName()));
				//System.out.println("Field : " + Modifier.toString(f.getModifiers()) + " " + f.getType().getName() + " " + f.getName() + " " + f.get(this));
			} catch (IllegalArgumentException | IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
