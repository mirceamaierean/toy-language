package model.values.types;

import model.values.IValue;
import model.values.IntegerValue;

public class IntegerType implements IType {
    @Override
    public IValue getDefaultValue() {
        return new IntegerValue(0);
    }

    @Override
    public String toString() {
        return "IntegerType";
    }
}