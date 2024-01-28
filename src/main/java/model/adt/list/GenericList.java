package model.adt.list;

import java.util.List;
import java.util.LinkedList;

public class GenericList<T> implements IGenericList<T> {
    private List<T> output;

    public GenericList() {
        output = new LinkedList<>();
    }

    @Override
    public void add(T element) {
        output.add(element);
    }

    @Override
    public void clear() {
        output.clear();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (T element : output) {
            result.append(element.toString()).append("\n");
        }
        return "GenericList{" +
                "output = " + result +
                '}';
    }

    @Override
    public List<T> getAll() {
        return output;
    }

    public void setOutput(List<T> output) {
        this.output = output;
    }

    public int size() {
        return output.size();
    }
}
