package model.state;

import model.exceptions.AppException;
import java.util.List;

public interface IFileTable {
    void openFile(String name) throws AppException;

    void closeFile(String name) throws AppException;

    int readFile(String name) throws AppException;

    String toString();

    List<String> getFileList();
}