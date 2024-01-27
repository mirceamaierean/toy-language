package model.statements;

import model.adt.dictionary.IGenericDictionary;
import model.exceptions.AppException;
import model.expressions.IExpression;
import model.state.PrgState;
import model.values.types.IType;

public class ReadHeapStatement implements IStatement {
    IExpression expr;

    public ReadHeapStatement(IExpression expr) {
        this.expr = expr;
    }

    @Override
    public PrgState execute(PrgState state) throws AppException {
        expr.evaluate(state);
        return null;
    }

    @Override
    public String toString() {
        return expr.toString();
    }

    @Override
    public IGenericDictionary<String, IType> typecheck(IGenericDictionary<String, IType> typeDictionary) throws AppException {
        expr.typecheck(typeDictionary);
        return typeDictionary;
    }
}