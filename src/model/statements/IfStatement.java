package model.statements;

import model.exceptions.AppException;
import model.expressions.IExpression;
import model.state.PrgState;
import model.values.BooleanValue;
import model.values.IValue;
import model.values.types.BooleanType;

public class IfStatement implements IStatement {
    IExpression expression;
    IStatement left;
    IStatement right;

    public IfStatement(IExpression expression, IStatement left, IStatement right) {
        this.expression = expression;
        this.left = left;
        this.right = right;
    }

    @Override
    public PrgState execute(PrgState state) throws AppException {
        IValue value = expression.evaluate(state);
        if (!(value.getType() instanceof BooleanType)) {
            throw new AppException("Invalid expression value for if statement");
        }
        if (((BooleanValue) value).getValue()) {
            state.getExeStack().push(left);
        } else {
            state.getExeStack().push(right);
        }
        return null;
    }

    @Override
    public String toString() {
        return "if(" + this.expression.toString() + ")" + "then {" + this.left.toString() + "} else {" + this.right.toString() + "}";
    }
}