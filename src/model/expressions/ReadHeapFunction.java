package model.expressions;

import model.exceptions.AppException;
import model.state.PrgState;
import model.values.IValue;
import model.values.RefValue;
import model.values.types.RefType;

public class ReadHeapFunction implements IExpression {

    IExpression expr;

    public ReadHeapFunction(IExpression expr) {
        this.expr = expr;
    }

    @Override
    public IValue evaluate(PrgState state) throws AppException {
        IValue value = expr.evaluate(state);
        if (!(value.getType() instanceof RefType))
            throw new AppException("Heap should only be accessed through references");
        return state.getHeap().read(((RefValue) value).getAddress());
    }

    @Override
    public String toString() {
        return "readHeap(" + expr.toString() + ")";
    }
}