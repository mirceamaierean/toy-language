package model.statements;

import model.adt.dictionary.IGenericDictionary;
import model.exceptions.AppException;
import model.expressions.IExpression;
import model.state.PrgState;
import model.values.types.IType;

public class AssignmentStatement implements IStatement {
    String variableName;
    IExpression expression;

    public AssignmentStatement(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws AppException {
        state.getSymTable().setValue(variableName, expression.evaluate(state));
        return null;
    }

    @Override
    public String toString() {
        return variableName + " = " + expression.toString();
    }

    @Override
    public IGenericDictionary<String, IType> typecheck(IGenericDictionary<String, IType> typeDictionary) throws AppException {
        IType variableType = typeDictionary.lookup(variableName);
        IType expressionType = expression.typecheck(typeDictionary);

        // Because the heap accesses the memory directly, RefType type checks cannot happen at compile time.
        // We need to trust the programmer that what is at the heap at the
        // address that is evaluated from the expression is of the correct type
        // at runtime, otherwise an error will occur.
        if (expressionType == null)
            return typeDictionary;

        if (!variableType.equals(expressionType))
            throw new AppException("Mismatched type in assignment");

        return typeDictionary;
    }
}