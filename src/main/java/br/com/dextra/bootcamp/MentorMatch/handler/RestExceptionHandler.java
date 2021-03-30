package br.com.dextra.bootcamp.MentorMatch.handler;

import br.com.dextra.bootcamp.MentorMatch.exception.UnexistentEntityException;
import br.com.dextra.bootcamp.MentorMatch.exception.UnexistentEntityExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(UnexistentEntityException.class)
    public ResponseEntity<UnexistentEntityExceptionDetails> handlerUnexistentEntityException(UnexistentEntityException une) {
        return new ResponseEntity<>(
                UnexistentEntityExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Not Found Exception, Check the Documentation")
                .details(une.getMessage())
                .developerMessage(une.getClass().getName())
                .build(), HttpStatus.BAD_REQUEST);
    }
}
