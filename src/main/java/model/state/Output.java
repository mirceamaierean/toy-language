package model.state;

import model.adt.list.GenericList;
import model.adt.list.IGenericList;

import java.util.List;

public class Output implements IOutput {
    IGenericList<String> data;

    public Output() {
        this.data = new GenericList<>();
    }

    @Override
    public List<String> getOutputAsList() {
        return this.data.getAll();
    }

    @Override
    public String getOutput() {
        StringBuilder answer = new StringBuilder();
        for (String elem : this.data.getAll()) {
            answer.append(elem);
            answer.append("\n");
        }
        return answer.toString();
    }

    @Override
    public void appendToOutput(String string) {
        this.data.addToEnd(string);
    }

    @Override
    public void setOutput(String string) {
        this.data.clear();
        this.data.addToEnd(string);
    }

    @Override
    public String toString() {
        return "Output:\n" + this.getOutput();
    }
}