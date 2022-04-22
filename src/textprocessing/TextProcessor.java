package textprocessing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exception.ParsingFileException;

/**
 * Opens text files and reads all the words
 * <p>
 * 
 * @author nethravijayadas
 *
 */
public class TextProcessor {

	/**
	 * prints the reverse word pair
	 * 
	 * @param args
	 * @throws ParsingFileException
	 */
	public static void main(String args[]) throws ParsingFileException {
		String filePath = "books.txt";

		List<String> words = getWordsFromTextFile(filePath);
		Map<String, String> reversePairList = new HashMap<>();
		Map<String, String> listOfReverseWords = findReverseWords(reversePairList, words);
		for (String key : listOfReverseWords.keySet()) {
			System.out.println(key + ", " + listOfReverseWords.get(key));
		}
	}

	/**
	 * If there are more than 1 word in the list For each word, finds it's reverse
	 * <p>
	 * Checks if the reverse word exists in the list
	 * <p>
	 * If exists, adds it to map and removes both the word from the list
	 * <p>
	 * Else removes only the word from the list and it won't be considered again
	 * process continues recursively until list becomes empty
	 * 
	 * @param reversePairList
	 * @param words
	 * @return pair of reverse words
	 */
	private static Map<String, String> findReverseWords(Map<String, String> reversePairList, List<String> words) {
		Integer length = words.size();
		// proceed only if there are more than 1 words in the list
		if (length > 1) {
			Map<String, String> reversePair = new HashMap<>();
			String word = words.get(0);
			String reverse = new String(new StringBuffer(word).reverse()); /* reverse the string */
			List<String> wordsToRemove = new ArrayList<>();
			wordsToRemove.add(word);
			if (word.length() > 1 && words.contains(reverse)) { /* check if reverse string exists in the list */
				System.out.println(word);
				System.out.println(reverse);

				reversePair.put(word, reverse); /* add pair to map */
				wordsToRemove.add(reverse);
			}
			words.removeAll(wordsToRemove); /* remove processed words */
			findReverseWords(reversePairList, words); /* perform same for the remaining words */
		}
		return reversePairList;
	}

	/**
	 * Opens file in filePath and reads data line by line
	 * <p>
	 * Combines all the lines to form a single string
	 * <p>
	 * Splits words by comma/space/tab/new line
	 * 
	 * @param filePath
	 * @return all the words in the file filePath
	 * @throws ParsingFileException if exception occurs while opening or reading
	 *                              file
	 * @see exception.ParsingFileException
	 */
	private static List<String> getWordsFromTextFile(String filePath) throws ParsingFileException {
		try {
			StringBuffer sb = new StringBuffer();
			FileReader file = new FileReader(filePath);
			BufferedReader br = new BufferedReader(file);
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line).append(" ");
			}
			br.close();
			String text = sb.toString();
			String[] words = text.split("[ ,\\n\\s]");
			return new ArrayList<String>(Arrays.asList(words));
		} catch (IOException io) {
			throw new ParsingFileException("Exception occured in parsing file " + filePath, io);
		}
	}

}
