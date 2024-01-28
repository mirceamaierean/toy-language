package model.statements;


import model.adt.dictionary.IGenericDictionary;
import model.exceptions.AppException;
import model.expressions.ConstantExpression;
import model.state.IExecutionStack;
import model.state.PrgState;
import model.values.IntegerValue;
import model.values.types.IType;

public class WaitStatement implements IStatement {
    private final Integer number;

    public WaitStatement(Integer number) {
        this.number = number;
    }

    @Override
    public PrgState execute(PrgState state) throws AppException {
        IExecutionStack exeStack = state.getExeStack();
        if (number > 0) {
            exeStack.push(new WaitStatement(number - 1));
            exeStack.push(new PrintStatement(new ConstantExpression(new IntegerValue(number))));

            state.setExeStack(exeStack);
        }
        return null;
    }

//    @Override
//    public IStmt deepCopy() {
//        return new SleepStmt(number);
//    }

    @Override
    public IGenericDictionary<String, IType> typecheck(IGenericDictionary<String, IType> typeEnv) throws AppException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "Wait(" + number + ")";
    }
}