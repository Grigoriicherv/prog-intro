package expression;

public final class Expressions {
    public static AllExpressions parse(final String source) {
        return parse(new StringSource(source));
    }

    public static AllExpressions parse(final CharSource source) {
        return new Expressionparser(source).parseExpression();
    }

    private static class Expressionparser extends BaseParser {
        public Expressionparser(final CharSource source) {
            super(source);
        }

        public AllExpressions parseExpressions() {
            final AllExpressions result = parseExpression();
            if (eof()) {
                return result;
            }
            throw error("End of JSON expected");
        }

        private AllExpressions parseExpression() {
            skipWhitespace();
            AllExpressions result = parseTerm();
            skipWhitespace();
            while (test('+') || test('-')){
                if (take('+')){
                    final AllExpressions result2 = parseValue();
                    result  = new Add (result, result2);
                }
                else{
                    take();
                    final AllExpressions result2 = parseValue();
                    result = new Subtract (result, result2);
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
                }
                else{
                    take();
                    final AllExpressions result2 = parseValue();
                    result = new Divide (result, result2);
                }
            }
            return result;
        }

        private AllExpressions parseValue() {
            skipWhitespace();
            final AllExpressions result;

            if (between('0', '9')){
                final StringBuilder sb = new StringBuilder();
                takeDigits(sb);
                return new Const(Integer.parseInt(sb.toString()));
            }
            else {
                char temp = take();
                switch (temp) {
                    case ('x'):
                        return new Variable("x");
                    case ('y'):
                        return new Variable("y");
                    case ('z'):
                        return new Variable("z");
                    case ('('):
                        result = parseExpression();
                        expect(')');
                        return result;
                    case ('-'):
                        result = parseExpression();
                        return new UnaryMinus(result);
                    default:
                        throw error("RRR");
                }
            }
        }

        private void takeDigits(final StringBuilder sb) {
            while (between('0', '9')) {
                sb.append(take());
            }
        }
        private void skipWhitespace() {
            while (take(' ') || take('\r') || take('\n') || take('\t')) {
            }
        }
    }
}
