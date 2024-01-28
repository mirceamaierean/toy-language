package model.state;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MyToySemaphore<T> implements IToySemaphore<T> {

    AtomicInteger freeLocation; // An int value that may be updated atomically
    Map<Integer, T> semaphoreTable;

    public MyToySemaphore() {
        this.freeLocation = new AtomicInteger(0);
        this.semaphoreTable = new HashMap<>();
    }
    @Override
    public int allocate(T value) {
        this.semaphoreTable.put(this.freeLocation.incrementAndGet(), value);
        return this.freeLocation.get();
    }

    @Override
    public T lookup(int address) {
        return this.semaphoreTable.get(address);
    }

    @Override
    public void update(int address, T value) {
        this.semaphoreTable.put(address, value);
    }

    @Override
    public boolean exists(int address) {
        return this.semaphoreTable.containsKey(address);
    }

    @Override
    public Map<Integer, T> getContent() {
        return this.semaphoreTable;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(var elem: this.semaphoreTable.keySet()) {
            if (elem != null)
                s.append(elem.toString()).append(" -> ").append(this.semaphoreTable.get(elem).toString()).append('\n');
        }
        return s.toString();
    }


}
