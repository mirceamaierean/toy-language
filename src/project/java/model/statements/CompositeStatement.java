package model.statements;

import model.adt.dictionary.IGenericDictionary;
import model.exceptions.AppException;
import model.state.PrgState;
import model.values.types.IType;

public class CompositeStatement implements IStatement {
    IStatement firstStatement;
    IStatement secondStatement;

    public CompositeStatement(IStatement firstStatement, IStatement secondStatement) {
        this.firstStatement = firstStatement;
        this.secondStatement = secondStatement;
    }

    @Override
    public PrgState execute(PrgState state) {
        state.getExeStack().push(secondStatement);
        state.getExeStack().push(firstStatement);
        return null;
    }

    @Override
    public String toString() {
        return firstStatement + "; " + secondStatement;
    }

    @Override
    public IGenericDictionary<String, IType> typecheck(IGenericDictionary<String, IType> typeDictionary) throws AppException {
        return secondStatement.typecheck(firstStatement.typecheck(typeDictionary));
    }
}