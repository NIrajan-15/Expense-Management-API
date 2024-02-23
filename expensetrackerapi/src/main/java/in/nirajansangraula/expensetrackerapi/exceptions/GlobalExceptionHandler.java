package in.nirajansangraula.expensetrackerapi.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
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

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorObject> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception, WebRequest request) {

        ErrorObject errObject = new ErrorObject();
        errObject.setMessage(exception.getMessage());
        errObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errObject.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(errObject, HttpStatus.BAD_REQUEST);
       
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObject> handleGeneralException(Exception exception, WebRequest request)
    {
        ErrorObject errObject = new ErrorObject();
        errObject.setMessage(exception.getMessage());
        errObject.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errObject.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(errObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
