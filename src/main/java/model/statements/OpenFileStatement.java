package model.statements;

import model.adt.dictionary.IGenericDictionary;
import model.exceptions.AppException;
import model.expressions.IExpression;
import model.state.PrgState;
import model.values.IValue;
import model.values.StringValue;
import model.values.types.IType;
import model.values.types.StringType;

public class OpenFileStatement implements IStatement {
    IExpression expression;

    public OpenFileStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws AppException {
        IValue value = this.expression.evaluate(state);
        if (!(value.getType() instanceof StringType)) {
            throw new AppException("Filename did not evaluate to string");
        }
        state.getFileTable().openFile(((StringValue) value).getValue());
        return null;
    }

    @Override
    public String toString() {
        return "openRFile(" + this.expression.toString() + ")";
    }

    @Override
    public IGenericDictionary<String, IType> typecheck(IGenericDictionary<String, IType> typeDictionary) throws AppException {
        if ((new StringType()).equals(expression.typecheck(typeDictionary))) {
            return typeDictionary;
        }
        throw new AppException("Open file expression doesn't evaluate to a StringType");
    }
}