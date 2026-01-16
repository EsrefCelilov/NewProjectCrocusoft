package az.ltclab.newprojectcrocusoft.Exeption;

public class DuplicateUserException extends RuntimeException {
    public  DuplicateUserException(String message) {
        super(message);
    }
}
