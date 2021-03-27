package br.com.dextra.bootcamp.MentorMatch.exception;

public class UnexistentEntityException extends Exception {

    public UnexistentEntityException(){}
    public UnexistentEntityException(String message) {
        super(message);
    }
}
