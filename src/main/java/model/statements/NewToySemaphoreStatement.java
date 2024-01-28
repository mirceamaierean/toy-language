package model.statements;

import model.adt.dictionary.IGenericDictionary;
import model.exceptions.AppException;
import model.expressions.IExpression;
import model.state.ISymTable;
import model.state.PrgState;
import model.values.IValue;
import model.values.IntegerValue;
import model.values.types.IType;
import model.values.types.IntegerType;
import utils.Triplet;

import java.util.LinkedList;

public class NewToySemaphoreStatement implements IStatement {
    private final String var;
    private final IExpression exp1;
    private final IExpression exp2;

    public NewToySemaphoreStatement(String var, IExpression exp1, IExpression exp2) {
        this.var = var;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }
    @Override
    public PrgState execute(PrgState state) throws AppException {
        ISymTable symTable = state.getSymTable();

        IValue exp1Val = this.exp1.evaluate(state);
        IValue exp2Val = this.exp2.evaluate(state);

        if(!exp1Val.getType().equals(new IntegerType()) || !exp2Val.getType().equals(new IntegerType()))
            throw new AppException("Values of expressions are not int");


        IValue varVal = symTable.getValue(this.var);
        if(!varVal.getType().equals(new IntegerType()))
            throw new AppException("Variable not int");

        int exp1Int = ((IntegerValue) exp1Val).getValue();
        int exp2Int = ((IntegerValue) exp2Val).getValue();
        int newFreeLocation = state.getSemaphore().allocate(new Triplet(exp1Int, new LinkedList<>(), exp2Int));
        symTable.setValue(this.var, new IntegerValue(newFreeLocation));

        state.setSymTable(symTable);
        return null;
    }


    @Override
    public IGenericDictionary<String, IType> typecheck(IGenericDictionary<String, IType> typeEnv) throws AppException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "newToySemaphore (" + this.var + ", " + this.exp1.toString() + ", " + this.exp2.toString() + ")";
    }
}