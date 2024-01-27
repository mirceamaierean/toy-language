package model.expressions;

import model.adt.dictionary.IGenericDictionary;
import model.exceptions.AppException;
import model.state.PrgState;
import model.values.IValue;
import model.values.types.IType;

public interface IExpression {
    IValue evaluate(PrgState state) throws AppException;

    String toString();

    IType typecheck(IGenericDictionary<String, IType> typeDictionary) throws AppException;
}
