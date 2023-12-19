package model.statements;

import model.exceptions.AppException;
import model.state.ExecutionStack;
import model.state.PrgState;

public class ForkStatement implements IStatement {
    IStatement innerStatement;

    public ForkStatement(IStatement innerStatement) {
        this.innerStatement = innerStatement;
    }

    @Override
    public PrgState execute(PrgState state) throws AppException {
        return new PrgState(new ExecutionStack(), state.getSymTable().copy(), state.getOutput(), innerStatement, state.getFileTable(), state.getHeap());
    }

    @Override
    public String toString() {
        return "fork(" + innerStatement.toString() + ")";
    }
}