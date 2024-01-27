package model.statements;

import model.adt.dictionary.IGenericDictionary;
import model.exceptions.AppException;
import model.state.PrgState;
import model.values.types.IType;

public class NoOperationStatement implements IStatement {
    public NoOperationStatement() {
    }

    @Override
    public PrgState execute(PrgState state) throws AppException {
        return null;
    }

    @Override
    public String toString() {
        return "NOP";
    }

    @Override
    public IGenericDictionary<String, IType> typecheck(IGenericDictionary<String, IType> typeDictionary) throws AppException {
        return typeDictionary;
    }
}