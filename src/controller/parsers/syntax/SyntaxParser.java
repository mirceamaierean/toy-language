package controller.parsers.syntax;

import controller.parsers.expressions.ExpressionParser;
import controller.parsers.expressions.exceptions.InvalidExpressionAppException;
import controller.parsers.syntax.exceptions.SyntaxAppException;
import model.expressions.IExpression;
import model.statements.*;
import model.values.types.*;
import utils.IntegerReference;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
    TYPE = IntegerType|BooleanType
    NAME="([a-zA-Z_])+[a-zA-Z0-9_]*"
    VariableDeclarationStatement = "TYPE NAME"
    AssignmentStatement = "NAME=Expression"
    IfStatement = "If Expression Then Statement1 else Statement2"
    PrintStatement = "print(Expression)"
    NoOperationStatement = ""
    CompositeStatement = "Statement1;Statement2"
    Statement = {Statement}
 */

public class SyntaxParser {
    private static final IType[] types = {new IntegerType(), new BooleanType(), new StringType()};
    private static final Character[] whiteSpace = {' ', '\t', '\n', '\r'};
    private static final Pattern namePattern = Pattern.compile("^(([a-zA-Z_])+[a-zA-Z0-9_]*)");

    private static void skipWhiteSpace(String string, IntegerReference position) {
        while (position.getValue() < string.length() && Arrays.asList(whiteSpace).contains(string.charAt(position.getValue()))) {
            position.increase(1);
        }
    }

    private static IType extractType(String string, IntegerReference position) throws SyntaxAppException {
        // check and find match for the type, the options are IntegerType and BooleanType
        for (IType type : types) {
            if (position.getValue() + type.toString().length() <= string.length() && string.startsWith(type.toString(), position.getValue())) {
                position.increase(type.toString().length());
                return type;
            }
        }
        throw new SyntaxAppException("No type present");
    }

    private static String extractName(String string, IntegerReference position) throws SyntaxAppException {
        Matcher matcher = namePattern.matcher(string.substring(position.getValue()));
        if (!matcher.find()) {
            throw new SyntaxAppException("No name found");
        }
        String answer = matcher.group(1);
        position.increase(matcher.group(0).length());
        return answer;
    }


    private static IStatement parseAssignment(String string, IntegerReference position) throws SyntaxAppException, InvalidExpressionAppException {
        skipWhiteSpace(string, position);
        String name = extractName(string, position);
        skipWhiteSpace(string, position);
        if (position.getValue() >= string.length() || string.charAt(position.getValue()) != '=') {
            throw new SyntaxAppException("Invalid assignment");
        }
        position.increase(1);
        skipWhiteSpace(string, position);
        return new AssignmentStatement(name, ExpressionParser.parseAtPosition(string, position));
    }

    private static IStatement parseVariableDeclaration(String string, IntegerReference position) throws SyntaxAppException {
        skipWhiteSpace(string, position);
        IType type = extractType(string, position);
        skipWhiteSpace(string, position);
        String name = extractName(string, position);
        return new VariableDeclarationStatement(name, type);
    }

    private static IStatement parsePrint(String string, IntegerReference position) throws SyntaxAppException, InvalidExpressionAppException {
        skipWhiteSpace(string, position);
        if (position.getValue() + 5 >= string.length() || !string.startsWith("print(", position.getValue())) {
            throw new SyntaxAppException("Invalid print statement");
        }
        position.increase(5);
        return new PrintStatement(ExpressionParser.parseAtPosition(string, position));
    }

    private static IStatement parseIf(String string, IntegerReference position) throws SyntaxAppException, InvalidExpressionAppException {
        skipWhiteSpace(string, position);

        // "if" not present, invalid syntax of the statement
        if (position.getValue() + 2 >= string.length())
            throw new SyntaxAppException("Invalid if syntax");

        String ifBegin = string.substring(position.getValue(), position.getValue() + 3);

        if (!(ifBegin.startsWith("if ") || ifBegin.startsWith("if(")))
            throw new SyntaxAppException("Invalid if syntax");


        position.increase(2);
        IExpression cond = ExpressionParser.parseAtPosition(string, position);
        skipWhiteSpace(string, position);

        // "then" not present, invalid syntax of the statement
        String thenBegin = string.substring(position.getValue(), position.getValue() + 5);

        if (!(thenBegin.startsWith("then ") || thenBegin.startsWith("then{")))
            throw new SyntaxAppException("Invalid if syntax");

        // increase the position, to be ready for the next match required
        position.increase(4);
        IStatement first = parseNonComposite(string, position);
        skipWhiteSpace(string, position);

        position.increase(1);

        // "else" not present, invalid syntax of the statement
        String elseBegin = string.substring(position.getValue(), position.getValue() + 5);
        if (!(elseBegin.startsWith("else ") || elseBegin.startsWith("else{")))
            throw new SyntaxAppException("Invalid if syntax");

        position.increase(4);
        IStatement second = parseNonComposite(string, position);
        skipWhiteSpace(string, position);

        return new IfStatement(cond, first, second);
    }


