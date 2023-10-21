package model.state;

import model.adt.dictionary.IGenericDictionary;
import model.adt.list.IGenericList;
import model.adt.stack.IGenericStack;
import model.statements.IStatement;
import model.values.IValue;

public class PrgState {
    IGenericStack <IStatement> exeStack;
    IGenericDictionary <String, IValue> symTable;
    IGenericList <IValue> out;

    public PrgState(IGenericStack <IStatement> exeStack, IGenericDictionary <String, IValue> symTable, IGenericList <IValue> out, IStatement prg) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;

        this.exeStack.push(prg);
    }

    public IGenericStack <IStatement> getExeStack() {
        return exeStack;
    }

    public void setExeStack(IGenericStack<IStatement> exeStack) {
        this.exeStack = exeStack;
    }

    public IGenericList <IValue> getOut() {
        return this.out;
    }

    public void setOut(IGenericList <IValue> out) {
        this.out = out;
    }

    public IGenericDictionary <String, IValue> getSymTable() {
        return this.symTable;
    }

    public void setSymTable(IGenericDictionary <String, IValue> symTable) {
        this.symTable = symTable;
    }

    @Override
    public String toString() {
        return "PrgState{" +
                "exeStack=" + exeStack +
                ", symTable=" + symTable +
                ", out=" + out +
                '}';
    }
}
