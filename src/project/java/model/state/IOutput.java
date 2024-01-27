package model.state;

import java.util.List;

public interface IOutput {
    List<String> getOutputAsList();

    String getOutput();

    void appendToOutput(String string);

    void setOutput(String string);

    String toString();
}