package model.statements;

import model.exceptions.AppException;
import model.expressions.IExpression;
import model.state.PrgState;
import model.state.exceptions.SymbolNotFoundAppException;
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
        // In case the expression is true of false, we need to have a boolean variable
        String stateType = state.getSymTable().getValue(variableName).getType().toString();
        if (this.expression.toString() == "false" || this.expression.toString() == "true") {
            if (stateType != "BooleanType")
                throw new InvalidTypeException("Variable " + variableName + " is not of type bool");
        } else if (stateType != "IntegerType")
            throw new InvalidTypeException("Variable " + variableName + " is not of type int");


        state.getSymTable().setValue(variableName, expression.evaluate(state));
    }

    @Override
    public String toString() {
        return variableName + " = " + expression.toString();
    }
}