package model.statements;

import model.exceptions.AppException;
import model.state.PrgState;

public class NoOperationStatement implements IStatement {
    @Override
    public void execute(PrgState state) {
    }

    @Override
    public String toString() {
        return "NOP";
    }
}