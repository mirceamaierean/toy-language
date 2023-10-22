package model.statements;

import model.exceptions.AppException;
import model.state.PrgState;

public class CompositeStatement implements IStatement{
    IStatement firstStatement;
    IStatement secondStatement;

    CompositeStatement(IStatement firstStatement, IStatement secondStatement) {
        this.firstStatement = firstStatement;
        this.secondStatement = secondStatement;
    }
    @Override
    public void execute(PrgState state) {
        state.getExeStack().push(secondStatement);
        state.getExeStack().push(firstStatement);
    }

    @Override
    public String toString() {
        return firstStatement + "; " + secondStatement;
    }
}
