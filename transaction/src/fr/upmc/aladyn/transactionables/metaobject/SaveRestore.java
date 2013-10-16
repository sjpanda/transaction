package fr.upmc.aladyn.transactionables.metaobject;

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
			} catch (IllegalArgumentException | IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
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
			} catch (IllegalArgumentException | IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
}
