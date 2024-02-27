package in.nirajansangraula.expensetrackerapi.exceptions;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import in.nirajansangraula.expensetrackerapi.entity.ErrorObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.util.Date;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
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
        errObject.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errObject.setMessage(exception.getMessage());
        errObject.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(errObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ItemAlreadyExistsException.class)
    public ResponseEntity<ErrorObject> handleItemAlreadyExistsException(Exception exception, WebRequest request)
    {
        ErrorObject errObject = new ErrorObject();
        errObject.setStatusCode(HttpStatus.CONFLICT.value());
        errObject.setMessage(exception.getMessage());
        errObject.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(errObject, HttpStatus.CONFLICT);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", new Date());
        body.put("status", HttpStatus.BAD_REQUEST.value());

        List<String> errors = exception.getBindingResult()
                                    .getFieldErrors()
                                    .stream()
                                    .map(x -> x.getDefaultMessage())
                                    .collect(Collectors.toList());
        body.put("messages", errors);

        return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);

    }



}
