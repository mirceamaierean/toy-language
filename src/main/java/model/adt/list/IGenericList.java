package model.adt.list;

import java.util.List;

public interface IGenericList<T> {
    void add(T element);

    void clear();

    List<T> getAll();
}
