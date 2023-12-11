package model.statements;

import model.exceptions.AppException;
import model.expressions.IExpression;
import model.state.PrgState;
import model.values.IValue;
import model.values.RefValue;
import model.values.types.RefType;

public class WriteHeapStatement implements IStatement {
    IExpression addressExpression;
    IExpression valueExpression;

    public WriteHeapStatement(IExpression addressExpression, IExpression valueExpression) {
        this.addressExpression = addressExpression;
        this.valueExpression = valueExpression;
    }

    @Override
    public void execute(PrgState state) throws AppException {
        IValue address = addressExpression.evaluate(state);
        IValue value = valueExpression.evaluate(state);
        if (!(address.getType() instanceof RefType)) {
            throw new AppException("Heap should be accessed only using references");
        }

        if (!address.getType().equals(value.getType())) {
            throw new AppException("Type of the value does not match the inner type of the reference");
        }

        state.getHeap().write(((RefValue) address).getAddress(), value);
    }

    @Override
    public String toString() {
        return "writeHeap(" + addressExpression.toString() + ", " + valueExpression.toString() + ")";
    }
}