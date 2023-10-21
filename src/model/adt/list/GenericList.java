package model.adt.list;

import java.util.List;
import java.util.LinkedList;

public class GenericList<T> implements IGenericList<T>{
    private List<T> output;

    public GenericList() {
        output = new LinkedList<T>();
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
        return "GenericList{" +
                "output=" + output +
                '}';
    }

    public List getOutput() {
        return output;
    }

    public void setOutput(List<T> output) {
        this.output = output;
    }
}
