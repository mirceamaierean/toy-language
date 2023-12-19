package model.values;

import model.exceptions.AppException;
import model.values.types.IType;

public interface IValue {
    String toString();

    IValue compose(IValue other, String operation) throws AppException;

    IType getType();

    boolean equals(IValue other);
    IValue clone();
}