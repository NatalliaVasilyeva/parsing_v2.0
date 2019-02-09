package by.epam.javaweb.vasilyevanatali.parsing.exception;

public class WrongValueException extends Exception {

    private static final long serialVersionUID = 987654321L;

    public WrongValueException() {

    }

    public WrongValueException(String message) {

        super(message);

    }

    public WrongValueException(String message, Throwable cause) {

        super(message, cause);

    }

    public WrongValueException(Throwable cause) {

        super(cause);

    }

}
