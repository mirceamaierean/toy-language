package model.state;

import model.exceptions.AppException;

import java.util.HashMap;
import java.util.Set;

public interface ILockTable {
    int getFreeValue();
    void put(int key, int value) throws AppException;
    HashMap<Integer, Integer> getContent();
    boolean containsKey(int position);
    int get(int position) throws AppException;
    void update(int position, int value) throws AppException;
    void setContent(HashMap<Integer, Integer> newMap);
    Set<Integer> keySet();
}