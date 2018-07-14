package pl.sda.spring.petclinic.aop;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.sda.spring.petclinic.exception.OwnerNotFoundException;
import pl.sda.spring.petclinic.exception.UserNotFoundException;

@ControllerAdvice
public class ApplicationErrorHandler {

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity handleNumberFormatException(){
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(OwnerNotFoundException.class)
    public ResponseEntity handleOwnerNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity handleUserNotFoundException(){
        return ResponseEntity.notFound().build();
    }


}
