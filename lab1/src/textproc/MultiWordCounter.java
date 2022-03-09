package textproc;

import java.util.*;

public class MultiWordCounter implements TextProcessor {
	private Map<String, Integer> map;

	public MultiWordCounter(String[] landskap) {
		this.map = new HashMap<>();
		for (String s : landskap) {
			map.put(s, 0);
		}
	}

	@Override
	public void process(String w) {
		if (map.containsKey(w)) {
			map.put(w, map.get(w) + 1);
		}

	}

	@Override
	public void report() {
		for (String w : map.keySet()) {
			System.out.println(w + ": " + map.get(w));
		}

	}

}
