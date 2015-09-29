package solver;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display {
	public static JFrame frame = null;
	public static void showInterface(Board b) { 
        frame = new JFrame("ButtonDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
 
        //Create and set up the content pane.
		SudokuGrid sg = new SudokuGrid(9);
        frame.add(sg);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);

	}
}
