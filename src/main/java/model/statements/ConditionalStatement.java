package model.statements;

import model.adt.dictionary.IGenericDictionary;
import model.exceptions.AppException;
import model.expressions.IExpression;
import model.state.PrgState;
import model.values.types.BooleanType;
import model.values.types.IType;

import java.util.Objects;

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
        IType typeVariable = typeEnv.lookup(variable);
        IType typeCondition = condition.typecheck(typeEnv);
        IType typeTrueBranch = trueBranch.typecheck(typeEnv);
        IType typeFalseBranch = falseBranch.typecheck(typeEnv);
        // Because the heap accesses the memory directly, RefType type checks cannot happen at compile time.
        // We need to trust the programmer that what is at the heap at the
        // address that is evaluated from the expression is of the correct type
        // at runtime, otherwise an error will occur.

        // if the type of the condition is not boolean, then throw an error
        System.out.println(typeCondition);

        if (!typeCondition.equals(new BooleanType()))
            throw new AppException("Condition is not a boolean");

        if (!typeVariable.equals(typeTrueBranch) || !typeVariable.equals(typeFalseBranch))
            throw new AppException("Mismatched type in conditional statement");

        return typeEnv;
    }


    @Override
    public String toString() {
        return variable + " = (" + condition + ") ? " + trueBranch + " : " + falseBranch;
    }
}