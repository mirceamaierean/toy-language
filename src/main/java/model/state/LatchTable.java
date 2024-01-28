package model.state;


import model.adt.dictionary.GenericDictionary;
import model.exceptions.AppException;

public class LatchTable extends GenericDictionary<Integer, Integer> implements ILatchTable {
    private int nextFreeLocation;

    public LatchTable() {
        super();
        this.nextFreeLocation = 1;
    }

    @Override
    public void put(Integer key, Integer value) throws AppException {
        if (!key.equals(nextFreeLocation))
            throw new AppException("Invalid lock table location!");
        super.insert(key, value);
        synchronized (this) {
            nextFreeLocation++;
        }
    }

    @Override
    public int put(Integer value) throws AppException {
        super.insert(nextFreeLocation, value);
        synchronized (this) {
            nextFreeLocation++;
        }
        return nextFreeLocation - 1;
    }

    @Override
    public int get(int position) throws AppException {
        synchronized (this) {
            if (!this.getMap().containsKey(position))
                throw new AppException(String.format("%d is not present in the table!", position));
            return this.getMap().get(position);
        }
    }

    @Override
    public int getFirstFreeLocation() {
        int locationAddress = 1;
        while (this.getMap().get(locationAddress) != null)
            locationAddress++;
        return locationAddress;
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();
        for (Integer key : this.getMap().keySet()) {
            answer.append(key.toString());
            answer.append(" -> ");
            answer.append(this.getMap().get(key).toString());
            answer.append("\n");
        }
        return answer.toString();
    }

}