package model.statements;

import model.exceptions.AppException;
import model.state.PrgState;

public interface IStatement {
    PrgState execute(PrgState state) throws AppException;

    String toString();
}