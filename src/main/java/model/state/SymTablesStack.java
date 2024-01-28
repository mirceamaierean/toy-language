package model.state;


import model.adt.dictionary.IGenericDictionary;
import model.adt.stack.GenericStack;
import model.exceptions.AppException;
import model.values.IValue;

public class SymTablesStack extends GenericStack<ISymTable> {
    public SymTablesStack deepCopy() throws AppException {
        SymTablesStack newStack = new SymTablesStack();
        GenericStack<ISymTable> tempStack = new GenericStack<>();

        while (!this.isEmpty())
            tempStack.push(this.pop());


        while (!tempStack.isEmpty()) {
            this.push(tempStack.top());
            newStack.push(tempStack.pop().copy());
        }

        return newStack;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (ISymTable symTable : this.getStack()) {
            result.append(symTable.toString()).append("\n");
        }
        return result.toString();
    }
}