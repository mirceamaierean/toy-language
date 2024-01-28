package model.state;

import model.exceptions.AppException;

import java.util.HashMap;
import java.util.Set;

public class LockTable implements ILockTable {
    private HashMap<Integer, Integer> lockTable;
    private int freeLocation = 0;

    public LockTable() {
        this.lockTable = new HashMap<>();

    }

    @Override
    public int getFreeValue() {
        synchronized (this) {
            freeLocation++;
            return freeLocation;
        }
    }

    @Override
    public void put(int key, int value) throws AppException {
        synchronized (this) {
            if (!lockTable.containsKey(key)) {
                lockTable.put(key, value);
            } else {
                throw new AppException(String.format("Lock table already contains the key %d!", key));
            }
        }
    }

    @Override
    public HashMap<Integer, Integer> getContent() {
        synchronized (this) {
            return lockTable;
        }
    }

    @Override
    public void setContent(HashMap<Integer, Integer> newMap) {
        synchronized (this) {
            this.lockTable = newMap;
        }
    }

    @Override
    public boolean containsKey(int position) {
        synchronized (this) {
            return lockTable.containsKey(position);
        }
    }

    @Override
    public int get(int position) throws AppException {
        synchronized (this) {
            if (!lockTable.containsKey(position))
                throw new AppException(String.format("%d is not present in the table!", position));
            return lockTable.get(position);
        }
    }

    @Override
    public void update(int position, int value) throws AppException {
        synchronized (this) {
            if (lockTable.containsKey(position)) {
                lockTable.replace(position, value);
            } else {
                throw new AppException(String.format("%d is not present in the table!", position));
            }
        }
    }

    @Override
    public Set<Integer> keySet() {
        synchronized (this) {
            return lockTable.keySet();
        }
    }

    @Override
    public String toString() {
        return lockTable.toString();
    }
}