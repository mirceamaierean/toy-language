package view.exceptions;

import model.exceptions.AppException;

public class ViewException extends AppException {
    ViewException(String message) {
        super(message);
    }
}