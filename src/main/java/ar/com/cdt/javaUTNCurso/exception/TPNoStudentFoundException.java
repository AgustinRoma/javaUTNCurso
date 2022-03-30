package ar.com.cdt.javaUTNCurso.exception;

public class TPNoStudentFoundException extends TPBaseException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2390709598149077405L;

	public TPNoStudentFoundException(String message, String serviceMessage) {
		super(message, serviceMessage);
	}
}
