package br.com.dextra.bootcamp.MentorMatch.exception;

public class UnexistentEntityException extends Exception {

	private static final long serialVersionUID = 1L;

	public UnexistentEntityException() {}
	
	public UnexistentEntityException(String message) {
		super(message);
	}
}