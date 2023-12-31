package model.statements;

import model.exceptions.AppException;
import model.expressions.IExpression;
import model.state.PrgState;

public class AssignmentStatement implements IStatement {
    String variableName;
    IExpression expression;

    public AssignmentStatement(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws AppException {
        state.getSymTable().setValue(variableName, expression.evaluate(state));
        return null;
    }

    @Override
    public String toString() {
        return variableName + " = " + expression.toString();
    }
}