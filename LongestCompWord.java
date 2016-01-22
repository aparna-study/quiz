import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;

public class LongestCompWord {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader inputBuffer = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<String> arrayListWords = new ArrayList<String>();
		String word;
		System.out.println("PLease Enter words you want to test");

		try {
			while ((word = inputBuffer.readLine()) != null) {
				word = word.trim();
				if (word.length() == 0)
					break;
				if (word.contains(" ")) {
					String[] wordArray = word.split("\\s+");
					for (int i = 0; i < wordArray.length; i++) {
						arrayListWords.add(wordArray[i].toLowerCase());
					}
				} else {
					arrayListWords.add(word.toLowerCase());
				}

			}

		} catch (IOException ex) {
			System.out.println("Error reading input");
		}

		System.out.println("Longest Compound word is: " + longestCompWord(arrayListWords));

	}

	private static String longestCompWord(ArrayList<String> inputListWords) {
		String longestWord = null;
		TreeMap<String, Integer> wordTree = new TreeMap<String, Integer>(new Comparator<String>() {
			public int compare(String str1, String str2) {
				return str1.length() - str2.length();
			}
		});

		for (int i = 0; i < inputListWords.size(); i++) {
			wordTree.put(inputListWords.get(i), i);
		}

		return searchLongestCompWord(wordTree, inputListWords);
	}

	private static String searchLongestCompWord(TreeMap<String, Integer> wordTree,
			ArrayList<String> inputListWords) {
		while (wordTree.size() > 0) {
			String word = wordTree.lastKey();
			wordTree.remove(word);
			inputListWords.remove(word);
			if (isCompWord(word, inputListWords))
				return word;
		}
		return "";
	}

	private static boolean isCompWord(String word, ArrayList<String> inputListWords) {
		if (inputListWords.contains(word)) {
			return true;
		}
		for (int i = 1; i < word.length(); i++) {
			String perfix = word.substring(0, i);
			if (isCompWord(perfix, inputListWords)) {
				String remainder = word.substring(i, word.length());
				if (remainder.length() == 0)
					return true;
				return isCompWord(remainder, inputListWords);
			}
		}
		return false;
	}
}
