package model.expressions;

import model.adt.dictionary.IGenericDictionary;
import model.state.PrgState;
import model.values.IValue;
import model.values.types.IType;

public class ConstantExpression implements IExpression {
    IValue value;

    public ConstantExpression(IValue value) {
        this.value = value;
    }

    @Override
    public IValue evaluate(PrgState state) {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public IType typecheck(IGenericDictionary<String, IType> typeDictionary) {
        return value.getType();
    }
}