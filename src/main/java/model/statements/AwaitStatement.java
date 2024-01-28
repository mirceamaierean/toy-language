package model.statements;

import javafx.util.Pair;
import model.adt.dictionary.IGenericDictionary;
import model.exceptions.AppException;
import model.state.PrgState;
import model.values.IValue;
import model.values.IntegerValue;
import model.values.types.IType;
import model.values.types.IntegerType;

import java.util.List;

public class AwaitStatement implements IStatement {
    private String variableName;

    public AwaitStatement(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public IGenericDictionary<String, IType> typecheck(IGenericDictionary<String, IType> typeTable) throws AppException {
        
        IType variableType = typeTable.lookup(variableName);
        if (variableType == null)
            throw new AppException(String.format("Variable %s has not been declared!", variableName));
        if (!variableType.equals(new IntegerType()))
            throw new AppException(String.format("Variable %s should be of integer type!", variableName));


        return typeTable;
    }

    @Override
    public PrgState execute(PrgState state) throws AppException {
        try {
            IValue variableValue = state.getSymTable().getValue(variableName);
            if (variableValue == null)
                throw new AppException(String.format("Variable %s has not been declared!", variableName));
            if (!variableValue.getType().equals(new IntegerType()))
                throw new AppException(String.format("Variable %s should be of integer type!", variableName));

            int variableInt = ((IntegerValue) variableValue).getValue();
            Pair<Integer, List<Integer>> barrierTableEntry = state.getBarrierTable().lookup(variableInt);
            if (barrierTableEntry == null)
                throw new AppException("Invalid barrier table location!");

            int barrierTableValue = barrierTableEntry.getKey();
            List<Integer> barrierPrograms = barrierTableEntry.getValue();

            if (barrierTableValue > barrierPrograms.size()) {
                if (!barrierPrograms.contains(state.getId()))
                    barrierPrograms.add(state.getId());
                state.getExeStack().push(this);
            }
        } catch (AppException e) {
            throw new AppException(e.getMessage());
        }

        return null;
    }

//    @Override
//    public IStatement deepCopy() {
//        return new AwaitStatement(variableName);
//    }

    @Override
    public String toString() {
        return String.format("await(%s)", variableName);
    }
}