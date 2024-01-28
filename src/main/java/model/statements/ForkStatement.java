package model.statements;

import model.adt.dictionary.IGenericDictionary;
import model.exceptions.AppException;
import model.state.ExecutionStack;
import model.state.PrgState;
import model.values.types.IType;

public class ForkStatement implements IStatement {
    IStatement innerStatement;

    public ForkStatement(IStatement innerStatement) {
        this.innerStatement = innerStatement;
    }

    @Override
    public PrgState execute(PrgState state) throws AppException {
        return new PrgState(new ExecutionStack(), state.getSymTable().copy(), state.getOutput(), innerStatement, state.getFileTable(), state.getHeap(), state.getBarrierTable());
    }

    @Override
    public String toString() {
        return "fork(" + innerStatement.toString() + ")";
    }

    @Override
    public IGenericDictionary<String, IType> typecheck(IGenericDictionary<String, IType> typeDictionary) throws AppException {
        return innerStatement.typecheck(typeDictionary);
    }
}