package fr.upmc.aladyn.transactionables.metaobject;

import java.lang.reflect.Method;

import fr.upmc.aladyn.transactionables.annotations.Transactionable;

import javassist.tools.reflect.Metaobject;

public class VerboseMetaobj extends Metaobject{
	private static final long serialVersionUID = 1L;

	public VerboseMetaobj(Object self, Object[] args){
		super(self, args);
		System.out.println("VerboseMetaobj created for " + self.getClass().getName());
	}
	
	public Object trapMethodcall(int identifier, Object[] args) throws Throwable{
		
		Method m = methods[identifier];
		System.out.println("ok kkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
		return super.trapMethodcall (identifier ,args) ;
		
		
//		System.out.println("trap method call : " + getMethodName(identifier) + "() in " + getClassMetaobject().getName());
		
//		Class<?>[] paramsTypes = new Class[args.length];
//		for(int i=0; i<args.length; i++){
//			paramsTypes[i] = args[i].getClass();
//		}
//		Method m = getObject().getClass().getDeclaredMethod(getMethodName(identifier), paramsTypes);
		
//		Method m = methods[identifier];
//		System.out.println("=== test === : " + m.getName());
//		Object res = null;
//		if(m.isAnnotationPresent(Transactionable.class)){
//			SaveRestore sr = new SaveRestore();
//			sr.save(getObject());
//			try{
//				res = m.invoke(getObject(), args);
//			} catch (Exception e){
//				sr.restore(getObject());
//				throw new Exception();
//			}
//		}
//		
//		return res;
	}
}
