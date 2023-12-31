package model.statements;

import model.exceptions.AppException;
import model.expressions.IExpression;
import model.state.PrgState;
import model.values.IValue;
import model.values.IntegerValue;
import model.values.StringValue;
import model.values.types.StringType;

public class ReadFileStatement implements IStatement {
    IExpression expression;
    String name;

    public ReadFileStatement(IExpression expression, String name) {
        this.expression = expression;
        this.name = name;
    }

    @Override
    public PrgState execute(PrgState state) throws AppException {
        IValue value = this.expression.evaluate(state);
        if (!(value.getType() instanceof StringType)) {
            throw new AppException("Filename did not evaluate to string");
        }
        state.getSymTable().setValue(name, new IntegerValue(state.getFileTable().readFile(((StringValue) value).getValue())));
        return null;
    }

    @Override
    public String toString() {
        return "readFile(" + this.expression.toString() + ", " + this.name + ")";
    }
}