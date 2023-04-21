

import java.io.*;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

public class WordStatInput {


	private static void checkonExs(String str, LinkedHashMap<String, Integer> wordsAndquants) {
		if (wordsAndquants.containsKey(str)) {

			wordsAndquants.put(str, wordsAndquants.get(str) + 1);

		} else {

			wordsAndquants.put(str, 1);

		}
	}

	public static void main(String[] args) {

		String inputfile = args[0];
		String outputfile = args[1];

		LinkedHashMap<String, Integer> wordsAndquants = new LinkedHashMap<>();

		String h;
		String str;

		try (MyScanner in = new MyScanner(new File(inputfile))) {

			while (in.hasNextLine("all")) {
				h = in.nextLine();

				MyScanner mysc = new MyScanner(h);
				while (mysc.hasNextWord()) {
					str = mysc.nextWord();

					checkonExs(str, wordsAndquants);

				}

			}
		}
		catch (FileNotFoundException e){
			System.err.println(e.getMessage());
		}

		String[] massivesort = new String[wordsAndquants.size()];
		wordsAndquants.keySet().toArray(massivesort);

		try (BufferedWriter writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(outputfile), StandardCharsets.UTF_8))) {

			for (int i = 0; i < wordsAndquants.size(); i++) {
				writer.write(massivesort[i] + " " + wordsAndquants.get(massivesort[i]) + System.lineSeparator());

			}


		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
