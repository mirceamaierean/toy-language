package model.expressions;

import model.adt.dictionary.IGenericDictionary;
import model.exceptions.AppException;
import model.state.PrgState;
import model.values.IValue;
import model.values.types.IType;
import model.values.types.IntegerType;

public class MulExpression implements IExpression {
    private final IExpression expression1;
    private final IExpression expression2;

    public MulExpression(IExpression expression1, IExpression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }


//    @Override
//    public MulExpression deepCopy() {
//        return new MulExpression(expression1.deepCopy(), expression2.deepCopy());
//    }

    @Override
    public IValue evaluate(PrgState state) throws AppException {
        return new BinaryExpression(new BinaryExpression(expression1, expression2, "*"), new BinaryExpression(expression1, expression2, "+"), "-").evaluate(state);
    }

    @Override
    public IType typecheck(IGenericDictionary<String, IType> typeEnv) throws AppException {
        IType type1 = expression1.typecheck(typeEnv);
        IType type2 = expression2.typecheck(typeEnv);
        if (type1.equals(new IntegerType()) && type2.equals(new IntegerType()))
            return new IntegerType();
        else
            throw new AppException("Expressions in the Mul should be int!");
    }

    @Override
    public String toString() {
        return String.format("MUL(%s, %s)", expression1, expression2);
    }
}