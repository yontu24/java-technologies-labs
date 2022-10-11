package ro.uaic.info.lab2.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @author ionut
 *
 */
public final class PermutationGenerator {
	private List<String> permutations;
	private int size;

	public PermutationGenerator(int size, List<Character> chars) {
		permutations = new ArrayList<>();
		this.size = size;

		permutation("", chars);
	}

	public List<String> getPermutations() {
		// get only the English words after performing a filter on permutations list
		permutations = getValidWords();

		if (size == 0)
			return permutations;

		return Collections.unmodifiableList(
				permutations.stream().filter(permutation -> permutation.length() == size).collect(Collectors.toList()));
	}

	/**
	 * Get only words outcome from permutations that are English words.
	 * 
	 * @return list with valid words
	 */
	private List<String> getValidWords() {
		return permutations.stream().filter(word -> getDictionaryWords().contains(word)).collect(Collectors.toList());
	}

	private void permutation(String prefix, List<Character> chars) {
		int n = chars.size();

		for (int i = 0; i < n; i++) {
			List<Character> newList = Stream.concat(chars.subList(0, i).stream(), chars.subList(i + 1, n).stream())
					.collect(Collectors.toList());

			permutation(prefix + chars.get(i), newList);
		}

		permutations.add(prefix);
	}

	public Set<String> getDictionaryWords() {
		return WordsCache.getInstance().getDictionaryWords();
	}

	/**
	 * Use a link{@TreeSet} to store words from a file. Each line contains one word.
	 * The class behave as a singleton class.
	 * 
	 * getInstance() method is thread-safe
	 * 
	 * @author ionut
	 *
	 */
	private static final class WordsCache {
		private static String DICTIONARY_NAME = "words.txt";
		private static WordsCache wordsCache = null;
		private Set<String> wordsMap = new TreeSet<>();

		/**
		 * private constructor used only once to read from file the words.
		 */
		private WordsCache() {
			System.out.println("WordsCache constructor()");
			try {
				readWordsFromDictionary();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/**
		 * Singleton getInstance() of this class
		 * 
		 * @return
		 */
		public static synchronized WordsCache getInstance() {
			if (wordsCache == null)
				wordsCache = new WordsCache();

			return wordsCache;
		}

		/**
		 * Read efficiently from file using link{@BufferReader}.
		 * 
		 * @throws IOException
		 */
		private void readWordsFromDictionary() throws IOException {
			File file = new File(System.getProperty("user.dir") + "\\" + DICTIONARY_NAME);
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				String word;
				while ((word = reader.readLine()) != null) {
					wordsMap.add(word);
				}
			}
			System.out.println("Successfully read " + wordsMap.size() + " words from " + DICTIONARY_NAME);
		}

		/**
		 * Get the words.
		 * 
		 * @return wordsMap
		 */
		public Set<String> getDictionaryWords() {
			return wordsMap;
		}
	}
}
