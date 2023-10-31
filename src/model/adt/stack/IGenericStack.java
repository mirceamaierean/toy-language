package model.adt.stack;

public interface IGenericStack<T> {
    void push(T element);

    boolean isEmpty();

    int size();

    T top() throws StackEmptyAppException;

    T pop() throws StackEmptyAppException;
}
