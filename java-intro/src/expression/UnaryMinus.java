package expression;

import java.util.Objects;

public class UnaryMinus implements AllExpressions{



    private final AllExpressions expression;
    UnaryMinus (AllExpressions expression){
        this.expression = expression;
    }


    @Override
    public int evaluate(int x) {
        return -expression.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return -expression.evaluate(x,y,z);
    }

    @Override
    public boolean equals(Object expression) {
        if (this == expression) {
            return true;
        }
        if (expression == null || getClass() != expression.getClass()) {
            return false;
        }
        UnaryMinus um = (UnaryMinus) expression;
        return expression.equals(um.expression);
    }
    @Override
    public int hashCode() {
        return Objects.hash(expression);
    }

    @Override
    public String toString() {
        return  "-"+expression.toString() ;
    }
}
