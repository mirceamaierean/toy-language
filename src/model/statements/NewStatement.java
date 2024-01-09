package model.statements;

import model.adt.dictionary.IGenericDictionary;
import model.exceptions.AppException;
import model.expressions.IExpression;
import model.state.PrgState;
import model.values.IValue;
import model.values.RefValue;
import model.values.types.IType;
import model.values.types.RefType;

public class NewStatement implements IStatement {
    String name;
    IExpression expression;

    public NewStatement(String name, IExpression expression) {
        this.name = name;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws AppException {
        IValue value = expression.evaluate(state);
        state.getSymTable().setValue(name, new RefValue(state.getHeap().allocate(value), value.getType()));
        return null;
    }

    @Override
    public String toString() {
        return "new(" + this.name + ", " + this.expression.toString() + ")";
    }

    @Override
    public IGenericDictionary<String, IType> typecheck(IGenericDictionary<String, IType> typeDictionary) throws AppException {
        IType variableType = typeDictionary.lookup(name);
        IType expressionType = expression.typecheck(typeDictionary);

        if (variableType != null && variableType.equals(new RefType(expressionType)))
            return typeDictionary;

        throw new AppException("Mismatched types for new statement");
    }
}