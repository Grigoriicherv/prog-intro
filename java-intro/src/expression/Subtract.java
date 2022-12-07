package expression;


public class Subtract extends BinaryOperations implements Expression, TripleExpression {
    public Subtract(AllExpressions expression1, AllExpressions expression2) {
        super(expression1, expression2, "-");
    }
}
