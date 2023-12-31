package model.statements;

import model.exceptions.AppException;
import model.expressions.IExpression;
import model.state.PrgState;
import model.values.IValue;
import model.values.RefValue;

public class NewStatement implements IStatement {
    String name;
    IExpression expression;

    public NewStatement(String name, IExpression expression) {
        this.name = name;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws AppException {
        IValue value = expression.evaluate(state);
        state.getSymTable().setValue(name, new RefValue(state.getHeap().allocate(value), value.getType()));
        return null;
    }

    @Override
    public String toString() {
        return "new(" + this.name.toString() + ", " + this.expression.toString() + ")";
    }
}