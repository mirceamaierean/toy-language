package model.expressions;

import model.exceptions.AppException;
import model.state.PrgState;
import model.values.IValue;

public class VariableExpression implements IExpression {
    String name;

    public VariableExpression(String name) {
        this.name = name;
    }

    @Override
    public IValue evaluate(PrgState state) throws AppException {
        return state.getSymTable().getValue(this.name);
    }

    @Override
    public String toString() {
        return name;
    }

}