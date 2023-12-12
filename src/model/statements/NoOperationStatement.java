package model.statements;

import model.exceptions.AppException;
import model.state.PrgState;

public class NoOperationStatement implements IStatement {
    public NoOperationStatement() {
        ;
    }

    @Override
    public PrgState execute(PrgState state) throws AppException {
        return null;
    }

    @Override
    public String toString() {
        return "NOP";
    }
}