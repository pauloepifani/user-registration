package br.com.userregistration.exception;

public class InvalidLoginException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public InvalidLoginException(String message) {
		super(message);
	}

}
