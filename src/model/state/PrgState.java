package model.state;

import model.statements.IStatement;

public class PrgState {
    IExecutionStack executionStack;
    ISymTable symTable;
    IOutput output;

    public PrgState(IExecutionStack executionStack, ISymTable symTable, IOutput output, IStatement statement) {
        this.executionStack = executionStack;
        this.symTable = symTable;
        this.output = output;
        this.executionStack.push(statement);
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

    @Override
    public String toString() {
        return this.executionStack.toString().strip() + "\n" + this.symTable.toString().strip() + "\n" + this.output.toString().strip();
    }
};