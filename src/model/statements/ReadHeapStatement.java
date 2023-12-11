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
    public void execute(PrgState state) throws AppException {
        expr.evaluate(state);
    }

    @Override
    public String toString() {
        return expr.toString();
    }
}