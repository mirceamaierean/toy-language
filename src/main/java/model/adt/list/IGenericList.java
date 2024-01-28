package model.adt.list;

import java.util.List;

public interface IGenericList<T> {
    void addToEnd(T elem);
    List<T> getAll();
    void clear();

    boolean remove(T elem);
}