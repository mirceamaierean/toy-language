package controller.parsers.expressions;

/* Expression grammar:
    expr = (expr)
    expr = term/factor/... operator expr
    expr = name
    expr = constant/true/false
 */

import controller.parsers.expressions.exceptions.InvalidExpressionAppException;
import controller.parsers.expressions.exceptions.WrongMatchAppException;
import model.expressions.*;
import model.values.BooleanValue;
import model.values.IntegerValue;
import model.values.StringValue;
import utils.IntegerReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.max;

public class ExpressionParser {

    private static final Character[] whiteSpace = {' ', '\t', '\n', '\r'};

    private static void skipWhiteSpace(String string, IntegerReference position) {
        while (position.getValue() < string.length() && Arrays.asList(whiteSpace).contains(string.charAt(position.getValue()))) {
            position.increase(1);
        }
    }

    private static int extractInteger(String string, IntegerReference position) throws WrongMatchAppException {
        skipWhiteSpace(string, position);
        int pos = position.getValue();
        if (!(Character.isDigit(string.charAt(pos)) || string.charAt(pos) == '-')) {
            throw new WrongMatchAppException("No integer here");
        }
        int sign = 1;
        if (string.charAt(pos) == '-') {
            sign = -1;
            pos += 1;
        }
        if (pos >= string.length() || !Character.isDigit(string.charAt(pos))) {
            throw new WrongMatchAppException("No integer here");
        }

        int answer = 0;

        while (pos < string.length() && Character.isDigit(string.charAt(pos))) {
            answer = answer * 10 + string.charAt(pos) - '0';
            pos += 1;
        }

        position.setValue(pos);
        return answer * sign;
    }

    private static String extractString(String string, IntegerReference position) throws WrongMatchAppException {
        skipWhiteSpace(string, position);
        int pos = position.getValue();
        if (!(string.charAt(pos) == '"')) {
            throw new WrongMatchAppException("No string here");
        }

        pos += 1;
        StringBuilder answer = new StringBuilder();

        while (pos < string.length() && string.charAt(pos) != '"') {
            answer.append(string.charAt(pos));
            pos += 1;
        }
        if (pos >= string.length() || string.charAt(pos) != '"') {
            throw new WrongMatchAppException("No string here");
        }
        pos += 1;

        position.setValue(pos);
        return answer.toString();
    }

    private static boolean extractBoolean(String string, IntegerReference position) throws WrongMatchAppException {
        skipWhiteSpace(string, position);
        if (position.getValue() + 3 < string.length()) {
            if (string.startsWith("true", position.getValue())) {
                position.increase(4);
                return true;
            }
        }
        if (position.getValue() + 4 < string.length()) {
            if (string.startsWith("false", position.getValue())) {
                position.increase(5);
                return false;
            }
        }
        throw new WrongMatchAppException("No boolean here");
    }

    private static String extractName(String string, IntegerReference position) throws InvalidExpressionAppException {
        skipWhiteSpace(string, position);
        StringBuilder name = new StringBuilder();
        List<Character> delimiterTokens = new ArrayList<>();
        for (String[] operator_list : OperatorPriority.operators) {
            for (String operator : operator_list) {
                if (operator.length() > 1) {
                    continue;
                }
                delimiterTokens.add(operator.charAt(0));
            }
        }
        delimiterTokens.add('(');
        delimiterTokens.add(')');
        delimiterTokens.addAll(Arrays.asList(whiteSpace));
        delimiterTokens.add('"');
        delimiterTokens.add('\'');
        delimiterTokens.add(';');
        delimiterTokens.add(',');

        while (position.getValue() < string.length() && !delimiterTokens.contains(string.charAt(position.getValue()))) {
            name.append(string.charAt(position.getValue()));
            position.increase(1);
        }
        if (name.isEmpty()) {
            throw new InvalidExpressionAppException("Invalid name");
        }
        return name.toString();
    }

    private static IExpression extractTerm(String string, IntegerReference position) throws InvalidExpressionAppException {
        skipWhiteSpace(string, position);
        if (string.charAt(position.getValue()) == '(') {
            position.increase(1);
            IExpression tmp = parseAtPositionWithOperator(string, position, 0);
            if (position.getValue() >= string.length() || string.charAt(position.getValue()) != ')') {
                throw new InvalidExpressionAppException("Expression is invalid, unbalanced parenthesis");
            }
            position.increase(1);
            return tmp;
        }
        if (position.getValue() + 8 < string.length() && string.startsWith("readHeap(", position.getValue())) {
            position.increase(9);
            IExpression expression = ExpressionParser.parseAtPosition(string, position);
            if (position.getValue() >= string.length() || string.charAt(position.getValue()) != ')') {
                throw new InvalidExpressionAppException("Expression is invalid, unbalanced parenthesis");
            }
            position.increase(1);
            return new ReadHeapFunction(expression);
        }
        try {
            return new ConstantExpression(new BooleanValue(extractBoolean(string, position)));
        } catch (WrongMatchAppException ignored) {

        }
        try {
            return new ConstantExpression(new IntegerValue(extractInteger(string, position)));
        } catch (WrongMatchAppException ignored) {

        }

        try {
            return new ConstantExpression(new StringValue(extractString(string, position)));
        } catch (WrongMatchAppException ignored) {

        }
        return new VariableExpression(extractName(string, position));
    }

    private static String extractOperator(String string, IntegerReference position, String[] currentOperators) {
        skipWhiteSpace(string, position);
        if (position.getValue() >= string.length()) {
            return null;
        }
        List<String> operators = new ArrayList<>();
        int minLength = -1;
        int maxLength = -1;
        for (String operator : currentOperators) {
            operators.add(operator);
            maxLength = max(maxLength, operator.length());
            if (minLength == -1 || minLength > operator.length()) {
                minLength = operator.length();
            }
        }

        for (int length = minLength; length <= maxLength; length++) {
            if (position.getValue() + length - 1 >= string.length()) {
                break;
            }
            if (operators.contains(string.substring(position.getValue(), position.getValue() + length))) {
                position.increase(length);
                return string.substring(position.getValue() - length, position.getValue());
            }
        }

        return null;
    }

    private static IExpression parseAtPositionWithOperator(String string, IntegerReference position, int currentOperator) throws InvalidExpressionAppException {
        skipWhiteSpace(string, position);
        if (position.getValue() >= string.length()) {
            return null;
        }
        if (currentOperator >= OperatorPriority.operators.length) { ///we can assume we have either a variable/constant/true/false either parenthesis
            return extractTerm(string, position);
        }

        IExpression currentExpression = parseAtPositionWithOperator(string, position, currentOperator + 1);
        if (currentExpression == null) {
            throw new InvalidExpressionAppException("Something wrong happened");
        }

        while (true) {
            if (position.getValue() >= string.length()) {
                break;
            }
            String operator = extractOperator(string, position, OperatorPriority.operators[currentOperator]);
            if (operator == null) {
                break;
            }
            currentExpression = new BinaryExpression(currentExpression, parseAtPositionWithOperator(string, position, currentOperator + 1), operator);
        }
        return currentExpression;
    }

    public static IExpression parseAtPosition(String string, IntegerReference position) throws InvalidExpressionAppException {
        return parseAtPositionWithOperator(string, position, 0);
    }

    public static IExpression parse(String string) throws InvalidExpressionAppException {
        return parseAtPositionWithOperator(string, new IntegerReference(0), 0);
    }
}