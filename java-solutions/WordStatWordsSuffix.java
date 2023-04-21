

import java.io.*;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import java.util.Arrays;

public class WordStatWordsSuffix {
    private static void checkonExs(String str, HashMap<String, Integer> wordsAndquants) {
        if (str.length() > 3) {
            str = str.substring(str.length() - 3);
        }
        if (wordsAndquants.containsKey(str)) {

            wordsAndquants.put(str, wordsAndquants.get(str) + 1);

        } else {

            wordsAndquants.put(str, 1);

        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        String inputFile = args[0];
        String outputFile = args[1];

        HashMap<String, Integer> wordsAndquants = new HashMap<>();

        String h;
        String str;

        try (MyScanner in = new MyScanner(new File(inputFile))){


            while (in.hasNextLine("all")) {
                h = in.nextLine();

                MyScanner mysc = new MyScanner(h);
                while (mysc.hasNextWord()) {
                    str = mysc.nextWord();
                    checkonExs(str, wordsAndquants);
                }
            }
        }
        catch(FileNotFoundException e){
            System.err.println(e.getMessage());
        }


        String[] massivesort = new String[wordsAndquants.size()];
        wordsAndquants.keySet().toArray(massivesort);
        Arrays.sort(massivesort);
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8))) {

            for (int i = 0; i < wordsAndquants.size(); i++) {
                writer.write(massivesort[i] + " " + wordsAndquants.get(massivesort[i]) + System.lineSeparator());
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
