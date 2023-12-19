package controller;

import model.state.IHeap;
import model.state.PrgState;
import model.state.exceptions.AddressOutOfBoundsAppException;
import model.values.IValue;
import model.values.RefValue;
import model.values.types.RefType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GarbageCollector {
    public static void runGarbageCollector(List<PrgState> states) {
        List<Integer> activeAddresses = states.stream().flatMap(e -> GarbageCollector.getActiveAddressesForState(e).stream()).toList();

        IHeap heap = states.get(0).getHeap();
        List<Integer> addresses = heap.getMap().keySet().stream().filter(e -> !activeAddresses.contains(e)).toList();

        addresses.forEach(e -> {
            try {
                heap.deallocate(e);
            } catch (AddressOutOfBoundsAppException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public static List<Integer> getActiveAddressesForState(PrgState state) {
        return state.getSymTable().getMap().values().stream().filter(e -> e.getType() instanceof RefType).map(e -> (RefValue) e).flatMap(value -> {
            List<Integer> addresses = new ArrayList<>();
            while (true) {
                if (value.getAddress() == 0) {
                    break;
                }
                addresses.add(value.getAddress());
                IValue next_value;
                try {
                    next_value = state.getHeap().read(value.getAddress());
                } catch (AddressOutOfBoundsAppException e) {
                    throw new RuntimeException(e);
                }
                if (!(next_value.getType() instanceof RefType)) {
                    break;
                }
                value = (RefValue) next_value;
            }
            return addresses.stream();
        }).collect(Collectors.toList());
    }
}