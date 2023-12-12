package view;

import controller.IController;
import model.exceptions.AppException;
import model.expressions.BinaryExpression;
import model.expressions.ConstantExpression;
import model.expressions.ReadHeapFunction;
import model.expressions.VariableExpression;
import model.statements.*;
import model.values.IntegerValue;
import model.values.StringValue;
import model.values.types.IntegerType;
import model.values.types.RefType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainView implements IMainView {
    IController controller;
    public MainView(IController controller) throws AppException {
        this.controller = controller;
    }

    public void handleFirstProgram() throws AppException {
        IStatement statement = new CompositeStatement(new VariableDeclarationStatement("v", new RefType(new IntegerType())), new CompositeStatement(new NewStatement("v", new ConstantExpression(new StringValue("20"))), new CompositeStatement(new VariableDeclarationStatement("a", new RefType(new RefType(new IntegerType()))), new CompositeStatement(new NewStatement("a", new VariableExpression("v")), new CompositeStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new VariableExpression("a")))))));
        this.controller.setProgram(statement);
    }

    public void handleSecondProgram() throws AppException {
        IStatement statement = new CompositeStatement(new VariableDeclarationStatement("v", new RefType(new IntegerType())), new CompositeStatement(new NewStatement("v", new ConstantExpression(new IntegerValue(20))), new CompositeStatement(new VariableDeclarationStatement("a", new RefType(new RefType(new IntegerType()))), new CompositeStatement(new NewStatement("a", new VariableExpression("v")), new CompositeStatement(new PrintStatement(new ReadHeapFunction(new VariableExpression("v"))), new PrintStatement(new BinaryExpression(new ReadHeapFunction(new ReadHeapFunction(new VariableExpression("a"))), new ConstantExpression(new IntegerValue(5)), "+")))))));
        this.controller.setProgram(statement);
    }

    public void handleThirdProgram() throws AppException {
        IStatement statement = new CompositeStatement(new VariableDeclarationStatement("v", new RefType(new IntegerType())), new CompositeStatement(new NewStatement("v", new ConstantExpression(new IntegerValue(20))), new CompositeStatement(new PrintStatement(new ReadHeapFunction(new VariableExpression("v"))), new CompositeStatement(new WriteHeapStatement(new VariableExpression("v"), new ConstantExpression(new StringValue("a"))), new PrintStatement(new BinaryExpression(new ReadHeapFunction(new VariableExpression("v")), new ConstantExpression(new IntegerValue(5)), "+"))))));
        controller.setProgram(statement);
        controller.executeAllSteps();
        this.controller.setProgram(statement);
    }

    public void handleFourthProgram() throws AppException {
        IStatement statement = new CompositeStatement(new VariableDeclarationStatement("v", new RefType(new IntegerType())), new CompositeStatement(new NewStatement("v", new ConstantExpression(new IntegerValue(20))), new CompositeStatement(new VariableDeclarationStatement("a", new RefType(new RefType(new IntegerType()))), new CompositeStatement(new NewStatement("a", new VariableExpression("v")), new CompositeStatement(new NewStatement("v", new ConstantExpression(new IntegerValue(30))), new PrintStatement(new ReadHeapFunction(new ReadHeapFunction(new VariableExpression("a")))))))));

        this.controller.setProgram(statement);
    }

    public void handleFifthProgram() throws AppException {
        IStatement statement = new CompositeStatement(new VariableDeclarationStatement("v", new IntegerType()), new CompositeStatement(new AssignmentStatement("v", new ConstantExpression(new IntegerValue(4))), new CompositeStatement(new WhileStatement(new BinaryExpression(new VariableExpression("v"), new ConstantExpression(new IntegerValue(0)), ">"), new CompositeStatement(new PrintStatement(new VariableExpression("v")), new AssignmentStatement("v", new BinaryExpression(new VariableExpression("v"), new ConstantExpression(new IntegerValue(1)), "-")))), new PrintStatement(new VariableExpression("v")))));
        this.controller.setProgram(statement);
    }

    public void handleSixthProgram() throws AppException {
        IStatement statement;
        statement = new CompositeStatement(new VariableDeclarationStatement("v", new RefType(new IntegerType())), new CompositeStatement(new NewStatement("v", new ConstantExpression(new IntegerValue(20))), new NewStatement("v", new ConstantExpression(new IntegerValue(30)))));

        this.controller.setProgram(statement);
    }

    public void handleSeventhProgram() throws AppException {
        // 7) int v;Ref int a;v=10;new(a,22);fork(wH(a,30);v=32;print(v);print(rH(a)));print(v);print(rH(a))
        IStatement statement;
        statement = new CompositeStatement(new VariableDeclarationStatement("v", new IntegerType()), new CompositeStatement(new VariableDeclarationStatement("a", new RefType(new IntegerType())), new CompositeStatement(new AssignmentStatement("v", new ConstantExpression(new IntegerValue(10))), new CompositeStatement(new NewStatement("a", new ConstantExpression(new IntegerValue(22))), new CompositeStatement(new ForkStatement(new CompositeStatement(new WriteHeapStatement(new VariableExpression("a"), new ConstantExpression(new IntegerValue(30))), new CompositeStatement(new AssignmentStatement("v", new ConstantExpression(new IntegerValue(32))), new CompositeStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeapFunction(new VariableExpression("a"))))))), new CompositeStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeapFunction(new VariableExpression("a")))))))));
        this.controller.setProgram(statement);
    }


    public void displayMenu() {
        System.out.println("1) Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)");
        System.out.println("2) Ref int v;new(v,20);Ref Ref int a; new(a,v);print(rH(v));print(rH(rH(a))+5)");
        System.out.println("3) Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5)");
        System.out.println("4) Ref int v;new(v,20);Ref Ref int a; new(a,v);new(v,30);print(rH(rH(a)))");
        System.out.println("5) int v; v=4; (while (v>0) print(v);v=v-1);print(v)");
        System.out.println("6) Ref int v;new(v,20);new(v,30);");
        System.out.println("7) int v;Ref int a;v=10;new(a,22);fork(wH(a,30);v=32;print(v);print(rH(a)));print(v);print(rH(a))");
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            this.displayMenu();
            try {
                System.out.print("Enter command>");
                String cmd = reader.readLine().strip();
                switch (cmd) {
                    case "1" -> {
                        this.handleFirstProgram();
                        try {
                            this.controller.executeAllSteps();
                        } catch (AppException exception) {
                            System.out.println(exception.getMessage());
                        }
                    }
                    case "2" -> {
                        this.handleSecondProgram();
                        try {
                            this.controller.executeAllSteps();
                        } catch (AppException exception) {
                            System.out.println(exception.getMessage());
                        }
                    }
                    case "3" -> {
                        this.handleThirdProgram();
                        try {
                            this.controller.executeAllSteps();
                        } catch (AppException exception) {
                            System.out.println(exception.getMessage());
                        }
                    }
                    case "4" -> {
                        this.handleFourthProgram();
                        try {
                            this.controller.executeAllSteps();
                        } catch (AppException exception) {
                            System.out.println(exception.getMessage());
                        }
                    }
                    case "5" -> {
                        this.handleFifthProgram();
                        try {
                            this.controller.executeAllSteps();
                        } catch (AppException exception) {
                            System.out.println(exception.getMessage());
                        }
                    }
                    case "6" -> {
                        this.handleSixthProgram();
                        try {
                            this.controller.executeAllSteps();
                        } catch (AppException exception) {
                            System.out.println(exception.getMessage());
                        }
                    }
                    case "7" -> {
                        this.handleSeventhProgram();
                        try {
                            this.controller.executeAllSteps();
                        } catch (AppException exception) {
                            System.out.println(exception.getMessage());
                        }
                    }
                    case "exit" -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> {
                        System.out.println("Invalid command!");
                    }
                }
            } catch (AppException exception) {
                System.out.println(exception.getMessage());
                break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}