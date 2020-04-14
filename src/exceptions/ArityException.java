package exceptions;

public class ArityException extends APSException {

	private static final long serialVersionUID = 1L;

	public ArityException(String func) { // TODO fix
		super("Function " + func + " is called with a wrong number of arguments");
	}

}
