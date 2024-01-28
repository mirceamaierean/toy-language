package model.statements;

import javafx.util.Pair;
import model.adt.dictionary.GenericDictionary;
import model.adt.dictionary.IGenericDictionary;
import model.adt.list.GenericList;
import model.exceptions.AppException;
import model.expressions.IExpression;
import model.state.ISymTable;
import model.state.PrgState;
import model.state.SymTable;
import model.values.IValue;
import model.values.types.IType;

import java.util.List;
import java.util.Vector;

public class CallStatement implements IStatement {
    private final String functionName;
    private final GenericList<IExpression> parameters;

    public CallStatement(String functionName, List<IExpression> parameters) {
        this.functionName = functionName;
        this.parameters = new GenericList<IExpression>();

        for (IExpression parameter : parameters) {
            this.parameters.add(parameter);
        }
    }

    @Override
    public PrgState execute(PrgState state) throws AppException {

        Pair<List<String>, IStatement> functionEntry = state.getProcedureTable().lookup(functionName);
        if (functionEntry == null)
            throw new AppException(String.format("Function '%s' does not exist!", functionName));

        List<String> paramNames = functionEntry.getKey();
        IStatement functionBody = functionEntry.getValue();

        List<IValue> paramValues = new Vector<IValue>();
        for (int i = 0; i < parameters.size(); ++i)
            paramValues.add(parameters.getAll().get(i).evaluate(state));

        SymTable newSymbolsTable = new SymTable();
        int size = paramNames.size();
        for (int i = 0; i < size; ++i)
        {
            newSymbolsTable.declValue(paramNames.get(i), paramValues.get(i).getType());
            newSymbolsTable.setValue(paramNames.get(i), paramValues.get(i));
        }

        state.getAllSymTables().push(newSymbolsTable);
        state.getExeStack().push(new FunctionReturnStatement());
        state.getExeStack().push(functionBody);

        return null;
    }

    @Override
    public IGenericDictionary<String, IType> typecheck(IGenericDictionary<String, IType> typeEnv) throws AppException {
        return typeEnv;
    }

//    @Override
//    public IStatement deepCopy() {
//        List<Expression> newParams = new Vector<Expression>();
//        for (int i = 0; i < parameters.size(); ++i) {
//            try {
//                newParams.add(parameters.get(i).deepCopy());
//            } catch (AppException e) {
//                return null;
//            }
//        }
//
//        return new FunctionCallStatement(functionName, newParams);
//    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("call " + functionName + "(");
        for (int i = 0; i < parameters.size() - 1; ++i) {
            result.append(parameters.getAll().get(i).toString()).append(", ");
        }

        if (parameters.size() > 0) {
            result.append(parameters.getAll().get(parameters.size() - 1).toString());
            result.append(")");
        }

        return result.toString();
    }
}