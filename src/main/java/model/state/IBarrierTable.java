package model.state;

import javafx.util.Pair;
import model.adt.dictionary.IGenericDictionary;
import model.exceptions.AppException;

import java.util.List;

public interface IBarrierTable extends IGenericDictionary<Integer, Pair<Integer, List<Integer>>> {
    int put(Pair<Integer, List<Integer>> value) throws AppException;
    int getFirstFreeLocation();
}