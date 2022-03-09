package textproc;

import java.util.*;

public class GeneralWordCounter implements TextProcessor {

	Map<String, Integer> words;
	Set<String> stopwords;

	public GeneralWordCounter(Set<String> stopwords) {
		words = new HashMap<>();
		this.stopwords = stopwords;

	}

	@Override
	public void process(String w) {
		if (!stopwords.contains(w)) {
			words.put(w, words.getOrDefault(w, 0) + 1);
		}

	}

	@Override
	public void report() {
		// for (String w : words.keySet()) {
		// int count = words.get(w);
		// if(count >= 200) {
		// System.out.println(w + ": " + count);
		// }
		// }
		Set<Map.Entry<String, Integer>> wordSet = words.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);

		wordList.sort(new WordCountComparator());

		for (int i = 0; i < 5000; i++) {
			System.out.println(wordList.get(i).getKey() + ": " + wordList.get(i).getValue());
		}
	}

	public List<Map.Entry<String, Integer>> getWordList() {
		return new ArrayList<>(words.entrySet());
	}
}