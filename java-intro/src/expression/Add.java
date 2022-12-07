package expression;


public class Add extends BinaryOperations implements Expression, TripleExpression {

    public Add(AllExpressions expression1, AllExpressions expression2) {
        super(expression1, expression2, "+");
    }
}
