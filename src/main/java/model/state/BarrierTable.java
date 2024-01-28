package model.state;

import javafx.util.Pair;
import model.adt.dictionary.GenericDictionary;
import model.exceptions.AppException;

import java.util.List;

public class BarrierTable extends GenericDictionary<Integer, Pair<Integer, List<Integer>>> implements IBarrierTable {
    private int nextFreeLocation;

    public BarrierTable() {
        super();
        this.nextFreeLocation = 1;
    }

    @Override
    public void insert(Integer key, Pair<Integer, List<Integer>> value) {
        super.insert(key, value);
        synchronized (this) {
            nextFreeLocation++;
        }
    }

    @Override
    public int put(Pair<Integer, List<Integer>> value) throws AppException {
        super.insert(nextFreeLocation, value);
        synchronized (this) {
            nextFreeLocation++;
        }
        return nextFreeLocation - 1;
    }

    @Override
    public int getFirstFreeLocation() {
        int locationAddress = 1;
        while (this.getMap().get(locationAddress) != null)
            locationAddress++;
        return locationAddress;
    }

}