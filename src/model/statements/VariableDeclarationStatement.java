package model.statements;

import model.exceptions.AppException;
import model.state.PrgState;
import model.values.types.IType;

public class VariableDeclarationStatement implements IStatement{
    String name;
    IType type;

    public VariableDeclarationStatement(String name, IType type) {
        this.name = name;
        this.type = type;
    }
    @Override
    public PrgState execute(PrgState state) throws AppException {
        state.getSymTable().declValue(name, type);
        return null;
    }

    @Override
    public String toString(){
        return type.toString() + " " + name;
    }
}