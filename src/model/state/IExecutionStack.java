package model.state;

import model.adt.stack.StackEmptyAppException;
import model.statements.IStatement;

public interface IExecutionStack {
    IStatement pop() throws StackEmptyAppException;

    void push(IStatement statement);

    boolean empty();

    int size();

    String toString();
}