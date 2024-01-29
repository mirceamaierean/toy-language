package utils;

import model.expressions.BinaryExpression;
import model.expressions.ConstantExpression;
import model.expressions.ReadHeapFunction;
import model.expressions.VariableExpression;
import model.statements.*;
import model.values.BooleanValue;
import model.values.IntegerValue;
import model.values.StringValue;
import model.values.types.BooleanType;
import model.values.types.IntegerType;
import model.values.types.RefType;
import model.values.types.StringType;

import java.util.ArrayList;
import java.util.List;

public class HardcodedPrograms {
    public static final List<IStatement> hardcodedPrograms = new ArrayList<IStatement>(List.of(
            new CompositeStatement(
                    new VariableDeclarationStatement("a", new RefType(new IntegerType())),
                    new CompositeStatement(
                            new VariableDeclarationStatement("b", new RefType(new IntegerType())),
                            new CompositeStatement(
                                    new VariableDeclarationStatement("v", new IntegerType()),
                                    new CompositeStatement(
                                            new NewStatement("a", new ConstantExpression(new IntegerValue(0))),
                                            new CompositeStatement(
                                                    new NewStatement("b", new ConstantExpression(new IntegerValue(0))),
                                                    new CompositeStatement(
                                                            new WriteHeapStatement(new VariableExpression("a"), new ConstantExpression(new IntegerValue(1))),
                                                            new CompositeStatement(
                                                                    new WriteHeapStatement(new VariableExpression("b"), new ConstantExpression(new IntegerValue(2))),
                                                                    new CompositeStatement(
                                                                            new ConditionalStatement(
                                                                                    "v",
                                                                                    new BinaryExpression(
                                                                                            new ReadHeapFunction(new VariableExpression("a")),
                                                                                            new ReadHeapFunction(new VariableExpression("b")),
                                                                                            "<"
                                                                                    ),
                                                                                    new ConstantExpression(new IntegerValue(100)),
                                                                                    new ConstantExpression(new IntegerValue(200))
                                                                            )
                                                                            ,
                                                                            new CompositeStatement(
                                                                                    new PrintStatement(new VariableExpression("v")),
                                                                                    new CompositeStatement(
                                                                                            new ConditionalStatement(
                                                                                                    "v",
                                                                                                    new BinaryExpression(
                                                                                                            new ReadHeapFunction(new VariableExpression("a")),
                                                                                                            new ReadHeapFunction(new VariableExpression("b")),
                                                                                                            ">"
                                                                                                    ),
                                                                                                    new ConstantExpression(new IntegerValue(100)),
                                                                                                    new ConstantExpression(new IntegerValue(200))
                                                                                            ),
                                                                                            new PrintStatement(new VariableExpression("v"))
                                                                                    )
                                                                            )
                                                                    )
                                                            )
                                                    )
                                            )
                                    )
                            )
                    )
            ),
            new CompositeStatement(
                    new VariableDeclarationStatement("a", new RefType(new IntegerType())),
                    new CompositeStatement(
                            new VariableDeclarationStatement("b", new RefType(new IntegerType())),
                            new CompositeStatement(
                                    new VariableDeclarationStatement("v", new IntegerType()),
                                    new CompositeStatement(
                                            new NewStatement("a", new ConstantExpression(new IntegerValue(0))),
                                            new CompositeStatement(
                                                    new NewStatement("b", new ConstantExpression(new IntegerValue(0))),
                                                    new CompositeStatement(
                                                            new WriteHeapStatement(new VariableExpression("a"), new ConstantExpression(new IntegerValue(1))),
                                                            new CompositeStatement(
                                                                    new WriteHeapStatement(new VariableExpression("b"), new ConstantExpression(new IntegerValue(2))),
                                                                    new CompositeStatement(
                                                                            new ConditionalStatement(
                                                                                    "v",
                                                                                    new BinaryExpression(
                                                                                            new ReadHeapFunction(new VariableExpression("a")),
                                                                                            new ReadHeapFunction(new VariableExpression("b")),
                                                                                            "<"
                                                                                    ),
                                                                                    new ConstantExpression(new StringValue("MAP Exam")),
                                                                                    new ConstantExpression(new IntegerValue(200))
                                                                            )
                                                                            ,
                                                                            new CompositeStatement(
                                                                                    new PrintStatement(new VariableExpression("v")),
                                                                                    new CompositeStatement(
                                                                                            new ConditionalStatement(
                                                                                                    "v",
                                                                                                    new BinaryExpression(
                                                                                                            new ReadHeapFunction(new VariableExpression("a")),
                                                                                                            new ReadHeapFunction(new VariableExpression("b")),
                                                                                                            ">"
                                                                                                    ),
                                                                                                    new ConstantExpression(new IntegerValue(100)),
                                                                                                    new ConstantExpression(new IntegerValue(200))
                                                                                            ),
                                                                                            new PrintStatement(new VariableExpression("v"))
                                                                                    )
                                                                            )
                                                                    )
                                                            )
                                                    )
                                            )
                                    )
                            )
                    )
            ),
            new CompositeStatement(
                    new VariableDeclarationStatement("a", new RefType(new IntegerType())),
                    new CompositeStatement(
                            new VariableDeclarationStatement("b", new RefType(new IntegerType())),
                            new CompositeStatement(
                                    new VariableDeclarationStatement("v", new IntegerType()),
                                    new CompositeStatement(
                                            new NewStatement("a", new ConstantExpression(new IntegerValue(0))),
                                            new CompositeStatement(
                                                    new NewStatement("b", new ConstantExpression(new IntegerValue(0))),
                                                    new CompositeStatement(
                                                            new WriteHeapStatement(new VariableExpression("a"), new ConstantExpression(new IntegerValue(1))),
                                                            new CompositeStatement(
                                                                    new WriteHeapStatement(new VariableExpression("b"), new ConstantExpression(new IntegerValue(2))),
                                                                    new CompositeStatement(
                                                                            new ConditionalStatement(
                                                                                    "v",
                                                                                    new BinaryExpression(
                                                                                            new ReadHeapFunction(new VariableExpression("a")),
                                                                                            new ReadHeapFunction(new VariableExpression("b")),
                                                                                            "+"
                                                                                    ),
                                                                                    new ConstantExpression(new IntegerValue(100)),
                                                                                    new ConstantExpression(new IntegerValue(200))
                                                                            )
                                                                            ,
                                                                            new CompositeStatement(
                                                                                    new PrintStatement(new VariableExpression("v")),
                                                                                    new CompositeStatement(
                                                                                            new ConditionalStatement(
                                                                                                    "v",
                                                                                                    new BinaryExpression(
                                                                                                            new ReadHeapFunction(new VariableExpression("a")),
                                                                                                            new ReadHeapFunction(new VariableExpression("b")),
                                                                                                            ">"
                                                                                                    ),
                                                                                                    new ConstantExpression(new IntegerValue(100)),
                                                                                                    new ConstantExpression(new IntegerValue(200))
                                                                                            ),
                                                                                            new PrintStatement(new VariableExpression("v"))
                                                                                    )
                                                                            )
                                                                    )
                                                            )
                                                    )
                                            )
                                    )
                            )
                    )
            )

    )
    );
}