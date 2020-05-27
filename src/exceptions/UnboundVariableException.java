package exceptions;

import aps0.ASTident;

public class UnboundVariableException extends APSException {

	private static final long serialVersionUID = 1L;

	public UnboundVariableException(ASTident var) {
		super("Unbound variable " + var.getName());
	}

}
