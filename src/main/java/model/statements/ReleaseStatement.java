package model.statements;

import model.adt.dictionary.IGenericDictionary;
import model.exceptions.AppException;
import model.state.ISymTable;
import model.state.IToySemaphore;
import model.state.PrgState;
import model.values.IValue;
import model.values.IntegerValue;
import model.values.types.IType;
import model.values.types.IntegerType;
import utils.Triplet;

public class ReleaseStatement implements IStatement {
    private final String var;

    public ReleaseStatement(String var) {
        this.var = var;
    }
    @Override
    public PrgState execute(PrgState state) throws AppException {
        ISymTable symbolTable = state.getSymTable();
        IToySemaphore<Triplet> semTable = state.getSemaphore();
        IValue varVal = symbolTable.getValue(this.var);

        if(!varVal.getType().equals(new IntegerType()))
            throw new AppException("Variable's value is not of type int");

        int foundIndex = ((IntegerValue) varVal).getValue();

        if(!semTable.exists(foundIndex))
            throw new AppException("Index does not exist");

        Triplet triplet = semTable.lookup(foundIndex);
        if(triplet.second.contains(state.getId()))
            triplet.second.remove((Integer) state.getId());

        state.setToySemaphoreTable(semTable);
        return null;
    }

    @Override
    public IGenericDictionary<String, IType> typecheck(IGenericDictionary<String, IType> typeDictionary) throws AppException {
        return typeDictionary;
    }
    @Override
    public String toString() {
        return "ToyRelease (" + this.var + ')';
    }
}