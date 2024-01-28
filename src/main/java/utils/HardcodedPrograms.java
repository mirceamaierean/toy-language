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
            new CompositeStatement(new VariableDeclarationStatement("v", new StringType()), new CompositeStatement(new AssignmentStatement("v", new ConstantExpression(new IntegerValue(2))), new PrintStatement(new VariableExpression("v")))),
            new CompositeStatement(new VariableDeclarationStatement("a", new IntegerType()), new CompositeStatement(new AssignmentStatement("a", new BinaryExpression(new ConstantExpression(new IntegerValue(2)), new BinaryExpression(new ConstantExpression(new IntegerValue(3)), new ConstantExpression(new IntegerValue(5)), "*"), "+")), new CompositeStatement(new VariableDeclarationStatement("b", new IntegerType()), new CompositeStatement(new AssignmentStatement("b", new BinaryExpression(new BinaryExpression(new VariableExpression("a"), new BinaryExpression(new ConstantExpression(new IntegerValue(4)), new ConstantExpression(new IntegerValue(2)), "/"), "-"), new ConstantExpression(new IntegerValue(7)), "+")), new PrintStatement(new VariableExpression("b")))))),
            new CompositeStatement(new VariableDeclarationStatement("a", new BooleanType()), new CompositeStatement(new AssignmentStatement("a", new ConstantExpression(new BooleanValue(false))), new CompositeStatement(new VariableDeclarationStatement("v", new IntegerType()), new IfStatement(new VariableExpression("a"), new AssignmentStatement("v", new ConstantExpression(new IntegerValue(2))), new AssignmentStatement("v", new ConstantExpression(new IntegerValue(3))))))),
            new CompositeStatement(new VariableDeclarationStatement("varf", new StringType()), new CompositeStatement(new AssignmentStatement("varf", new ConstantExpression(new StringValue("test.txt"))), new CompositeStatement(new OpenFileStatement(new VariableExpression("varf")), new CompositeStatement(new VariableDeclarationStatement("varc", new IntegerType()), new CompositeStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"), new CompositeStatement(new PrintStatement(new VariableExpression("varc")), new CompositeStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"), new CompositeStatement(new PrintStatement(new VariableExpression("varc")), new CloseFileStatement(new VariableExpression("varf")))))))))),
            new CompositeStatement(new VariableDeclarationStatement("v", new RefType(new IntegerType())), new CompositeStatement(new NewStatement("v", new ConstantExpression(new IntegerValue(20))), new CompositeStatement(new VariableDeclarationStatement("a", new RefType(new RefType(new IntegerType()))), new CompositeStatement(new NewStatement("a", new VariableExpression("v")), new CompositeStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new VariableExpression("a"))))))),
            new CompositeStatement(new VariableDeclarationStatement("v", new RefType(new IntegerType())), new CompositeStatement(new NewStatement("v", new ConstantExpression(new IntegerValue(20))), new CompositeStatement(new VariableDeclarationStatement("a", new RefType(new RefType(new IntegerType()))), new CompositeStatement(new NewStatement("a", new VariableExpression("v")), new CompositeStatement(new PrintStatement(new ReadHeapFunction(new VariableExpression("v"))), new PrintStatement(new BinaryExpression(new ReadHeapFunction(new ReadHeapFunction(new VariableExpression("a"))), new ConstantExpression(new IntegerValue(5)), "+"))))))),
            new CompositeStatement(new VariableDeclarationStatement("v", new RefType(new IntegerType())), new CompositeStatement(new NewStatement("v", new ConstantExpression(new IntegerValue(20))), new CompositeStatement(new PrintStatement(new ReadHeapFunction(new VariableExpression("v"))), new CompositeStatement(new WriteHeapStatement(new VariableExpression("v"), new ConstantExpression(new IntegerValue(30))), new PrintStatement(new BinaryExpression(new ReadHeapFunction(new VariableExpression("v")), new ConstantExpression(new IntegerValue(5)), "+")))))),
            new CompositeStatement(new VariableDeclarationStatement("v", new RefType(new IntegerType())), new CompositeStatement(new NewStatement("v", new ConstantExpression(new IntegerValue(20))), new CompositeStatement(new VariableDeclarationStatement("a", new RefType(new RefType(new IntegerType()))), new CompositeStatement(new NewStatement("a", new VariableExpression("v")), new CompositeStatement(new NewStatement("v", new ConstantExpression(new IntegerValue(30))), new PrintStatement(new ReadHeapFunction(new ReadHeapFunction(new VariableExpression("a"))))))))),
            new CompositeStatement(new VariableDeclarationStatement("v", new IntegerType()), new CompositeStatement(new AssignmentStatement("v", new ConstantExpression(new IntegerValue(4))), new CompositeStatement(new WhileStatement(new BinaryExpression(new VariableExpression("v"), new ConstantExpression(new IntegerValue(0)), ">"), new CompositeStatement(new PrintStatement(new VariableExpression("v")), new AssignmentStatement("v", new BinaryExpression(new VariableExpression("v"), new ConstantExpression(new IntegerValue(1)), "-")))), new PrintStatement(new VariableExpression("v"))))),
            new CompositeStatement(new VariableDeclarationStatement("v", new IntegerType()), new CompositeStatement(new VariableDeclarationStatement("a", new RefType(new IntegerType())), new CompositeStatement(new AssignmentStatement("v", new ConstantExpression(new IntegerValue(10))), new CompositeStatement(new NewStatement("a", new ConstantExpression(new IntegerValue(22))), new CompositeStatement(new ForkStatement(new CompositeStatement(new WriteHeapStatement(new VariableExpression("a"), new ConstantExpression(new IntegerValue(30))), new CompositeStatement(new AssignmentStatement("v", new ConstantExpression(new IntegerValue(32))), new CompositeStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeapFunction(new VariableExpression("a"))))))), new CompositeStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeapFunction(new VariableExpression("a"))))))))),
            new CompositeStatement(new VariableDeclarationStatement("a", new RefType(new IntegerType())), new CompositeStatement(new VariableDeclarationStatement("count", new IntegerType()), new WhileStatement(new BinaryExpression(new VariableExpression("count"), new ConstantExpression(new IntegerValue(10)), "<"), new CompositeStatement(new ForkStatement(new ForkStatement(new CompositeStatement(new NewStatement("a", new VariableExpression("count")), new PrintStatement(new ReadHeapFunction(new VariableExpression("a")))))), new AssignmentStatement("count", new BinaryExpression(new VariableExpression("count"), new ConstantExpression(new IntegerValue(1)), "+")))))),
            new CompositeStatement(
                    new VariableDeclarationStatement("v1", new RefType(new IntegerType())),
                    new CompositeStatement(
                            new VariableDeclarationStatement("cnt", new IntegerType()),
                            new CompositeStatement(
                                    new NewStatement("v1", new ConstantExpression(new IntegerValue(2))),
                                    new CompositeStatement(
                                            new NewToySemaphoreStatement("cnt", new ReadHeapFunction(new VariableExpression("v1")), new ConstantExpression(new IntegerValue(1))),
                                            new CompositeStatement(
                                                    new ForkStatement(
                                                            new CompositeStatement(
                                                                    new AcquireStatement("cnt"),
                                                                    new CompositeStatement(
                                                                            new WriteHeapStatement(new VariableExpression("v1"), new BinaryExpression(new ReadHeapFunction(new VariableExpression("v1")), new ConstantExpression(new IntegerValue(10)), "*")),
                                                                            new CompositeStatement(
                                                                                    new PrintStatement(new ReadHeapFunction(new VariableExpression("v1"))),
                                                                                    new ReleaseStatement("cnt")
                                                                            )
                                                                    )
                                                            )
                                                    ),
                                                    new CompositeStatement(
                                                            new ForkStatement(
                                                                    new CompositeStatement(
                                                                            new AcquireStatement("cnt"),
                                                                            new CompositeStatement(
                                                                                    new WriteHeapStatement(new VariableExpression("v1"), new BinaryExpression(new ReadHeapFunction(new VariableExpression("v1")), new ConstantExpression(new IntegerValue(10)), "*")),
                                                                                    new CompositeStatement(
                                                                                            new WriteHeapStatement(new VariableExpression("v1"), new BinaryExpression(new ReadHeapFunction(new VariableExpression("v1")), new ConstantExpression(new IntegerValue(2)), "*")),
                                                                                            new CompositeStatement(
                                                                                                    new PrintStatement(new ReadHeapFunction(new VariableExpression("v1"))),
                                                                                                    new ReleaseStatement("cnt")
                                                                                            )
                                                                                    )
                                                                            )
                                                                    )
                                                            ),
                                                            new CompositeStatement(
                                                                    new AcquireStatement("cnt"),
                                                                    new CompositeStatement(
                                                                            new PrintStatement(new BinaryExpression(new ReadHeapFunction(new VariableExpression("v1")), new ConstantExpression(new IntegerValue(1)), "-")),
                                                                            new ReleaseStatement("cnt")
                                                                    )
                                                            )
                                                    )
                                            )
                                    )
                            )
                    )
            )));
}