package fr.upmc.aladyn.transactionables.injection;


import java.util.List;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.expr.ExprEditor;
import javassist.expr.Handler;
import fr.upmc.aladyn.transactionables.annotations.Transactionable;

public class Injection {
	/* la methode injecter prend une liste de noms de classes
	 * et ajouter les codes de sauvegarde et de restauration 
	 * aux méthodes transactionnables dans ces classes
	 */
	public static void injecter(List<String> classes){
		ClassPool pool = ClassPool.getDefault();
		for(String claz : classes){
			try {
				CtClass cc = pool.get(claz);
				if(! cc.hasAnnotation(Transactionable.class)){
					continue;
				}
				
				CtClass ccSaveRestore = pool.get(SaveRestore.class.getPackage().getName() + ".SaveRestore");
				CtField fSaveRestore = new CtField(ccSaveRestore, "saveRestore", cc);
				fSaveRestore.setModifiers(Modifier.PRIVATE);
				cc.addField(fSaveRestore);
				
				CtField fSelf = new CtField(ccSaveRestore, "saveRestore", cc);
				fSaveRestore.setModifiers(Modifier.PRIVATE);
				cc.addField(fSaveRestore);
				
				CtMethod[] cms = cc.getDeclaredMethods();
				for(CtMethod cm : cms){
					if (cm.hasAnnotation(Transactionable.class)){
						// backup au debut de la methode
						String before = "{ saveRestore = new " + SaveRestore.class.getPackage().getName() + ".SaveRestore(); saveRestore.save($0);}";
						cm.insertBefore(before);
						
						// add "throws Exception" a la signature de la methode
						CtClass etype = pool.get("java.lang.Exception");
						CtClass[] etypes = { etype };
						cm.setExceptionTypes(etypes);
						
						// injecter les catch/finally existants avec la methode de restauration
						cm.instrument(
								new ExprEditor(){
									public void edit(Handler h) throws CannotCompileException{
										//h.insertBefore("{ System.out.println($1); }");
										h.insertBefore("{ throw new Exception(); }");
										h.insertBefore("{ System.out.println(22); }");
										//h.insertBefore("{ saveRestore.restore($0); throw new Exception(); }");
										//h.replace("{ $_ = $proceed($$); saveRestore.restore($0); throw new Exception(); }", this);
										//h.replace("{ $_ = $proceed($$); saveRestore.restore($0); throw new Exception(); }");
									}
								});
						
						// ajouter un catch pour restaurer si une exception est levée
						cm.addCatch("{ saveRestore.restore($0); throw $e; }", etype);
					}
				}
			} catch (NotFoundException e) {
				e.printStackTrace();
			} catch (CannotCompileException e) {
				e.printStackTrace();
			}
		}
	}
}
