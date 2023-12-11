package model.values.types;

import model.values.IValue;

public interface IType {
    IValue getDefaultValue();
    boolean equals(IType other);
}
