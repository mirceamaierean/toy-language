package model.state;

import model.adt.dictionary.GenericDictionary;
import model.adt.dictionary.IGenericDictionary;
import model.adt.dictionary.KeyNotFoundAppException;
import model.exceptions.AppException;
import model.state.exceptions.AddressOutOfBoundsAppException;
import model.values.IValue;

import java.util.Map;

public class Heap implements IHeap {
    IGenericDictionary<Integer, IValue> heap;
    int firstFree;

    public Heap() {
        this.heap = new GenericDictionary<>();
        this.firstFree = 1;
    }

    @Override
    public int allocate(IValue value) {
        heap.insert(firstFree, value);
        firstFree += 1;
        return firstFree - 1;
    }

    @Override
    public IValue read(int address) throws AddressOutOfBoundsAppException {
        try {
            return heap.lookup(address);
        } catch (KeyNotFoundAppException e) {
            throw new AddressOutOfBoundsAppException("Address " + Integer.toString(address) + " out of bounds.");
        }
    }

    @Override
    public void write(int address, IValue value) throws AddressOutOfBoundsAppException {
        if (!heap.getKeys().contains(address)) {
            throw new AddressOutOfBoundsAppException("Address " + Integer.toString(address) + " out of bounds.");
        }
        heap.insert(address, value);
    }

    @Override
    public void deallocate(int address) throws AddressOutOfBoundsAppException {
        try {
            heap.delete(address);
        } catch (KeyNotFoundAppException e) {
            throw new AddressOutOfBoundsAppException("Address " + Integer.toString(address) + " out of bounds.");
        }
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder("Heap:\n");
        try {
            for (int key : heap.getKeys()) {
                answer.append(key).append("(").append(heap.lookup(key).getType().toString()).append(")").append(":-> ").append(heap.lookup(key).toString()).append("\n");
            }
        } catch (AppException exception) {
            throw new RuntimeException(exception.getMessage());
        }
        return answer.toString();
    }

    public Map<Integer, IValue> getMap() {
        return this.heap.getMap();
    }
}