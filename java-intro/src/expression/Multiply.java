package expression;


public class Multiply extends BinaryOperations implements Expression, TripleExpression {
    public Multiply(AllExpressions expression1, AllExpressions expression2) {
        super(expression1, expression2, "*");
    }
}
