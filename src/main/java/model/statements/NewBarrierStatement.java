package model.statements;

import javafx.util.Pair;
import model.adt.dictionary.IGenericDictionary;
import model.exceptions.AppException;
import model.expressions.IExpression;
import model.state.PrgState;
import model.values.IValue;
import model.values.IntegerValue;
import model.values.types.IType;
import model.values.types.IntegerType;

import java.util.Vector;

public class NewBarrierStatement implements IStatement {
    public String variableName;
    public IExpression expression;

    public NewBarrierStatement(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }
    public String getVariableName() {
        return variableName;
    }
    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }
    public IExpression getExpression() {
        return expression;
    }
    public void setExpression(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public IGenericDictionary<String, IType> typecheck(IGenericDictionary<String, IType> typeTable) throws AppException {
        try {
            IType expressionType = expression.typecheck(typeTable);
            if (!expressionType.equals(new IntegerType()))
                throw new AppException(String.format("Expression %s should evaluate to an integer type!", expression.toString()));

            IType variableType = typeTable.lookup(variableName);
            if (variableType == null)
                throw new AppException(String.format("Variable %s has not been declared!", variableName));
            if (!variableType.equals(new IntegerType()))
                throw new AppException(String.format("Variable %s should be of integer type!", variableName));

        } catch (AppException e) {
            throw new AppException(e.getMessage());
        }

        return typeTable;
    }

    @Override
    public PrgState execute(PrgState state) throws AppException {
        try {
            IValue expressionEval = expression.evaluate(state);
            if (!expressionEval.getType().equals(new IntegerType()))
                throw new AppException(String.format("Expression %s should evaluate to an integer type!", expression.toString()));

            int expressionValue = ((IntegerValue) expressionEval).getValue();
            int newLocation = state.getBarrierTable().put(new Pair<>(expressionValue, new Vector<>()));

            IValue variableValue = state.getSymTable().getValue(variableName);
            if (variableValue == null)
                throw new AppException(String.format("Variable %s has not been declared!", variableName));
            if (!variableValue.getType().equals(new IntegerType()))
                throw new AppException(String.format("Variable %s should be of integer type!", variableName));

            state.getSymTable().setValue(variableName, new IntegerValue(newLocation));

        } catch (AppException e) {
            throw new AppException(e.getMessage());
        }

        return null;
    }
//    @Override
//    public Statement deepCopy() {
//        return new NewBarrierStatement(variableName, expression);
//    }

    @Override
    public String toString() {
        return String.format("newBarrier(%s, %s)", variableName, expression.toString());
    }
}