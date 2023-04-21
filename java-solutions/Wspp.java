


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.LinkedHashMap;

public class Wspp {
    private static void checkonExs(String str, Map<String, Intlist> wordsAndquants, int count) {
        if (wordsAndquants.containsKey(str)) {
            wordsAndquants.get(str).addIdx(count);
        } else {
            wordsAndquants.put(str, new Intlist());
            wordsAndquants.get(str).addIdx(count);
        }
    }

    public static void main(String[] args) {

        String inputfile = args[0];
        String outputfile = args[1];
        Map<String, Intlist> wordsAndquants = new LinkedHashMap<>();
        int count = 1;
        String h;
        String str;

        try {
            MyScanner in = new MyScanner(new File(inputfile));

            while (in.hasNextLine("all")) {
                h = in.nextLine();

                MyScanner mysc = new MyScanner(h);
                while (mysc.hasNextWord()) {
                    str = mysc.nextWord();
                    checkonExs(str, wordsAndquants, count);
                    count++;
                }
            }
            in.close();
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        }



        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(outputfile), StandardCharsets.UTF_8))) {

            for (Map.Entry<String, Intlist> entry : wordsAndquants.entrySet()) {
                writer.write(entry.getKey()+" "+entry.getValue().getRepeats()+" ");
                for(int i =1 ; i<entry.getValue().getQuantityofNumb(); i++){
                    if (i==entry.getValue().getQuantityofNumb()-1){
                        writer.write(entry.getValue().getStr1(i));
                    }
                    else{
                        writer.write(entry.getValue().getStr1(i)+" ");
                    }
                }
                writer.newLine();

            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
