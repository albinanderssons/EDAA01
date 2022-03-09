package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class BookReaderApplication {

	public static void main(String[] args) {
		final String delimiter = "(\\s|,|\\.|:|;|!|\\?|'|\\\")+";

		try {

			Scanner scan = new Scanner(new File("undantagsord.txt"));
			scan.useDelimiter(delimiter);

			Set<String> stopwords = new HashSet<String>();
			while (scan.hasNext()) {
				stopwords.add(scan.next().toLowerCase());
			}
			List<TextProcessor> list = new ArrayList<>();
			GeneralWordCounter counter = new GeneralWordCounter(stopwords);
			list.add(counter);
			scan.close();

			Scanner s = new Scanner(new File("nilsholg.txt"));
			s.findWithinHorizon("\uFEFF", 1);
			s.useDelimiter(delimiter);

			while (s.hasNext()) {
				String word = s.next().toLowerCase();
				list.forEach((TextProcessor p) -> p.process(word));
			}
			s.close();
			// list.forEach(TextProcessor::report);

			BookReaderController controller = new BookReaderController(counter);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
