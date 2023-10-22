package model.statements;

import model.exceptions.AppException;
import model.state.PrgState;

public interface IStatement {
    void execute(PrgState state) throws AppException;
}
