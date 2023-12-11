package model.state;

import model.exceptions.AppException;
import model.state.exceptions.SymbolAlreadyExistsAppException;
import model.state.exceptions.SymbolNotFoundAppException;
import model.values.IValue;
import model.values.types.IType;

import java.util.Map;

public interface ISymTable {

    void declValue(String name, IType type) throws AppException;
    IValue getValue(String name) throws AppException;
    void setValue(String name, IValue value) throws AppException;
    String toString();
    Map<String, IValue> getMap();
}