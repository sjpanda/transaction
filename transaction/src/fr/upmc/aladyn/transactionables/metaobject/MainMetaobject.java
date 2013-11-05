package fr.upmc.aladyn.transactionables.metaobject;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.NotFoundException;
import javassist.tools.reflect.Loader;

public class MainMetaobject {
	public static void main(String[] args){
		try {
			//			Loader cl = new Loader() ;
			//			MyT t = new MyT(cl);
			//			ClassPool pool = ClassPool.getDefault();
			//
			//			cl.addTranslator(pool, t);

			Loader cl = new Loader();
			cl.makeReflective(Banque.class.getName(), 
					VerboseMetaobj.class.getName(), 
					"javassist.tools.reflect.ClassMetaobject");
			cl.makeReflective(Compte.class.getName(), 
					VerboseMetaobj.class.getName(), 
					"javassist.tools.reflect.ClassMetaobject");
			cl.makeReflective(Client.class.getName(), 
					VerboseMetaobj.class.getName(), 
					"javassist.tools.reflect.ClassMetaobject");
			

			cl.run(Main.class.getName(), args);
		} catch (CannotCompileException e) {
			e.printStackTrace();
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}


}

