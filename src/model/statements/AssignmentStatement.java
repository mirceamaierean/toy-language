package model.statements;

import model.exceptions.AppException;
import model.expressions.IExpression;
import model.state.PrgState;
import model.statements.exceptions.InvalidTypeException;

public class AssignmentStatement implements IStatement {
    String variableName;
    IExpression expression;

    public AssignmentStatement(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public void execute(PrgState state) throws AppException {

        String stateType = state.getSymTable().getValue(variableName).getType().toString();
        String expressionValue = this.expression.toString();

        if (stateType == "IntegerType")
        {
            if (expressionValue == "false" && expressionValue == "true")
                throw new InvalidTypeException("Variable " + variableName + " is not of type bool");
        }

        state.getSymTable().setValue(variableName, expression.evaluate(state));
    }

    @Override
    public String toString() {
        return variableName + " = " + expression.toString();
    }
}