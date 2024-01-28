package model.state;

import model.exceptions.AppException;
import model.statements.IStatement;
import utils.Triplet;

public class PrgState {
    static int nextId = 0;
    int id;
    IExecutionStack executionStack;
    ISymTable symTable;
    IOutput output;
    IFileTable fileTable;
    IHeap heap;

    IToySemaphore<Triplet> semaphore;

    public PrgState(IExecutionStack executionStack, ISymTable symTable, IOutput output, IStatement statement, IFileTable fileTable, IHeap heap, IToySemaphore<Triplet> semaphore) {
        this.executionStack = executionStack;
        this.symTable = symTable;
        this.output = output;
        this.executionStack.push(statement);
        this.fileTable = fileTable;
        this.heap = heap;
        this.semaphore = semaphore;
        this.incrementId();
    }

    public synchronized void incrementId() {
        nextId++;
        this.id = nextId;
    }

    public int getId(){
        return id;
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

    public IFileTable getFileTable() {
        return fileTable;
    }

    public IHeap getHeap() {
        return heap;
    }

    public IToySemaphore<Triplet> getSemaphore() {
        return semaphore;
    }

    public void setToySemaphoreTable(IToySemaphore<Triplet> semaphore) {
        this.semaphore = semaphore;
    }

    public boolean isNotCompleted() {
        return this.executionStack.size() > 0;
    }

    @Override
    public String toString() {
        return "Id: " + this.id + "\n" + this.executionStack.toString().strip() + "\n" + this.symTable.toString().strip() + "\n" + this.output.toString().strip() + "\n" + this.fileTable.toString().strip() + "\n" + this.heap.toString() + "\n" + this.semaphore.toString() + "\n";
    }

    public PrgState executeOneStep() throws AppException {
        IStatement statement = executionStack.pop();
        return statement.execute(this);
    }
}