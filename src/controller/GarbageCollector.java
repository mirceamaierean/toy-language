package controller;

import model.state.PrgState;
import model.state.exceptions.AddressOutOfBoundsAppException;
import model.values.IValue;
import model.values.RefValue;
import model.values.types.RefType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GarbageCollector {
    public static void runGarbageCollector(PrgState state) {
        List<Integer> activeAddresses = GarbageCollector.getActiveAddresses(state);

        List <Integer> addresses = state.getHeap().getMap().keySet().stream().filter(e -> !activeAddresses.contains(e)).collect(Collectors.toList());

        addresses.forEach(e -> {
            try {
                state.getHeap().deallocate(e);
            } catch (AddressOutOfBoundsAppException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public static List<Integer> getActiveAddresses(PrgState state) {
        return state.getSymTable().getMap().values().stream()
                .filter(e -> e.getType() instanceof RefType)
                .map(e -> (RefValue) e)
                .flatMap(value -> {
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