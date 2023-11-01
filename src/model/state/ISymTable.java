package model.state;

import model.state.exceptions.SymbolAlreadyExistsAppException;
import model.state.exceptions.SymbolNotFoundAppException;
import model.values.IValue;
import model.values.types.IType;

public interface ISymTable {
    void declValue(String name, IType type) throws SymbolAlreadyExistsAppException;

    IValue getValue(String name) throws SymbolNotFoundAppException;

    void setValue(String name, IValue value) throws SymbolNotFoundAppException;
}