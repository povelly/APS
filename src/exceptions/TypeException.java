package exceptions;

public class TypeException extends APSException {

	private static final long serialVersionUID = 1L;

	public TypeException() {
		super("Wrong type while caling a function");
	}

}
