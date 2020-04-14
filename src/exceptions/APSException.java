package exceptions;

public abstract class APSException extends Exception {

	private static final long serialVersionUID = 1L;
	
	protected APSException(String message) {
		super("APS Exception : " + message);
	}

}
