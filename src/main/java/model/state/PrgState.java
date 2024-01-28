package model.state;

import model.exceptions.AppException;
import model.statements.IStatement;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class PrgState {
    int id;
    static int lastId = 0;
    IExecutionStack executionStack;
    ISymTable symTable;
    IOutput output;
    IFileTable fileTable;
    IHeap heap;
    ILatchTable latchTable;

    static public Lock lock = new ReentrantLock();

    public PrgState(IExecutionStack executionStack, ISymTable symTable, IOutput output, IStatement statement, IFileTable fileTable, IHeap heap, ILatchTable latchTable)
    {
        this.executionStack = executionStack;
        this.symTable = symTable;
        this.output = output;
        this.executionStack.push(statement);
        this.fileTable = fileTable;
        this.heap = heap;
        this.latchTable = latchTable;
        this.id = setId();
    }

    public synchronized int setId() {
        lastId++;
        return lastId;
    }

    public int getId() {
        return this.id;
    }

    public IExecutionStack getExeStack() {
        return executionStack;
    }

    public void setExeStack(IExecutionStack executionStack) {
        this.executionStack = executionStack;
    }

    public ISymTable getSymTable() {
        return symTable;
    }

    public void setSymTable(ISymTable symTable) {
        this.symTable = symTable;
    }

    public IOutput getOutput() {
        return output;
    }

    public void setOutput(IOutput output) {
        this.output = output;
    }

    public IFileTable getFileTable() {
        return fileTable;
    }

    public void setFileTable(IFileTable fileTable) {
        this.fileTable = fileTable;
    }

    public IHeap getHeap() {
        return heap;
    }

    public void setHeap(IHeap heap) {
        this.heap = heap;
    }

    public ILatchTable getLatchTable() {
        return latchTable;
    }

    public void setLatchTable(ILatchTable latchTable) {
        this.latchTable = latchTable;
    }

    public boolean isNotCompleted() {
        return this.executionStack.size() > 0;
    }

    @Override
    public String toString() {
        return "Id: " + this.id + "\n" + this.executionStack.toString().strip() + "\n" + this.symTable.toString().strip() + "\n" + this.output.toString().strip() + "\n" + this.fileTable.toString().strip() + "\n" + this.heap.toString() + "\n" + this.latchTable.toString() + "\n";
    }

    public PrgState executeOneStep() throws AppException {
        IStatement statement = executionStack.pop();
        return statement.execute(this);
    }
}