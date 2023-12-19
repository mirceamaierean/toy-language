package model.statements;

import model.exceptions.AppException;
import model.expressions.IExpression;
import model.state.PrgState;

public class PrintStatement implements IStatement {

    IExpression expression;

    public PrintStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws AppException {
        state.getOutput().appendToOutput(expression.evaluate(state).toString());
        return null;
    }

    @Override
    public String toString() {
        return "print(" + expression.toString() + ")";
    }
}