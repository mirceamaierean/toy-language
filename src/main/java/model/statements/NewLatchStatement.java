package model.statements;


import model.adt.dictionary.IGenericDictionary;
import model.exceptions.AppException;
import model.expressions.IExpression;
import model.state.PrgState;
import model.values.IValue;
import model.values.IntegerValue;
import model.values.types.IType;
import model.values.types.IntegerType;

/**
 * NewLatchStatement creates a new countdownlatch into the LatchTable
 */
public class NewLatchStatement implements IStatement {
    private String variableName;
    private IExpression expression;

    public NewLatchStatement(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws AppException {
        PrgState.lock.lock();

        // evaluate the expression
        IValue expressionValue = expression.evaluate(state);

        // if it is not an integer then print an error and stop the execution
        if (!expressionValue.getType().equals(new IntegerType())) {
            PrgState.lock.unlock();
            throw new AppException(String.format("Expression '%s' should have evaluate to an integer", expression.toString()));
        }

        // LatchTable2 = LatchTable1 synchronizedUnion {newfreelocation ->num1}
        int latch = ((IntegerValue) expressionValue).getValue();
        int latchLocation = state.getLatchTable().put(latch);
        IValue variableValue = null;
        try {
            variableValue = state.getSymTable().getValue(variableName);
        } catch (AppException e) {
            PrgState.lock.unlock();
            throw new AppException(String.format("Variable '%s' has not been declared", variableName));
        }

        if (!variableValue.getType().equals(new IntegerType())) {
            PrgState.lock.unlock();
            throw new AppException(String.format("Variable '%s' should be of integer type", variableName));
        }

        // if var exists in SymTable1 and has the type int then
        state.getSymTable().setValue(variableName, new IntegerValue(latchLocation));
        PrgState.lock.unlock();
        return null;
    }

    @Override
    public IGenericDictionary<String, IType> typecheck(IGenericDictionary<String, IType> typeEnv) throws AppException {
        IType variableType = typeEnv.getMap().get(variableName);

        if (variableType == null)
            throw new AppException(String.format("Variable '%s' has not been declared", variableName));

        if (!variableType.equals(new IntegerType()))
            throw new AppException(String.format("Variable '%s' should be of integer type", variableName));

        IType expressionType = expression.typecheck(typeEnv);

        if (!expressionType.equals(new IntegerType()))
            throw new AppException(String.format("Expression '%s' should be of integer type", expressionType));

        return typeEnv;
    }


    @Override
    public String toString() {
        return String.format("newLatch(%s, %s)", variableName, expression.toString());
    }
}