package exceptions;

import aps1.ASTproc;
import interfaces.IFun;

public class ArityException extends APSException {

	private static final long serialVersionUID = 1L;

	public ArityException(IFun f) {
		super("Function " + f + " is called with a wrong number of arguments");
	}
	
	public ArityException(ASTproc p) {
		super("Procedure " + p + " is called with a wrong number of arguments");
	}

}
