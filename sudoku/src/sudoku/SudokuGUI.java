package sudoku;

import java.awt.BorderLayout;	
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;

public class SudokuGUI {
	private SudokuSolver sudoku;
	private JPanel panel;
	private int boxesToSolver[][] = new int[9][9];
	private JTextField[][] boxes;
	
	public SudokuGUI(SudokuSolver sudoku) {
		this.sudoku = sudoku;
		boxes = new JTextField[9][9];
		SwingUtilities.invokeLater(() -> createWindow("Sudoko"));
	
	}

	public void createWindow(String title) {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container pane = frame.getContentPane();
		
		panel = new JPanel();		
		panel.setLayout(new GridLayout(9,9));	
		panel.setPreferredSize(new Dimension(500,500));
		
		build(true);
		
		JButton solveButton = new JButton("Solve");
		JButton clearButton = new JButton("Clear");
		
		solveButton.addActionListener((e) -> {
			if(scanGrid()) {
				sudoku.setMatrix(boxesToSolver);
				if(sudoku.isValid()) {
					if(sudoku.solve()){
						rebuildBoard();
						JOptionPane.showMessageDialog(pane, "Solved!");
					}else{
						JOptionPane.showMessageDialog(pane, "Sudoku can not be solved");
					}
					
				}else{
						JOptionPane.showMessageDialog(pane, "Sudoku can not be solved");
					}
				}
		});
		
		clearButton.addActionListener(e -> {
			clearBoard();
		});
		
		JPanel controls = new JPanel();
		
		controls.add(solveButton);
		controls.add(clearButton);
		
		pane.add(panel, BorderLayout.NORTH);
		pane.add(controls, BorderLayout.SOUTH);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	private void build(boolean firstBuild) {
		
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				int nbr = sudoku.get(r,c);
				String val = nbr > 0 ? String.valueOf(nbr) : "";
				JTextField field = new JTextField();
				
				if(firstBuild) {
					setFirstBuild(field, r, c);
					panel.add(field);
					boxes[r][c] = field;
				}			
				
				boxes[r][c].setText(val);
				if(r/3 != 1 && c/3 != 1 || r/3 == 1 && c/3 == 1) {
					boxes[r][c].setBackground(new Color(255, 110, 0));
				}
			}
		}
	}
	
	private void setFirstBuild(JTextField field, int r, int c) {
		field.setHorizontalAlignment(JTextField.CENTER);
		field.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				String t = field.getText();
				try {
					int nbr = Integer.parseInt(t);
					if(nbr <= 0) {
						throw new IllegalArgumentException();
					}
					sudoku.add(r, c, nbr);
				
				}catch(Exception error){
					sudoku.add(r,c, 0);
					field.setText("");
					setFirstBuild(field, r, c);				
				}
			}
		});
	}
	
	private void clearBoard() {
		sudoku.clear();
		build(false);
	}
	
	private void rebuildBoard() {
		build(false);	
	}
	
	private boolean scanGrid() {
		for(int i=0; i<9; i++) {
        	for(int n=0; n<9; n++) {
        		
        		if(boxes[i][n].getText()=="0"||boxes[i][n].getText()==null||boxes[i][n].getText().equals("")) {
        			boxesToSolver[i][n]=0;
        		}
        		else {
            		try {
            			boxesToSolver[i][n] = Integer.parseInt (boxes[i][n].getText());
            			if(boxesToSolver[i][n] > 9||boxesToSolver[i][n]< 0) {
            				throw new NumberFormatException();
            			}
            		}
            		catch(NumberFormatException e) {
            			System.out.println("Exception");

            			return false;
            		}
        		}

        	}
        }
		return true;
	}
	

}
