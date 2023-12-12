package model.statements;

import model.exceptions.AppException;
import model.expressions.IExpression;
import model.state.PrgState;

public class ReadHeapStatement implements IStatement {
    IExpression expr;

    public ReadHeapStatement(IExpression expr) {
        this.expr = expr;
    }

    @Override
    public PrgState execute(PrgState state) throws AppException {
        expr.evaluate(state);
        return null;
    }

    @Override
    public String toString() {
        return expr.toString();
    }
}