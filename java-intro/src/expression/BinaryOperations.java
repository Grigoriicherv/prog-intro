package expression;

import java.util.Objects;

public class BinaryOperations implements AllExpressions {

    private final AllExpressions expression1;
    private final AllExpressions expression2;
    private final String op;

    protected BinaryOperations(AllExpressions expression1, AllExpressions expression2, String op) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.op = op;
    }

    @Override
    public boolean equals(Object expression) {
        if (this == expression) {
            return true;
        }
        if (expression == null || getClass() != expression.getClass()) {
            return false;
        }
        BinaryOperations bp = (BinaryOperations) expression;
        return expression1.equals(bp.expression1) && expression2.equals(bp.expression2) && op.equals(bp.op);
    }

    @Override
    public String toString() {
        return "(" + expression1.toString() + " " + op + " " + expression2.toString() + ")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression1, expression2, op);
    }

    @Override
    public int evaluate(int x) {
        switch (op) {
            case "+":
                return expression1.evaluate(x) + expression2.evaluate(x);
            case "-":
                return expression1.evaluate(x) - expression2.evaluate(x);
            case "/":
                return expression1.evaluate(x) / expression2.evaluate(x);
            case "*":
                return expression1.evaluate(x) * expression2.evaluate(x);
            default:
                return 0;
        }
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (op) {
            case "+":
                return expression1.evaluate(x, y, z) + expression2.evaluate(x, y, z);
            case "-":
                return expression1.evaluate(x, y, z) - expression2.evaluate(x, y, z);
            case "/":
                return expression1.evaluate(x, y, z) / expression2.evaluate(x, y, z);
            case "*":
                return expression1.evaluate(x, y, z) * expression2.evaluate(x, y, z);
            default:
                return 0;
        }
    }
}
