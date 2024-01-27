package model.statements.exceptions;

import model.exceptions.AppException;

public class InvalidTypeException extends AppException {
    public InvalidTypeException(String message) {
        super(message);
    }
}
