package model.statements;

import model.adt.dictionary.IGenericDictionary;
import model.exceptions.AppException;
import model.expressions.IExpression;
import model.state.PrgState;
import model.values.BooleanValue;
import model.values.IValue;
import model.values.types.BooleanType;
import model.values.types.IType;

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

    @Override
    public IGenericDictionary<String, IType> typecheck(IGenericDictionary<String, IType> typeDictionary) throws AppException {

        if ((new BooleanType()).equals(condition.typecheck(typeDictionary)))
            return statement.typecheck(typeDictionary);

        throw new AppException("While condition does not evaluate to a BooleanType");
    }
}