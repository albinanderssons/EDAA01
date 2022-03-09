package textproc;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Map.Entry;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class BookReaderController {
	public String file;

	public BookReaderController(GeneralWordCounter counter) {
		SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 100, 300));

	}

	private void createWindow(GeneralWordCounter counter, String title, int width, int height) {

		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();

		SortedListModel<Entry<String, Integer>> model = new SortedListModel<>(counter.getWordList());
		JList<Entry<String, Integer>> list = new JList<>(model);
		JScrollPane scrollPane = new JScrollPane(list);
		pane.add(scrollPane, BorderLayout.NORTH);
		scrollPane.setPreferredSize(new Dimension(200, 300));

		// Controlls
		JPanel controls = new JPanel();

		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
			this.file = chooser.getSelectedFile().getName();
		}

		JRadioButton alphab = new JRadioButton("Alphabetic");
		JRadioButton freq = new JRadioButton("Frequency");

		JTextField searchText = new JTextField();
		JButton search = new JButton("Search");
		searchText.setPreferredSize(new Dimension(200, 24));

		// add
		controls.add(alphab);
		controls.add(freq);
		controls.add(searchText);
		controls.add(search);
		controls.add(chooser);

		// Listeners
		alphab.addActionListener((e) -> {
			model.sort((m1, m2) -> m1.getKey().compareTo(m2.getKey()));
			freq.setSelected(false);
		});

		freq.addActionListener((e) -> {
			model.sort((m1, m2) -> m1.getValue().compareTo(m2.getValue()));
			alphab.setSelected(false);
		});

		search.addActionListener((e) -> {
			for (int i = 0; i < model.getSize(); i++) {

				if (model.getElementAt(i).getKey().equals(searchText.getText().trim().toLowerCase())) {
					list.ensureIndexIsVisible(i);
					list.setSelectedIndex(i);
					return;
				}
			}
			JOptionPane.showMessageDialog(frame, "Ordet hittades inte");
		});

		searchText.addActionListener(e -> search.doClick());

		pane.add(controls, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible(true);
	}

}