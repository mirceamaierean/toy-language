package model.values.types;

import model.values.BooleanValue;
import model.values.IValue;

public class BooleanType implements IType {
    @Override
    public IValue getDefaultValue() {
        return new BooleanValue(false);
    }

    @Override
    public String toString() {
        return "BooleanType";
    }

    @Override
    public boolean equals(IType other) {
        return (other instanceof BooleanType);
    }
}