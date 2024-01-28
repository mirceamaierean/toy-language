package model.statements;

import model.adt.dictionary.IGenericDictionary;
import model.exceptions.AppException;
import model.expressions.IExpression;
import model.state.PrgState;
import model.values.types.IType;

public class ConditionalStatement implements IStatement{
    private String variable;
    private IExpression condition;
    private IExpression trueBranch;
    private IExpression falseBranch;
    public ConditionalStatement(String variable, IExpression condition, IExpression trueBranch, IExpression falseBranch) {
        this.variable = variable;
        this.condition = condition;
        this.trueBranch = trueBranch;
        this.falseBranch = falseBranch;
    }
    @Override
    public PrgState execute(PrgState state) throws AppException {
        IfStatement ifStmt = new IfStatement(
                condition,
                new AssignmentStatement(variable, trueBranch),
                new AssignmentStatement(variable, falseBranch)
        );
        state.getExeStack().push(ifStmt);

        return null;
    }

//    @Override
//    public IStatement deepCopy() {
//        return null;
//    }

    @Override
    public IGenericDictionary<String, IType> typecheck(IGenericDictionary<String, IType> typeEnv) throws AppException {
        return typeEnv;
    }


    @Override
    public String toString() {
        return variable + " = (" + condition + ") ? " + trueBranch + " : " + falseBranch;
    }
}