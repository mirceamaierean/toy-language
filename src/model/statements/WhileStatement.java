package model.statements;

import model.exceptions.AppException;
import model.expressions.IExpression;
import model.state.PrgState;
import model.values.BooleanValue;
import model.values.IValue;
import model.values.types.BooleanType;

public class WhileStatement implements IStatement {
    IExpression condition;
    IStatement statement;

    public WhileStatement(IExpression condition, IStatement statement) {
        this.condition = condition;
        this.statement = statement;
    }

    @Override
    public PrgState execute(PrgState state) throws AppException {
        IValue value = this.condition.evaluate(state);
        if (!(value.getType() instanceof BooleanType)) {
            throw new AppException("While condition should evaluate to a BooleanType");
        }
        if (((BooleanValue) value).getValue()) {
            state.getExeStack().push(this);
            state.getExeStack().push(statement);
        }
        return null;
    }

    @Override
    public String toString() {
        return "While(" + condition.toString() + "){" + statement.toString() + "};";
    }
}