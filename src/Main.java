import controller.parsers.syntax.SyntaxParser;
import model.expressions.BinaryExpression;
import model.expressions.ConstantExpression;
import model.expressions.VariableExpression;
import model.statements.*;
import model.values.BooleanValue;
import model.values.IntegerValue;
import model.values.types.BooleanType;
import model.values.types.IntegerType;
import view.View;

public class Main {
    public static void main(String[] args) {
//        IStatement statement1 = new CompositeStatement(
//            new VariableDeclarationStatement("v", new IntegerType()),
//            new CompositeStatement(
//                    new AssignmentStatement(
//                            "v",
//                            new ConstantExpression(new IntegerValue(2))
//                    ),
//                    new PrintStatement(
//                            new VariableExpression("v")
//                    )
//            )
//        );
//
//        IStatement statement2 = new CompositeStatement(
//                new VariableDeclarationStatement("a",new IntegerType()),
//                new CompositeStatement(
//                        new VariableDeclarationStatement("b",new IntegerType()),
//                        new CompositeStatement(
//                                new AssignmentStatement("a",
//                                        new BinaryExpression(
//                                                new ConstantExpression(new IntegerValue(2)),
//                                                new BinaryExpression(
//                                                        new ConstantExpression(new IntegerValue(3)),
//                                                        new ConstantExpression(new IntegerValue(5)),
//                                                        "*"
//                                                ),
//                                                "+"
//                                        )
//                                ),
//                                new CompositeStatement(
//                                        new AssignmentStatement(
//                                                "b",
//                                                new BinaryExpression(
//                                                        new VariableExpression("a"),
//                                                        new ConstantExpression(new IntegerValue(1)),
//                                                        "+"
//                                                )
//                                        ),
//                                        new PrintStatement(new VariableExpression("b"))
//                                )
//                        )
//                )
//        );
//        IStatement statement3 = new CompositeStatement(
//                new VariableDeclarationStatement("a",new BooleanType()),
//                new CompositeStatement(
//                        new VariableDeclarationStatement("v", new IntegerType()),
//                        new CompositeStatement(
//                                new AssignmentStatement("a", new BinaryExpression(
//                                        new ConstantExpression(new BooleanValue(true)),
//                                        new ConstantExpression(new BooleanValue(false)),
//                                        "or"
//                                )),
//                                new CompositeStatement(
//                                        new IfStatement(
//                                                new VariableExpression("a"),
//                                                new AssignmentStatement("v",new ConstantExpression(new IntegerValue(2))),
//                                                new AssignmentStatement("v", new ConstantExpression(new IntegerValue(3)))
//                                        ),
//                                        new PrintStatement(new VariableExpression("v"))
//                                )
//                        )
//                )
//        );
        // print(1);
        // StringType s="salut";print(s);
        // IntegerType v;v=2;print(v);
        // IntegerType a;a = 2 + 3 * 5;IntegerType b;b = a - (4 * (9 / 2) + 23) / 2 + 7;print(b);
        // IntegerType a;IntegerType b;a=10;b=5;if a<b then print(a);else print(b);
        // BooleanType a;a=true;BooleanType b;b=true;IntegerType v;if a then if b then v=2213;else v=23112;else v=321312;print(v);
        View view = new View();
        view.run();

    }
}