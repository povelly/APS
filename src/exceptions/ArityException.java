package exceptions;

import interfaces.IFun;

public class ArityException extends APSException {

	private static final long serialVersionUID = 1L;

	public ArityException(IFun f) { // TODO fix
		super("Function " + f + " is called with a wrong number of arguments");
	}

}
