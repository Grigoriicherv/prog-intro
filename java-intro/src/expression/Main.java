package expression;

import expression.parser.ExpressionParser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int x = sc.nextInt();
//        System.out.println(new Add(new Subtract(new Multiply(new Variable("x"), new Variable("y")),
//                new Multiply(new Const(2), new Variable("z"))), new Const(1)).evaluate(x));
        ExpressionParser expr = new ExpressionParser();
        System.out.println(expr.parse("y + z + (-30 + y)").toMiniString());
        //System.out.println(expr.parse("      -1").toString());


        //System.out.println(Expressions.parse("(25 * 2 +6*32)*41 + --(11 - (7/5 +23))"));
    }
}
