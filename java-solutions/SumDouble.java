

public class SumDouble {
    public static void main(String []args) {
        double sum = 0.0;
        int started = 0;
        int ended;
        for (String arg : args){
            for (int i = 0 ; i < arg.length() ; i++){
                if ( (!(Character.isWhitespace(arg.charAt(i)))) && ( (i-1) < 0 || ((Character.isWhitespace(arg.charAt(i - 1)))) )) {
                    started = i;
                }
                if ((!(Character.isWhitespace(arg.charAt(i)))) && ( ((i+1) >= arg.length()) || ((Character.isWhitespace(arg.charAt(i + 1)))) )) {
                    ended = i;
                    sum = sum + Double.parseDouble(arg.substring(started , ended+1));
                }
            }
        }
        System.out.print(sum);
    }
}
