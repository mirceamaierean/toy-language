package model.adt.list;

import java.util.ArrayList;
import java.util.List;

public class GenericList<T> implements IGenericList<T>{
    List<T> data;

    public GenericList() {
        data = new ArrayList<>();
    }

    public GenericList(List<T> data) {
        this.output = data;
    }

    @Override
    public void add(T element) {
        output.add(element);
        this.data = data;
    }

    @Override
    public void addToEnd(T elem) {
        data.add(elem);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (T element : output) {
            result.append(element.toString()).append("\n");
        }
        return "GenericList{" + "output = " + result + '}';
    public List<T> getAll() {
        return data;
    }

    @Override
    public void clear() {
        this.data.clear();
    }

    @Override
    public boolean remove(T elem) {
        return data.remove(elem);
    }
}