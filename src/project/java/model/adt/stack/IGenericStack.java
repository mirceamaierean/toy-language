package model.adt.stack;

import java.util.List;

public interface IGenericStack<T> {
    void push(T element);

    boolean isEmpty();

    int size();

    T top() throws StackEmptyAppException;

    T pop() throws StackEmptyAppException;

    public List<T> toList();
}
