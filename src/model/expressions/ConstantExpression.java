package model.expressions;

import model.exceptions.AppException;
import model.state.PrgState;
import model.values.IValue;

public class ConstantExpression implements IExpression {
    IValue value;

    public ConstantExpression(IValue value) {
        this.value = value;
    }

    @Override
    public IValue evaluate(PrgState state) {
        return value;
    }

    @Override
    public String toString(){
        return value.toString();
    }

};