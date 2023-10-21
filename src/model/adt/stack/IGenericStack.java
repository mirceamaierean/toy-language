package model.adt.stack;

public interface IGenericStack<T> {
    void push(T element);

    T pop();

    boolean isEmpty();
}
