package in.nirajansangraula.expensetrackerapi.exceptions;


public class ItemAlreadyExistsException extends RuntimeException{
    
    public ItemAlreadyExistsException(String message){
        super(message);
    }
}
