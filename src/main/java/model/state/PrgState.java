package model.state;

import model.exceptions.AppException;
import model.statements.IStatement;

public class PrgState {
    int id;
    static int nextId = 0;
    IExecutionStack executionStack;
    ISymTable symTable;
    IOutput output;
    IFileTable fileTable;
    IHeap heap;
    ILockTable lockTable;



    public PrgState(IExecutionStack executionStack, ISymTable symTable, IOutput output, IStatement statement, IFileTable fileTable, IHeap heap, ILockTable lockTable) {
        this.id = setId();
        this.executionStack = executionStack;
        this.symTable = symTable;
        this.output = output;
        this.executionStack.push(statement);
        this.fileTable = fileTable;
        this.heap = heap;
        this.lockTable = lockTable;
    }

    public synchronized int setId() {
        nextId++;
        return nextId;
    }

    public int getId() {
        return this.id;
    }

    public IExecutionStack getExeStack() {
        return executionStack;
    }

    public ISymTable getSymTable() {
        return symTable;
    }

    public IOutput getOutput() {
        return output;
    }

    public IFileTable getFileTable() {
        return fileTable;
    }

    public IHeap getHeap() {
        return heap;
    }

    public ILockTable getLockTable() {
        return lockTable;
    }

    public void setExeStack(IExecutionStack executionStack) {
        this.executionStack = executionStack;
    }

    public void setSymTable(ISymTable symTable) {
        this.symTable = symTable;
    }

    public void setOutput(IOutput output) {
        this.output = output;
    }

    public void setFileTable(IFileTable fileTable) {
        this.fileTable = fileTable;
    }

    public void setHeap(IHeap heap) {
        this.heap = heap;
    }

    public void setLockTable(ILockTable lockTable) {
        this.lockTable = lockTable;
    }


    public boolean isNotCompleted() {
        return this.executionStack.size() > 0;
    }

    @Override
    public String toString() {
        return "Id: " + this.id + "\n" + this.executionStack.toString().strip() + "\n" + this.symTable.toString().strip() + "\n" + this.output.toString().strip() + "\n" + this.fileTable.toString().strip() + "\n" + this.heap.toString() + "\n" + this.lockTable.toString() + "\n";
    }

    public PrgState executeOneStep() throws AppException {
        IStatement statement = executionStack.pop();
        return statement.execute(this);
    }
}