package expression;


public class Divide extends BinaryOperations implements Expression, TripleExpression {
    public Divide(AllExpressions expression1, AllExpressions expression2) {
        super(expression1, expression2, "/");
    }
}
