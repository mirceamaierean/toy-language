package utils;

import model.expressions.BinaryExpression;
import model.expressions.ConstantExpression;
import model.expressions.ReadHeapFunction;
import model.expressions.VariableExpression;
import model.statements.*;
import model.values.IntegerValue;
import model.values.StringValue;
import model.values.types.IntegerType;
import model.values.types.RefType;
import model.values.types.StringType;

import java.util.ArrayList;
import java.util.List;

public class HardcodedPrograms {
    public static final List<IStatement> hardcodedPrograms = new ArrayList<IStatement>(List.of(
            new CompositeStatement(
                    new VariableDeclarationStatement("v1", new RefType(new IntegerType())),
                    new CompositeStatement(
                            new VariableDeclarationStatement("v2", new RefType(new IntegerType())),
                            new CompositeStatement(
                                    new VariableDeclarationStatement("v3", new RefType(new IntegerType())),
                                    new CompositeStatement(
                                            new VariableDeclarationStatement("cnt", new IntegerType()),
                                            new CompositeStatement(
                                                    new NewStatement("v1", new ConstantExpression(new IntegerValue(2))),
                                                    new CompositeStatement(
                                                            new NewStatement("v2", new ConstantExpression(new IntegerValue(3))),
                                                            new CompositeStatement(
                                                                    new NewStatement("v3", new ConstantExpression(new IntegerValue(4))),
                                                                    new CompositeStatement(
                                                                            new NewLatchStatement("cnt", new ReadHeapFunction(new VariableExpression("v2"))),
                                                                            new CompositeStatement(
                                                                                    new ForkStatement(
                                                                                            new CompositeStatement(
                                                                                                    new WriteHeapStatement(new VariableExpression("v1"), new BinaryExpression(new ReadHeapFunction(new VariableExpression("v1")), new ConstantExpression(new IntegerValue(10)), "*")),
                                                                                                    new CompositeStatement(
                                                                                                            new PrintStatement(new ReadHeapFunction(new VariableExpression("v1"))),
                                                                                                            new CountDownStatement("cnt")
                                                                                                    )
                                                                                            )
                                                                                    ),
                                                                                    new CompositeStatement(
                                                                                            new ForkStatement(
                                                                                                    new CompositeStatement(
                                                                                                            new WriteHeapStatement(new VariableExpression("v2"), new BinaryExpression(new ReadHeapFunction(new VariableExpression("v2")), new ConstantExpression(new IntegerValue(10)), "*")),
                                                                                                            new CompositeStatement(
                                                                                                                    new PrintStatement(new ReadHeapFunction(new VariableExpression("v2"))),
                                                                                                                    new CountDownStatement("cnt")
                                                                                                            )
                                                                                                    )
                                                                                            ),
                                                                                            new CompositeStatement(
                                                                                                    new ForkStatement(
                                                                                                            new CompositeStatement(
                                                                                                                    new WriteHeapStatement(new VariableExpression("v3"), new BinaryExpression(new ReadHeapFunction(new VariableExpression("v3")), new ConstantExpression(new IntegerValue(10)), "*")),
                                                                                                                    new CompositeStatement(
                                                                                                                            new PrintStatement(new ReadHeapFunction(new VariableExpression("v3"))),
                                                                                                                            new CountDownStatement("cnt")
                                                                                                                    )
                                                                                                            )
                                                                                                    ),
                                                                                                    new CompositeStatement(
                                                                                                            new AwaitLatchStatement("cnt"),
                                                                                                            new CompositeStatement(
                                                                                                                    new PrintStatement(new ConstantExpression(new IntegerValue(100))),
                                                                                                                    new CompositeStatement(
                                                                                                                            new CountDownStatement("cnt"),
                                                                                                                            new PrintStatement(new ConstantExpression(new IntegerValue(100)))
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
                            )
                    )
            ),
            new CompositeStatement(
                    new VariableDeclarationStatement("v1", new RefType(new IntegerType())),
                    new CompositeStatement(
                            new VariableDeclarationStatement("v2", new RefType(new IntegerType())),
                            new CompositeStatement(
                                    new VariableDeclarationStatement("v3", new RefType(new IntegerType())),
                                    new CompositeStatement(
                                            new VariableDeclarationStatement("cnt", new IntegerType()),
                                            new CompositeStatement(
                                                    new NewStatement("v1", new ConstantExpression(new IntegerValue(2))),
                                                    new CompositeStatement(
                                                            new NewStatement("v2", new ConstantExpression(new IntegerValue(-1))),
                                                            new CompositeStatement(
                                                                    new NewStatement("v3", new ConstantExpression(new IntegerValue(4))),
                                                                    new CompositeStatement(
                                                                            new NewLatchStatement("cnt", new ReadHeapFunction(new VariableExpression("v2"))),
                                                                            new CompositeStatement(
                                                                                    new ForkStatement(
                                                                                            new CompositeStatement(
                                                                                                    new WriteHeapStatement(new VariableExpression("v1"), new BinaryExpression(new ReadHeapFunction(new VariableExpression("v1")), new ConstantExpression(new IntegerValue(10)), "*")),
                                                                                                    new CompositeStatement(
                                                                                                            new PrintStatement(new ReadHeapFunction(new VariableExpression("v1"))),
                                                                                                            new CountDownStatement("cnt")
                                                                                                    )
                                                                                            )
                                                                                    ),
                                                                                    new CompositeStatement(
                                                                                            new ForkStatement(
                                                                                                    new CompositeStatement(
                                                                                                            new WriteHeapStatement(new VariableExpression("v2"), new BinaryExpression(new ReadHeapFunction(new VariableExpression("v2")), new ConstantExpression(new IntegerValue(10)), "*")),
                                                                                                            new CompositeStatement(
                                                                                                                    new PrintStatement(new ReadHeapFunction(new VariableExpression("v2"))),
                                                                                                                    new CountDownStatement("cnt")
                                                                                                            )
                                                                                                    )
                                                                                            ),
                                                                                            new CompositeStatement(
                                                                                                    new ForkStatement(
                                                                                                            new CompositeStatement(
                                                                                                                    new WriteHeapStatement(new VariableExpression("v3"), new BinaryExpression(new ReadHeapFunction(new VariableExpression("v3")), new ConstantExpression(new IntegerValue(10)), "*")),
                                                                                                                    new CompositeStatement(
                                                                                                                            new PrintStatement(new ReadHeapFunction(new VariableExpression("v3"))),
                                                                                                                            new CountDownStatement("cnt")
                                                                                                                    )
                                                                                                            )
                                                                                                    ),
                                                                                                    new CompositeStatement(
                                                                                                            new AwaitLatchStatement("cnt"),
                                                                                                            new CompositeStatement(
                                                                                                                    new PrintStatement(new ConstantExpression(new IntegerValue(100))),
                                                                                                                    new CompositeStatement(
                                                                                                                            new CountDownStatement("cnt"),
                                                                                                                            new PrintStatement(new ConstantExpression(new IntegerValue(100)))
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
                            )
                    )
            ),
            new CompositeStatement(
                    new VariableDeclarationStatement("v1", new RefType(new IntegerType())),
                    new CompositeStatement(
                            new VariableDeclarationStatement("v2", new RefType(new StringType())),
                            new CompositeStatement(
                                    new VariableDeclarationStatement("v3", new RefType(new IntegerType())),
                                    new CompositeStatement(
                                            new VariableDeclarationStatement("cnt", new StringType()),
                                            new CompositeStatement(
                                                    new NewStatement("v1", new ConstantExpression(new IntegerValue(2))),
                                                    new CompositeStatement(
                                                            new NewStatement("v2", new ConstantExpression(new StringValue("APM Exam"))),
                                                            new CompositeStatement(
                                                                    new NewStatement("v3", new ConstantExpression(new IntegerValue(4))),
                                                                    new CompositeStatement(
                                                                            new NewLatchStatement("cnt", new ReadHeapFunction(new VariableExpression("v2"))),
                                                                            new CompositeStatement(
                                                                                    new ForkStatement(
                                                                                            new CompositeStatement(
                                                                                                    new WriteHeapStatement(new VariableExpression("v1"), new BinaryExpression(new ReadHeapFunction(new VariableExpression("v1")), new ConstantExpression(new IntegerValue(10)), "*")),
                                                                                                    new CompositeStatement(
                                                                                                            new PrintStatement(new ReadHeapFunction(new VariableExpression("v1"))),
                                                                                                            new CountDownStatement("cnt")
                                                                                                    )
                                                                                            )
                                                                                    ),
                                                                                    new CompositeStatement(
                                                                                            new ForkStatement(
                                                                                                    new CompositeStatement(
                                                                                                            new WriteHeapStatement(new VariableExpression("v2"), new BinaryExpression(new ReadHeapFunction(new VariableExpression("v2")), new ConstantExpression(new IntegerValue(10)), "*")),
                                                                                                            new CompositeStatement(
                                                                                                                    new PrintStatement(new ReadHeapFunction(new VariableExpression("v2"))),
                                                                                                                    new CountDownStatement("cnt")
                                                                                                            )
                                                                                                    )
                                                                                            ),
                                                                                            new CompositeStatement(
                                                                                                    new ForkStatement(
                                                                                                            new CompositeStatement(
                                                                                                                    new WriteHeapStatement(new VariableExpression("v3"), new BinaryExpression(new ReadHeapFunction(new VariableExpression("v3")), new ConstantExpression(new IntegerValue(10)), "*")),
                                                                                                                    new CompositeStatement(
                                                                                                                            new PrintStatement(new ReadHeapFunction(new VariableExpression("v3"))),
                                                                                                                            new CountDownStatement("cnt")
                                                                                                                    )
                                                                                                            )
                                                                                                    ),
                                                                                                    new CompositeStatement(
                                                                                                            new AwaitLatchStatement("cnt"),
                                                                                                            new CompositeStatement(
                                                                                                                    new PrintStatement(new ConstantExpression(new IntegerValue(100))),
                                                                                                                    new CompositeStatement(
                                                                                                                            new CountDownStatement("cnt"),
                                                                                                                            new PrintStatement(new ConstantExpression(new IntegerValue(100)))
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
                            )
                    )
            ),
            new CompositeStatement(
                    new VariableDeclarationStatement("v1", new RefType(new IntegerType())),
                    new CompositeStatement(
                            new VariableDeclarationStatement("v2", new RefType(new IntegerType())),
                            new CompositeStatement(
                                    new VariableDeclarationStatement("v3", new RefType(new IntegerType())),
                                    new CompositeStatement(
                                            new VariableDeclarationStatement("cnt", new IntegerType()),
                                            new CompositeStatement(
                                                    new NewStatement("v1", new ConstantExpression(new IntegerValue(2))),
                                                    new CompositeStatement(
                                                            new NewStatement("v2", new ConstantExpression(new IntegerValue(3))),
                                                            new CompositeStatement(
                                                                    new NewStatement("v3", new ConstantExpression(new IntegerValue(4))),
                                                                    new CompositeStatement(
                                                                            new NewLatchStatement("cnt", new ReadHeapFunction(new VariableExpression("v2"))),
                                                                            new CompositeStatement(
                                                                                    new ForkStatement(
                                                                                            new CompositeStatement(
                                                                                                    new WriteHeapStatement(new VariableExpression("v1"), new BinaryExpression(new ReadHeapFunction(new VariableExpression("v1")), new ConstantExpression(new IntegerValue(10)), "*")),
                                                                                                    new CompositeStatement(
                                                                                                            new PrintStatement(new ReadHeapFunction(new VariableExpression("v1"))),
                                                                                                            new CountDownStatement("cnt")
                                                                                                    )
                                                                                            )
                                                                                    ),
                                                                                    new CompositeStatement(
                                                                                            new ForkStatement(
                                                                                                    new CompositeStatement(
                                                                                                            new WriteHeapStatement(new VariableExpression("v2"), new BinaryExpression(new ReadHeapFunction(new VariableExpression("v2")), new ConstantExpression(new IntegerValue(10)), "*")),
                                                                                                            new CompositeStatement(
                                                                                                                    new PrintStatement(new ReadHeapFunction(new VariableExpression("v2"))),
                                                                                                                    new CountDownStatement("cnt")
                                                                                                            )
                                                                                                    )
                                                                                            ),
                                                                                            new CompositeStatement(
                                                                                                    new ForkStatement(
                                                                                                            new CompositeStatement(
                                                                                                                    new WriteHeapStatement(new VariableExpression("v3"), new BinaryExpression(new ReadHeapFunction(new VariableExpression("v3")), new ConstantExpression(new IntegerValue(10)), "*")),
                                                                                                                    new CompositeStatement(
                                                                                                                            new PrintStatement(new ReadHeapFunction(new VariableExpression("v3"))),
                                                                                                                            new CountDownStatement("cnt")
                                                                                                                    )
                                                                                                            )
                                                                                                    ),
                                                                                                    new CompositeStatement(
                                                                                                            new AwaitLatchStatement("cnt"),
                                                                                                            new CompositeStatement(
                                                                                                                    new PrintStatement(new ConstantExpression(new IntegerValue(100))),
                                                                                                                    new CompositeStatement(
                                                                                                                            new CountDownStatement("CNT"),
                                                                                                                            new PrintStatement(new ConstantExpression(new IntegerValue(100)))
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
                            )
                    )
            ),
            new CompositeStatement(
                    new VariableDeclarationStatement("v1", new RefType(new IntegerType())),
                    new CompositeStatement(
                            new VariableDeclarationStatement("v2", new RefType(new StringType())),
                            new CompositeStatement(
                                    new VariableDeclarationStatement("v3", new RefType(new IntegerType())),
                                    new CompositeStatement(
                                            new VariableDeclarationStatement("cnt", new IntegerType()),
                                            new CompositeStatement(
                                                    new NewStatement("v1", new ConstantExpression(new IntegerValue(2))),
                                                    new CompositeStatement(
                                                            new NewStatement("v2", new ConstantExpression(new StringValue("MAP Exam"))),
                                                            new CompositeStatement(
                                                                    new NewStatement("v3", new ConstantExpression(new IntegerValue(4))),
                                                                    new CompositeStatement(
                                                                            new NewLatchStatement("cnt", new ReadHeapFunction(new VariableExpression("v2"))),
                                                                            new CompositeStatement(
                                                                                    new ForkStatement(
                                                                                            new CompositeStatement(
                                                                                                    new WriteHeapStatement(new VariableExpression("v1"), new BinaryExpression(new ReadHeapFunction(new VariableExpression("v1")), new ConstantExpression(new IntegerValue(10)), "*")),
                                                                                                    new CompositeStatement(
                                                                                                            new PrintStatement(new ReadHeapFunction(new VariableExpression("v1"))),
                                                                                                            new CountDownStatement("cnt")
                                                                                                    )
                                                                                            )
                                                                                    ),
                                                                                    new CompositeStatement(
                                                                                            new ForkStatement(
                                                                                                    new CompositeStatement(
                                                                                                            new WriteHeapStatement(new VariableExpression("v2"), new BinaryExpression(new ReadHeapFunction(new VariableExpression("v2")), new ConstantExpression(new IntegerValue(10)), "*")),
                                                                                                            new CompositeStatement(
                                                                                                                    new PrintStatement(new ReadHeapFunction(new VariableExpression("v2"))),
                                                                                                                    new CountDownStatement("cnt")
                                                                                                            )
                                                                                                    )
                                                                                            ),
                                                                                            new CompositeStatement(
                                                                                                    new ForkStatement(
                                                                                                            new CompositeStatement(
                                                                                                                    new WriteHeapStatement(new VariableExpression("v3"), new BinaryExpression(new ReadHeapFunction(new VariableExpression("v3")), new ConstantExpression(new IntegerValue(10)), "*")),
                                                                                                                    new CompositeStatement(
                                                                                                                            new PrintStatement(new ReadHeapFunction(new VariableExpression("v3"))),
                                                                                                                            new CountDownStatement("cnt")
                                                                                                                    )
                                                                                                            )
                                                                                                    ),
                                                                                                    new CompositeStatement(
                                                                                                            new AwaitLatchStatement("cnt"),
                                                                                                            new CompositeStatement(
                                                                                                                    new PrintStatement(new ConstantExpression(new IntegerValue(100))),
                                                                                                                    new CompositeStatement(
                                                                                                                            new CountDownStatement("CNT"),
                                                                                                                            new PrintStatement(new ConstantExpression(new IntegerValue(100)))
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
                            )
                    )
            )
    )
    );
}