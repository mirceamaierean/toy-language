package model.state;

import model.adt.dictionary.IGenericDictionary;
import model.exceptions.AppException;

public interface ILatchTable  extends IGenericDictionary<Integer, Integer> {
    void put(Integer key, Integer value) throws AppException;

    int put(Integer value) throws AppException;
    int get(int position) throws AppException;
    int getFirstFreeLocation();
}