package expression.exceptions;

import expression.*;
import expression.parser.BaseParser;
import expression.parser.CharSource;
import expression.parser.StringSource;

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
            final AllExpressions result = parseSomethingonExpression();
            skipWhitespace();
            if (eof()) {
                return result;
            }
            checkExceptionsinExpressions();
            throw new ParsingException ("End of parser");
        }
        private AllExpressions parseSomethingonExpression(){
            skipWhitespace();
            AllExpressions result = parseExpression();
            skipWhitespace();
            while (test('s') || test('c')){
                if (take('s')){
                    expect("et");
                    final AllExpressions result2 = parseExpression();
                    result  = new Set(result, result2);

                }
                else{
                    take();
                    expect("lear");
                    final AllExpressions result2 = parseExpression();
                    result = new Clear(result, result2);

                }
            }
            return result;
        }

        private AllExpressions parseExpression() {
            skipWhitespace();
            AllExpressions result = parseTerm();
            skipWhitespace();
            while (test('+') || test('-')){
                if (take('+')){
                    skipWhitespace();
                    checkExceptionsinExpression();
                    final AllExpressions result2 = parseTerm();
                    result  = new CheckedAdd(result, result2);

                }
                else{
                    take();
                    skipWhitespace();
                    checkExceptionsinExpression();
                    final AllExpressions result2 = parseTerm();
                    result = new CheckedSubtract(result, result2);

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
                    skipWhitespace();
                    checkExceptionsinExpression();
                    final AllExpressions result2 = parseValue();
                    result  = new CheckedMultiply (result, result2);
                    skipWhitespace();
                }
                else{
                    take();
                    skipWhitespace();
                    checkExceptionsinExpression();
                    final AllExpressions result2 = parseValue();
                    result = new CheckedDivide (result, result2);
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
                    if (test('s') || test('c')){
                        throw new ParsingException("You have to do spase before set or clear");
                    }
                    try {
                        return new Const(Integer.parseInt(sb.toString()));
                    }catch (NumberFormatException e){
                        throw new ParsingException("Number is too small");
                    }
                }
                else {
                    skipWhitespace();
                    if (test('\0')) {
                        throw new ParsingException("No expression after minus"+" on position "+ super.getPosition());
                    }
                    result = parseValue();
                    return new CheckedNegate(result);
                }
            } else if (between('0', '9')) {
                final StringBuilder sb = new StringBuilder();
                takeDigits(sb);
                if (test('s') || test('c')){
                    throw new ParsingException("You have to do spase before set or clear");
                }
                try {
                    return new Const(Integer.parseInt(sb.toString()));
                }catch (NumberFormatException e){
                    throw new ParsingException("Number is too big");
                }
            } else if (take('x')) {
                return new Variable("x");
            } else if(take('y')) {
                return new Variable("y");
            } else if (take('z')) {
                return new Variable("z");
            } else if (take('(')) {
                skipWhitespace();
                if (test(')')){
                    throw new ParsingException("No arguments in brackets");
                }
                result = parseSomethingonExpression();
                expect(')');
                return result;
            } else if (between(')','/')) {
                if (between('*', '/')){
                    throw new ParsingException ("No first argument"+" on position "+ super.getPosition());
                } else {
                    throw new ParsingException("No open brackets");
                }
            } else{
                throw new ParsingException("No such symbol on position " + super.getPosition());
            }
        }
        private void takeDigits(final StringBuilder sb) {
            while (between('0', '9') ) {
                sb.append(take());
            }
        }
        private void skipWhitespace() {
            while (isItWhiteSpase()){
                take();
            }
        }
        private void checkExceptionsinExpression(){
            if(test('*') || test('/') || test('+')){
                throw new ParsingException ("No second argument in operation"+" on position "+ super.getPosition());
            } else if (test(')')) {
                throw new ParsingException ("No second argument in operation"+" on position "+ super.getPosition());
            } else if (test('\0')) {
                throw new ParsingException ("No second argument in operation"+" in the end of source ");
            }
        }
        private void checkExceptionsinExpressions(){
            if (test(')')){
                throw new ParsingException ("No open brackets");
            } else if (between('0','9')) {
                throw new ParsingException("Spaces in numbers");
            }
        }

    }
}
