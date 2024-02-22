package in.nirajansangraula.expensetrackerapi.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import in.nirajansangraula.expensetrackerapi.entity.ErrorObject;
import org.springframework.http.HttpStatus;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorObject> handleExpenseNotFoundException(ResourceNotFoundException exception, WebRequest request) {

        ErrorObject errObject = new ErrorObject();
        errObject.setMessage(exception.getMessage());
        errObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errObject.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(errObject, HttpStatus.NOT_FOUND);
       
}

}
