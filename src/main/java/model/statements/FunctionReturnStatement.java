package model.statements;

import model.adt.dictionary.IGenericDictionary;
import model.exceptions.AppException;
import model.state.PrgState;
import model.values.types.IType;

public class FunctionReturnStatement implements IStatement {
    @Override
    public PrgState execute(PrgState state) throws AppException {
        state.getAllSymTables().pop();

        return null;
    }

    @Override
    public IGenericDictionary<String, IType> typecheck(IGenericDictionary<String, IType> typeEnv) throws AppException {
        return typeEnv;
    }

//    @Override
//    public Statement deepCopy() {
//        return new FunctionReturnStatement();
//    }

    @Override
    public String toString() {
        return "return";
    }
}