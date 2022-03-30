package ar.com.cdt.javaUTNCurso.exception;

public class TPInvalidActionException extends TPBaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8884429543440528790L;

	public TPInvalidActionException(String message, String serviceMessage) {
		super(message, serviceMessage);
	}
}