    private static IStatement parseNoOperation(String string, IntegerReference position) throws SyntaxAppException {
        skipWhiteSpace(string, position);
        if (position.getValue() >= string.length() || string.charAt(position.getValue()) != ';') {
            throw new SyntaxAppException("Invalid NOP statement");
        }
        return new NoOperationStatement();
    }

    private static IStatement parseOpenRFile(String string, IntegerReference position) throws SyntaxAppException, InvalidExpressionAppException {
        skipWhiteSpace(string, position);
        if (position.getValue() + 9 >= string.length() || !string.startsWith("openRFile(", position.getValue())) {
            throw new SyntaxAppException("Invalid openRFile statement");
        }
        position.increase(9);
        return new OpenFileStatement(ExpressionParser.parseAtPosition(string, position));
    }

    private static IStatement parseCloseRFile(String string, IntegerReference position) throws SyntaxAppException, InvalidExpressionAppException {
        skipWhiteSpace(string, position);
        if (position.getValue() + 10 >= string.length() || !string.startsWith("closeRFile(", position.getValue())) {
            throw new SyntaxAppException("Invalid closeRFile statement");
        }
        position.increase(10);
        return new CloseFileStatement(ExpressionParser.parseAtPosition(string, position));
    }

    private static IStatement parseReadFile(String string, IntegerReference position) throws SyntaxAppException, InvalidExpressionAppException {
        skipWhiteSpace(string, position);
        if (position.getValue() + 8 >= string.length() || !string.startsWith("readFile(", position.getValue())) {
            throw new SyntaxAppException("Invalid readFile statement");
        }
        position.increase(9);
        IExpression expression = ExpressionParser.parseAtPosition(string, position);
        skipWhiteSpace(string, position);
        if (position.getValue() >= string.length() || string.charAt(position.getValue()) != ',') {
            throw new SyntaxAppException("Invalid readFile statement");
        }
        position.increase(1);
        skipWhiteSpace(string, position);
        String name = extractName(string, position);
        skipWhiteSpace(string, position);
        if (position.getValue() >= string.length() || string.charAt(position.getValue()) != ')') {
            throw new SyntaxAppException("Invalid readFile statement");
        }
        position.increase(1);
        return new ReadFileStatement(expression, name);
    }

    private static IStatement parseNonComposite(String string, IntegerReference position) throws SyntaxAppException, InvalidExpressionAppException {
        skipWhiteSpace(string, position);
        if (position.getValue() >= string.length()) {
            return null;
        }
        if (string.charAt(position.getValue()) == '{') {
            position.increase(1);
            IStatement statement = parseAtPosition(string, position);
            if (string.charAt(position.getValue()) != '}') {
                throw new SyntaxAppException("Invalid syntax");
            }
            position.increase(1);
            return statement;
        }
        if (string.charAt(position.getValue()) == '}') {
            return null;
        }
        if (string.charAt(position.getValue()) == ';') {
            return parseNoOperation(string, position);
        }
        if (position.getValue() + 4 < string.length() && string.startsWith("print", position.getValue())) {
            return parsePrint(string, position);
        }
        if (position.getValue() + 8 < string.length() && string.startsWith("openRFile", position.getValue())) {
            return parseOpenRFile(string, position);
        }
        if (position.getValue() + 9 < string.length() && string.startsWith("closeRFile", position.getValue())) {
            return parseCloseRFile(string, position);
        }
        if (position.getValue() + 7 < string.length() && string.startsWith("readFile", position.getValue())) {
            return parseReadFile(string, position);
        }
        if (position.getValue() + 2 < string.length() && string.startsWith("if", position.getValue())) {
            return parseIf(string, position);
        }
        int pos = position.getValue();
        boolean hasEqual = false;
        while (!hasEqual && pos < string.length() && string.charAt(pos) != ';') {
            hasEqual = (string.charAt(pos) == '=');
            pos++;
        }
        if (hasEqual) {
            return parseAssignment(string, position);
        }
        return parseVariableDeclaration(string, position);
    }


    private static IStatement parseAtPosition(String string, IntegerReference position) throws SyntaxAppException, InvalidExpressionAppException {
        skipWhiteSpace(string, position);
        if (position.getValue() >= string.length()) {
            return null;
        }
        if (position.getValue() < string.length() && string.charAt(position.getValue()) == '}') {
            return null;
        }

        IStatement currentStatement = parseNonComposite(string, position);

        skipWhiteSpace(string, position);
        if (position.getValue() >= string.length() || string.charAt(position.getValue()) != ';') {
            throw new SyntaxAppException("Missing ;");
        }
        position.increase(1);
        IStatement nextStatement = parseAtPosition(string, position);
        if (nextStatement == null) {
            return currentStatement;
        }
        currentStatement = new CompositeStatement(currentStatement, nextStatement);

        return currentStatement;
    }

    public static IStatement parse(String string) throws SyntaxAppException, InvalidExpressionAppException {
        return parseAtPosition(string, new IntegerReference(0));
    }
}