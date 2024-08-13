package ilknurdogan.servicefinder.exception;

public class UniqueEmailException extends RuntimeException{

    public UniqueEmailException(String message) {
        super(message);
    }

    public UniqueEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
