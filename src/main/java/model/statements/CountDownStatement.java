package model.statements;


import model.adt.dictionary.IGenericDictionary;
import model.exceptions.AppException;
import model.state.PrgState;
import model.values.IValue;
import model.values.IntegerValue;
import model.values.types.IType;
import model.values.types.IntegerType;

public class CountDownStatement implements IStatement {
    private String variableName;

    public CountDownStatement(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public PrgState execute(PrgState state) throws AppException {
        IValue variableValue = state.getSymTable().getValue(variableName);
        // if var is not in symbol table, or it has not the type int then print an error message and terminate the execution.
        if (variableValue == null)
            throw new AppException(String.format("Variable '%s' has not been declared", variableName));
        if (!variableValue.getType().equals(new IntegerType()))
            throw new AppException(String.format("Variable '%s' should be of integer type", variableName));

        PrgState.lock.lock();

        // latchLocation = lookup(symbolTable,var)
        Integer latchLocation = ((IntegerValue) variableValue).getValue();
        Integer latchValue = state.getLatchTable().get(latchLocation);

        // if latchLocation is not an index in the LatchTable then
        if (latchValue == null) {
            PrgState.lock.unlock();
            throw new AppException("Invalid latch table location!");
        }
        // if LatchTable[latchLocation] > 0 then
        if (latchValue > 0)
            // LatchTable[latchLocation]=LatchTable[latchLocation]-1;
            state.getLatchTable().insert(latchLocation, latchValue-1);

        // write into out table the current PrgState id
        state.getOutput().appendToOutput((new IntegerValue(state.getId())).toString());

        PrgState.lock.unlock();

        return null;
    }

    @Override
    public IGenericDictionary<String, IType> typecheck(IGenericDictionary<String, IType> typeTable) throws AppException {
        try {
            IType variableType = typeTable.getMap().get(variableName);
            if (variableType == null)
                throw new AppException(String.format("Variable %s has not been declared!", variableName));
            if (!variableType.equals(new IntegerType()))
                throw new AppException(String.format("Variable %s should be of integer type!", variableName));

        } catch (AppException e) {
            throw new AppException(e.getMessage());
        }

        return typeTable;
    }

//    @Override
//    public Statement deepCopy() {
//        return new CountDownStatement(variableName);
//    }

    @Override
    public String toString() {
        return String.format("countDown(%s)", variableName);
    }
}