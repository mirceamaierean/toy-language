package model.statements;

import model.state.PrgState;

public class CompositeStatement implements IStatement{
    IStatement firstStatement;
    IStatement secondStatement;

    public CompositeStatement(IStatement firstStatement, IStatement secondStatement) {
        this.firstStatement = firstStatement;
        this.secondStatement = secondStatement;
    }

    @Override
    public PrgState execute(PrgState state) {
        state.getExeStack().push(secondStatement);
        state.getExeStack().push(firstStatement);
        return null;
    }

    @Override
    public String toString(){
        return firstStatement + "; " + secondStatement;
    }
}