package textproc;

import java.util.*;
import java.util.Map.Entry;

public class WordCountComparator implements Comparator<Map.Entry<String, Integer>> {

	@Override
	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
		// TODO Auto-generated method stub
		int value = o2.getValue() - o1.getValue();

		if (value == 0) {
			value = o1.getKey().compareTo(o2.getKey());
		}

		return value;

	}
}
