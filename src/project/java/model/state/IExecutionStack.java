package model.state;

import model.adt.stack.StackEmptyAppException;
import model.statements.IStatement;

import java.util.List;

public interface IExecutionStack {
    IStatement pop() throws StackEmptyAppException;

    void push(IStatement statement);

    boolean empty();

    int size();

    String toString();

    List<IStatement> toList();
}