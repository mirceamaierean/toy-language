package model.state;

import model.adt.dictionary.IGenericDictionary;
import model.exceptions.AppException;
import model.statements.IStatement;
import model.values.IValue;

public class PrgState {
    static int nextId = 0;
    int id;
    IExecutionStack executionStack;
    //    ISymTable symTable;
    SymTablesStack symTables;
    IOutput output;
    IFileTable fileTable;
    IHeap heap;

    IProcedureTable procedureTable;

    public PrgState(IExecutionStack executionStack, ISymTable symTable, IOutput output, IStatement statement, IFileTable fileTable, IHeap heap, IProcedureTable procedureTable) {
        this.id = setId();
        this.executionStack = executionStack;
        this.symTables = new SymTablesStack();
        this.symTables.push(symTable);
        this.output = output;
        this.executionStack.push(statement);
        this.fileTable = fileTable;
        this.heap = heap;
        this.procedureTable = procedureTable;
    }

    public PrgState(IExecutionStack executionStack, ISymTable symTable, IOutput output, IFileTable fileTable, IHeap heap, IProcedureTable procedureTable) {
        this.executionStack = executionStack;
        this.symTables = new SymTablesStack();
        this.symTables.push(symTable);
        this.id = setId();
        this.output = output;
        this.fileTable = fileTable;
        this.heap = heap;
        this.procedureTable = procedureTable;
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
        return (ISymTable) symTables.getStack().peek();
    }

    public SymTablesStack getAllSymTables() {
        return symTables;
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

    public IProcedureTable getProcedureTable() {
        return procedureTable;
    }

    public boolean isNotCompleted() {
        return this.executionStack.size() > 0;
    }

    public String procedureTableToString() {
        StringBuilder procedureTableStringBuilder = new StringBuilder();
        for (String key : procedureTable.getKeys()) {
            try {
                procedureTableStringBuilder.append(String.format("%s - %s: %s\n", key, procedureTable.lookup(key).getKey(), procedureTable.lookup(key).getValue()));
            } catch (AppException ignored) {

            }
        }
        procedureTableStringBuilder.append("\n");
        return procedureTableStringBuilder.toString();
    }


    @Override
    public String toString() {
        return "Id: " + this.id + "\n" + this.executionStack.toString().strip() + "\n" + this.symTables.toString().strip() + "\n" + this.output.toString().strip() + "\n" + this.fileTable.toString().strip() + "\n" + this.heap.toString() + "\n" + this.procedureTableToString() + "\n";
    }

    public PrgState executeOneStep() throws AppException {
        IStatement statement = executionStack.pop();
        return statement.execute(this);
    }
}