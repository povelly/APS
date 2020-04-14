package exceptions;

import aps0.ASTident;

public class TypeException extends APSException {

	private static final long serialVersionUID = 1L;

	public TypeException(ASTident var) {
		super("Wrong type for variable " + var.getString());
	}

}
