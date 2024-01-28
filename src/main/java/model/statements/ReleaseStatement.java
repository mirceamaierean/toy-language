package model.statements;

import model.adt.dictionary.IGenericDictionary;
import model.exceptions.AppException;
import model.state.PrgState;
import model.values.IValue;
import model.values.IntegerValue;
import model.values.types.IType;
import model.values.types.IntegerType;

public class ReleaseStatement implements  IStatement{
    String name;

    public ReleaseStatement(String name) {
        this.name = name;
    }

    @Override
    public PrgState execute(PrgState state) throws AppException {
        IValue value = state.getSymTable().getValue(this.name);
        if(!(value.getType() instanceof IntegerType)) {
            throw new AppException("Invalid expression value for release statement");
        }
        int semaphoreId = ((IntegerValue)value).getValue();
        state.getSemaphoreTable().release(semaphoreId, state.getId());
        return null;
    }
    @Override
    public String toString(){
        return "release(\"" + this.name + "\")";
    }

    @Override
    public IGenericDictionary<String, IType> typecheck(IGenericDictionary<String, IType> typeDictionary) throws AppException {
        if(!typeDictionary.exists(this.name) || !typeDictionary.lookup(this.name).equals(new IntegerType())){
            throw new AppException("Invalid expression type for acquire variable");
        }
        return typeDictionary;
    }
}