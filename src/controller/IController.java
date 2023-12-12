package controller;

import model.exceptions.AppException;
import model.statements.IStatement;

public interface IController {
    void executeOneStep() throws AppException;

    void executeAllSteps() throws AppException;

    void displayCurrentState() throws AppException;

    void removeCompletedPrograms();

    void setDisplayFlag(boolean displayFlag);

    void setProgram(IStatement statement) throws AppException;

    boolean getDisplayFlag();
}