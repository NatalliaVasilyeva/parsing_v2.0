package by.epam.javaweb.vasilyevanatali.parsing.exception;

public class ParserException extends Exception {

    private static final long serialVersionUID = 987654321L;

    public ParserException() {

    }

    public ParserException(String message) {

        super(message);

    }

    public ParserException(String message, Throwable cause) {

        super(message, cause);

    }

    public ParserException(Throwable cause) {

        super(cause);

    }

}
