package med.voll.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GestorErrores {

    //id no existe
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity gestionarError404(){
        return ResponseEntity.notFound().build();
    }

    //te faltan valores que son obligatorios y cuales son
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity gestionarError400(MethodArgumentNotValidException ex){
        var error = ex.getFieldErrors().stream()
                .map(DatosErrorDTO::new).toList();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity tratarErrorValidacion(ValidacionException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    public record DatosErrorDTO(String campo, String mensaje){
            public DatosErrorDTO(FieldError error){
                this(error.getField(), error.getDefaultMessage());
            }
    }


}
