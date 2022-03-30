package ar.com.cdt.javaUTNCurso.exception;

public class TPDuplicatedStudentException extends TPBaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1054536109363663184L;

	public TPDuplicatedStudentException(String message, Throwable cause) {
		super(message, cause);
	}

	public TPDuplicatedStudentException(String message, String serviceMessage) {
		super(message, serviceMessage);
	}
}
