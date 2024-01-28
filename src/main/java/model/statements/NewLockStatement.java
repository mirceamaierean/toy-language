package model.statements;
import model.adt.dictionary.IGenericDictionary;
import model.exceptions.AppException;
import model.state.ILockTable;
import model.state.ISymTable;
import model.state.PrgState;
import model.values.IntegerValue;
import model.values.types.IType;
import model.values.types.IntegerType;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewLockStatement implements IStatement {
    private final String var;
    private static final Lock lock = new ReentrantLock();

    public NewLockStatement(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws AppException {
        lock.lock();
        ILockTable lockTable = state.getLockTable();
        ISymTable symTable = state.getSymTable();
        int freeAddress = lockTable.getFreeValue();
        lockTable.put(freeAddress, -1);
        if (symTable.containsKey(var) && symTable.getValue(var).getType().equals(new IntegerType())) {
            symTable.setValue(var, new IntegerValue(freeAddress));
        }
        else
            throw new AppException("Variable not declared!");
        lock.unlock();
        return null;
    }

    @Override
    public IGenericDictionary<String, IType> typecheck(IGenericDictionary<String, IType> typeEnv) throws AppException {
        if (typeEnv.lookup(var).equals(new IntegerType()))
            return typeEnv;
        else
            throw new AppException("Var is not of int Types!");
    }
//
//    @Override
//    public Statement deepCopy() {
//        return new NewLockStatement(var);
//    }

    @Override
    public String toString() {
        return String.format("newLock(%s)", var);
    }
}