package controller;

import model.exceptions.AppException;
import model.state.PrgState;
import model.statements.IStatement;

import java.util.List;

public interface IController {
    void executeOneStep() throws AppException;

    void executeAllSteps() throws AppException;

    void displayCurrentState() throws AppException;

    void removeCompletedPrograms();

    void setDisplayFlag(boolean displayFlag);

    void setProgram(IStatement statement) throws AppException;

    boolean getDisplayFlag();

    List<PrgState> getPrgStates();
}