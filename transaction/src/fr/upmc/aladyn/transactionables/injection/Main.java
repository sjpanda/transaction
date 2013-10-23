package fr.upmc.aladyn.transactionables.injection;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Loader;
import javassist.NotFoundException;
import javassist.Translator;
import javassist.expr.ExprEditor;
import javassist.expr.Handler;

public class Main {

	public static void main(String[] args) {
	    try {
			Translator t = new MyTranslator();
			ClassPool pool = ClassPool.getDefault() ;
		    Loader cl = new Loader() ;
			cl.addTranslator(pool, t);
		    cl.run("fr.upmc.aladyn.transactionables.injection.MainInjection", args);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}

class MyTranslator implements Translator
{
	public void	onLoad(ClassPool pool, String className) throws	NotFoundException, CannotCompileException
	{
		if (className.equals("fr.upmc.aladyn.transactionables.injection.Client")) {
			CtClass c = pool.get(className) ;
			CtMethod m = c.getDeclaredMethod("mistake") ;
			m.instrument(
				new ExprEditor() {
					public void edit(Handler h) throws CannotCompileException {
						h.insertBefore(
							"System.out.println(\"ajoutée au catch...\");");
					}
				});
			CtClass etype = pool.get("java.lang.Exception") ;
			m.addCatch("{ System.out.println(987) ; throw $e ; }", etype);
		}
	}

	public void start(ClassPool arg0) throws NotFoundException, CannotCompileException
	{
	}
}
