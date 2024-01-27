package model.expressions;

import model.adt.dictionary.IGenericDictionary;
import model.exceptions.AppException;
import model.state.PrgState;
import model.values.IValue;
import model.values.types.IType;

public class VariableExpression implements IExpression {
    String name;

    public VariableExpression(String name) {
        this.name = name;
    }

    @Override
    public IValue evaluate(PrgState state) throws AppException {
        return state.getSymTable().getValue(this.name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public IType typecheck(IGenericDictionary<String, IType> typeDictionary) throws AppException {
        return typeDictionary.lookup(name);
    }

}