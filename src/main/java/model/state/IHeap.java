package model.state;

import model.state.exceptions.AddressOutOfBoundsAppException;
import model.values.IValue;

import java.util.Map;

public interface IHeap {
    int allocate(IValue value);
    IValue read(int address) throws AddressOutOfBoundsAppException;
    void write(int address, IValue value) throws AddressOutOfBoundsAppException;
    void deallocate(int address) throws AddressOutOfBoundsAppException;
    Map<Integer, IValue> getMap();
}