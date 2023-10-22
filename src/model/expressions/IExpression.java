package model.expressions;

import model.exceptions.AppException;
import model.state.PrgState;
import model.values.IValue;

public interface IExpression {
    public IValue evaluate(PrgState state) throws AppException;
    public String toString();
}
