package model.state;

import javafx.util.Pair;
import model.adt.list.IGenericList;
import model.exceptions.AppException;

import java.util.List;

public interface ISemaphoreTable {
    int createSemaphore(int count) throws AppException;
    boolean acquire(int semaphoreId, int threadId) throws AppException;
    void release(int semaphoreId, int threadId) throws AppException;
    List<Pair<Pair<Integer, Integer>, IGenericList<Integer>>> getSemaphoreDictionaryAsList();
}