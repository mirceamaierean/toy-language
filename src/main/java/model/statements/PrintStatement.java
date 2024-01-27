package model.statements;

import model.adt.dictionary.IGenericDictionary;
import model.exceptions.AppException;
import model.expressions.IExpression;
import model.state.PrgState;
import model.values.types.IType;

public class PrintStatement implements IStatement {

    IExpression expression;

    public PrintStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws AppException {
        state.getOutput().appendToOutput(expression.evaluate(state).toString());
        return null;
    }

    @Override
    public String toString() {
        return "print(" + expression.toString() + ")";
    }

    @Override
    public IGenericDictionary<String, IType> typecheck(IGenericDictionary<String, IType> typeDictionary) throws AppException {
        expression.typecheck(typeDictionary);
        return typeDictionary;
    }
}