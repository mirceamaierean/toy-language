package model.expressions;

import model.adt.dictionary.IGenericDictionary;
import model.exceptions.AppException;
import model.state.PrgState;
import model.values.IValue;
import model.values.types.BooleanType;
import model.values.types.IType;
import model.values.types.IntegerType;

public class BinaryExpression implements IExpression {
    IExpression left;
    IExpression right;
    String operator;

    public BinaryExpression(IExpression left, IExpression right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public IValue evaluate(PrgState state) throws AppException {
        return left.evaluate(state).compose(right.evaluate(state), operator);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " " + operator + " " + right.toString() + ")";
    }

    @Override
    public IType typecheck(IGenericDictionary<String, IType> typeDictionary) throws AppException {
        IType firstType = left.typecheck(typeDictionary);
        IType secondType = right.typecheck(typeDictionary);

        if (firstType == null || !firstType.equals(secondType)) {
            throw new AppException("Binary expression operands are not the same");
        }

        // we will decide which type to return based on the operator
        return switch (operator) {
            case "+", "-", "*", "/" -> new IntegerType();
            case "<", "<=", ">", ">=", "==", "!=" -> new BooleanType();
            default -> firstType;
        };
    }
}