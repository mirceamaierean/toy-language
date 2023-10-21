package model.adt.stack;

import java.util.Stack;

public class GenericStack<T> implements IGenericStack<T> {
    Stack<T> stack;

    public GenericStack() {
        this.stack = new Stack<T>();
    }

    @Override
    public void push(T element) {
        this.stack.push(element);
    }

    @Override
    public T pop() {
        return this.stack.pop();
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public String toString() {
        return "GenericStack{" +
                "stack=" + stack +
                '}';
    }

    public Stack<T> getStack() {
        return stack;
    }

    public void setStack(Stack<T> stack) {
        this.stack = stack;
    }
}
