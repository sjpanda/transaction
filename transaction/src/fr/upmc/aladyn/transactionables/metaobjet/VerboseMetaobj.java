package fr.upmc.aladyn.transactionables.metaobjet;

import javassist.tools.reflect.Metaobject;

public class VerboseMetaobj extends Metaobject{
	private static final long serialVersionUID = 1L;

	public VerboseMetaobj(Object self, Object[] args){
		super(self, args);
		System.out.println("VerboseMetaobj created for " + self.getClass().getName());
	}
	
	public Object trapMethodCall(int identifier, Object[] args) throws Throwable{
		System.out.println("trap method call : " + getMethodName(identifier) + "() in " + getClassMetaobject().getName());
		
		return super.trapMethodcall(identifier, args);
	}
}
