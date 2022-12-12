package expression.parser;

import expression.*;

public final class ExpressionParser implements TripleParser {
    public TripleExpression parse(final String source) {
        return (TripleExpression) parse(new StringSource(source));
    }

    public static AllExpressions parse(final CharSource source) {
        return new Expressionsparser(source).parseExpressions();
    }

    private static class Expressionsparser extends BaseParser {
        public Expressionsparser(final CharSource source) {
            super(source);
        }

        public AllExpressions parseExpressions() {
            skipWhitespace();
            final AllExpressions result = parseExpression();
            skipWhitespace();
            if (eof()) {
                return result;
            }
            throw error("End of parser");
        }

        private AllExpressions parseExpression() {
            skipWhitespace();
            AllExpressions result = parseTerm();
            skipWhitespace();
            while (test('+') || test('-')){
                if (take('+')){
                    final AllExpressions result2 = parseTerm();
                    result  = new Add(result, result2);

                }
                else{
                    take();
                    final AllExpressions result2 = parseTerm();
                    result = new Subtract(result, result2);

                }
            }
            return result;
        }
        private AllExpressions parseTerm(){
            skipWhitespace();
            AllExpressions result = parseValue();
            skipWhitespace();
            while (test('*') || test('/')){
                if (take('*')){
                    final AllExpressions result2 = parseValue();
                    result  = new Multiply (result, result2);
                    skipWhitespace();
                }
                else{
                    take();
                    final AllExpressions result2 = parseValue();
                    result = new Divide (result, result2);
                    skipWhitespace();
                }
            }
            return result;
        }

        private AllExpressions parseValue() {
            skipWhitespace();
            final AllExpressions result;
            if (take('-')){
                if (between('0', '9')){
                    final StringBuilder sb = new StringBuilder();
                    sb.append('-');
                    takeDigits(sb);
                    return new Const(Integer.parseInt(sb.toString()));
                }
                else {
                    result = parseValue();
                    return new UnaryMinus(result);
                }
            } else if (between('0', '9')) {
                final StringBuilder sb = new StringBuilder();
                takeDigits(sb);
                return new Const(Integer.parseInt(sb.toString()));
            } else if (take('x')) {
                return new Variable("x");
            }
            else if(take('y')) {
                return new Variable("y");

            } else if (take('z')) {
                return new Variable("z");
            } else if (take('(')) {
                result = parseExpression();
                expect(')');
                return result;
            }
            else{
                throw error("No such symbol");
            }
        }
        private void takeDigits(final StringBuilder sb) {
            while (between('0', '9') ) {
                sb.append(take());
            }
        }
        private void skipWhitespace() {
            while (take(' ') || take('\r') || take('\n') || take('\t')
                    || take((char) 12) || take((char) 11) || take('\u2029')) {
            }
        }
    }
}
