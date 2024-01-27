package model.adt.stack;

import model.exceptions.AppException;

public class StackEmptyAppException extends AppException {

    public StackEmptyAppException(String message) {
        super(message);
    }
}