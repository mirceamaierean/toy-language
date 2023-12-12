package model.state;

import model.adt.dictionary.GenericDictionary;
import model.adt.dictionary.IGenericDictionary;
import model.adt.dictionary.KeyNotFoundAppException;
import model.exceptions.AppException;
import model.state.exceptions.SymbolAlreadyExistsAppException;
import model.state.exceptions.SymbolNotFoundAppException;
import model.values.IValue;
import model.values.types.IType;

import java.util.Map;

public class SymTable implements ISymTable {
    IGenericDictionary<String, IValue> data;

    public SymTable() {
        data = new GenericDictionary<>();
    }

    @Override
    public void declValue(String name, IType type) throws SymbolAlreadyExistsAppException {
        if (data.exists(name))
            throw new SymbolAlreadyExistsAppException("Symbol " + name + " already exists.");

        data.insert(name, type.getDefaultValue());
    }

    @Override
    public IValue getValue(String name) throws SymbolNotFoundAppException {
        try {
            return data.lookup(name);
        } catch (KeyNotFoundAppException exception) {
            throw new SymbolNotFoundAppException("Symbol " + name + " not found.");
        }
    }

    @Override
    public void setValue(String name, IValue value) throws SymbolNotFoundAppException, KeyNotFoundAppException {
        if (!data.exists(name))
            throw new SymbolNotFoundAppException("Symbol " + name + " not found.");

        if (!data.lookup(name).getType().equals(value.getType()))
            throw new SymbolNotFoundAppException("Symbol " + name + " does not have the same type as new value.");

        data.insert(name, value);
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder("SymTable:\n");
        try {
            for (String key : data.getKeys()) {
                answer.append(key).append("(").append(data.lookup(key).getType().toString()).append(")").append(":-> ").append(data.lookup(key).toString()).append("\n");
            }
        } catch (AppException exception) {
            throw new RuntimeException(exception.getMessage());
        }
        return answer.toString();
    }

    @Override
    public ISymTable copy() throws AppException{
        ISymTable newSymTable = new SymTable();

        for(String key:data.getKeys()){
            newSymTable.declValue(key, data.lookup(key).getType());
            newSymTable.setValue(key, data.lookup(key).clone());
        }

        return newSymTable;
    }

    public Map<String, IValue> getMap() {
        return this.data.getMap();
    }
}