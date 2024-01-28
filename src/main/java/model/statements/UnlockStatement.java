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

public class UnlockStatement implements IStatement {
    private static final Lock lock = new ReentrantLock();
    private final String var;

    public UnlockStatement(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws AppException {
        lock.lock();
        ISymTable symTable = state.getSymTable();
        ILockTable lockTable = state.getLockTable();
        if (symTable.containsKey(var)) {
            if (symTable.getValue(var).getType().equals(new IntegerType())) {
                IntegerValue fi = (IntegerValue) symTable.getValue(
                        var);
                int foundIndex = fi.getValue();
                if (lockTable.containsKey(foundIndex)) {
                    if (lockTable.get(foundIndex) == state.getId())
                        lockTable.update(foundIndex, -1);
                } else {
                    throw new AppException("Index not in the lock table!");
                }
            } else {
                throw new AppException("Var is not of int Types!");
            }
        } else {
            throw new AppException("Variable is not defined!");
        }
        lock.unlock();
        return null;
    }

    @Override
    public IGenericDictionary<String, IType> typecheck(IGenericDictionary<String, IType> typeEnv) throws AppException {
        if (typeEnv.lookup(var).equals(new IntegerType())) {
            return typeEnv;
        } else {
            throw new AppException("Var is not of int Types!");
        }
    }

//    @Override
//    public IStatement deepCopy() {
//        return new LockStatement(var);
//    }

    @Override
    public String toString() {
        return String.format("lock(%s)", var);
    }
}