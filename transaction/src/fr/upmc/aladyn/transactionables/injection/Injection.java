package fr.upmc.aladyn.transactionables.injection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import fr.upmc.aladyn.transactionables.annotations.Transactionable;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

public class Injection {
	/* la methode injecter prend une liste de noms de classes
	 * et ajouter les codes de sauvegarde et de restauration 
	 * aux méthodes transactionnables dans ces classes
	 */
	public static void injecter(List<String> classes){
		ClassPool pool = ClassPool.getDefault();
		for(String claz : classes){
			try {
				CtClass cc  = pool.get(claz);
				CtMethod[] cms = cc.getDeclaredMethods();
				for(CtMethod cm : cms){
					if (cm.hasAnnotation(Transactionable.class)){
//						StringBuffer before = new StringBuffer();
//						before.append("java.util.Map<String, Object> olds = new java.util.HashMap<String, object>();\n");
//						before.append("Class<?> claz = this.getClass();\n");
//						before.append("java.lang.reflect.Field[] fields = claz.getDeclaredFields();\n");
//						before.append("for(java.lang.reflect.Field f : fields){\n");
//						before.append("try {\n");
//						before.append("String field = f.getName();\n");
//						before.append("java.lang.reflect.Method m = claz.getDeclaredMethod(\n");
//						before.append("\"get\" + field.replaceFirst(field.substring(0, 1), field.substring(0, 1).toUpperCase()));\n");
//						before.append("olds.put(f.getName(), m.invoke(this));\n");
//						before.append("} catch (IllegalArgumentException | IllegalAccessException e1) {\n");
//						before.append("e1.printStackTrace();\n");
//						before.append("} catch (NoSuchMethodException e) {\n");
//						before.append("e.printStackTrace();\n");
//						before.append("} catch (SecurityException e) {\n");
//						before.append("e.printStackTrace();\n");
//						before.append("} catch (java.lang.reflect.InvocationTargetException e) {\n");
//						before.append("e.printStackTrace();\n");
//						before.append("}\n");
//						before.append("}\n");
//						before.append("try {\n");
//						cm.insertBefore(before.toString());
//						
//						StringBuffer after = new StringBuffer();
//						after.append("} catch {\n");
//						after.append("for(java.lang.reflect.Field f : fields){\n");
//						after.append("try {\n");
//						after.append("String fieldd = f.getName();\n");
//						after.append("java.lang.reflect.Method m = claz.getDeclaredMethod(\n");
//						after.append("\"set\" + fieldd.replaceFirst(fieldd.substring(0, 1), fieldd.substring(0, 1).toUpperCase()),\n");
//						after.append("f.getType());\n");
//						after.append("m.invoke(this, olds.get(fieldd));\n");
//						after.append("} catch (IllegalArgumentException | IllegalAccessException e1) {\n");
//						after.append("e1.printStackTrace();\n");
//						after.append("} catch (NoSuchMethodException e) {\n");
//						after.append("e.printStackTrace();\n");
//						after.append("} catch (SecurityException e) {\n");
//						after.append("e.printStackTrace();\n");
//						after.append("} catch (java.lang.reflect.InvocationTargetException e) {\n");
//						after.append("e.printStackTrace();\n");
//						after.append("}\n");
//						after.append("}\n");
//						after.append("}\n");
//						cm.insertAfter(after.toString());
						
						String before = "{ fr.upmc.aladyn.transactionables.injection.SaveRestore srb = " + 
								"new fr.upmc.aladyn.transactionables.injection.SaveRestore(); srb.save($0);}";
						cm.insertBefore(before);
						
						//String after = "{ srb.restore($0); }";
						//cm.insertAfter(after);
					}
				}
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CannotCompileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
