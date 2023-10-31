package model.state;

import model.adt.dictionary.GenericDictionary;
import model.adt.dictionary.IGenericDictionary;
import model.adt.dictionary.KeyNotFoundAppException;
import model.exceptions.AppException;
import model.state.exceptions.SymbolAlreadyExistsAppException;
import model.state.exceptions.SymbolNotFoundAppException;
import model.values.IValue;
import model.values.types.IType;

public class SymTable implements  ISymTable{
    IGenericDictionary<String, IValue> data;

    public SymTable() {
        data = new GenericDictionary<>();
    }

    @Override
    public void declValue(String name, IType type) throws SymbolAlreadyExistsAppException {
        if(data.exists(name)){
            throw new SymbolAlreadyExistsAppException("Symbol " + name + " already exists.");
        }
        data.insert(name, type.getDefaultValue());
    }

    @Override
    public IValue getValue(String name) throws SymbolNotFoundAppException {
        try{
            return data.lookup(name);
        }catch(KeyNotFoundAppException exception){
            throw new SymbolNotFoundAppException("Symbol " + name + " not found.");
        }
    }

    @Override
    public void setValue(String name, IValue value) throws SymbolNotFoundAppException{
        if(!data.exists(name)){
            throw new SymbolNotFoundAppException("Symbol " + name + " not found.");
        }
        data.insert(name, value);
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder("SymTable:\n");
        try {
            for (String key: data.getKeys()){
                answer.append(key).append("(").append(data.lookup(key).getType().toString()).append(")").append(":-> ").append(data.lookup(key).toString()).append("\n");
            }
        } catch(AppException exception){
            throw new RuntimeException(exception.getMessage());
        }
        return answer.toString();
    }
}