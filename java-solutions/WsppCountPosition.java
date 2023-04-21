


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Arrays;
import java.util.LinkedHashMap;
public class WsppCountPosition {
    private static int [] quantofSimillarWords = new int[10];

    private static int checkonExs(String str, Map<String, Intlist> wordsAndquants, int count,
                                    int countString, int max ) {
        // getOrDefault
        if (wordsAndquants.containsKey(str)) {
            Intlist tempIntList =wordsAndquants.get(str);
            tempIntList.addIdx2(count, countString);
            quantofSimillarWords[tempIntList.getRepeats()]--;
            quantofSimillarWords[tempIntList.getRepeats()+1]++;
            if (tempIntList.getRepeats()>max){
                max = tempIntList.getRepeats();
            }
            if (tempIntList.getRepeats()+1 >= quantofSimillarWords.length-1){
                quantofSimillarWords = Arrays.copyOf(quantofSimillarWords,
                        quantofSimillarWords.length*2);
            }
        } else {
            wordsAndquants.put(str, new Intlist());
            wordsAndquants.get(str).addIdx2(count, countString);
            // quantity of words before our word that have the same numb of repeats as idx
            quantofSimillarWords[2]++;
        }
        return max;
    }

    public static void main(String[] args) {

        String inputfile = args[0];
        String outputfile = args[1];
        Map<String, Intlist> wordsAndquants = new LinkedHashMap<>();
        int count = 1;
        int countString = 1;
        String h;
        String str;

        int max= 0 ;
        int supNumb=0;

        try {
            MyScanner in = new MyScanner(new File(inputfile));
            while (in.hasNextLine("all")) {
                h = in.nextLine();
                MyScanner mysc = new MyScanner(h);
                while (mysc.hasNextWord()) {
                    str = mysc.nextWord();
                    max = checkonExs(str, wordsAndquants, count, countString, max);
                    count++;
                }
                countString++;
                count = 1;
            }
            // finally
            in.close();
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        }

        for (int j = 2; j <= max+1; j++ ){

            if (quantofSimillarWords[j]!=0) {
                quantofSimillarWords[j] = quantofSimillarWords[j] + supNumb ;
                quantofSimillarWords[j-1]=supNumb;
                supNumb = quantofSimillarWords[j];

            }
        }

        String[] sortArray = new String[wordsAndquants.size()];
        for (Map.Entry<String, Intlist> entry : wordsAndquants.entrySet()) {
            sortArray[quantofSimillarWords[entry.getValue().getRepeats()]++] = entry.getKey();
        }

        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(outputfile), StandardCharsets.UTF_8))) {
            for (String s : sortArray) {
                writer.write(s+" "+wordsAndquants.get(s).getRepeats()+" ");
                for(int i =1 ; i<wordsAndquants.get(s).getQuantityofNumb(); i=i+2){
                    if (i==wordsAndquants.get(s).getQuantityofNumb()-2){
                        writer.write(wordsAndquants.get(s).getStrNosp(i));
                    }
                    else{
                        writer.write(wordsAndquants.get(s).getStr(i));
                    }
                }
                writer.newLine();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        quantofSimillarWords = new int[10];
    }
}
