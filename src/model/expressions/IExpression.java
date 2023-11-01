package model.expressions;

import model.exceptions.AppException;
import model.state.PrgState;
import model.values.IValue;

public interface IExpression {
    IValue evaluate(PrgState state) throws AppException;

    String toString();
}
