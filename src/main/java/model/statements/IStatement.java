package model.statements;

import model.adt.dictionary.IGenericDictionary;
import model.exceptions.AppException;
import model.state.PrgState;
import model.values.types.IType;

public interface IStatement {
    PrgState execute(PrgState state) throws AppException;

    String toString();
    IGenericDictionary<String, IType> typecheck(IGenericDictionary<String, IType> typeDictionary) throws AppException;
}