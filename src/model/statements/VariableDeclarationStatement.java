package model.statements;

import model.state.PrgState;
import model.state.exceptions.SymbolAlreadyExistsAppException;
import model.values.types.IType;

public class VariableDeclarationStatement implements IStatement{
    String name;
    IType type;

    public VariableDeclarationStatement(String name, IType type) {
        this.name = name;
        this.type = type;
    }
    @Override
    public void execute(PrgState state) throws SymbolAlreadyExistsAppException {
        state.getSymTable().declValue(name, type);
    }

    @Override
    public String toString(){
        return type.toString() + " " + name;
    }
}