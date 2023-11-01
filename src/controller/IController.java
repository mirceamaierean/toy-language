package controller;

import model.exceptions.AppException;
import model.statements.IStatement;

public interface IController {
    void executeAllSteps() throws AppException;

    void oneStep() throws AppException;

    void setProgram(IStatement statement) throws AppException;

    void displayCurrentState() throws AppException;
}
