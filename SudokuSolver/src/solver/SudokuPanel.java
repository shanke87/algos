package solver;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

class SudokuPanel extends JPanel {

    char digit; //the number it would display
    int x, y; //the x,y position on the grid
    boolean editable,isProblem;
    public static ArrayList<SudokuPanel> list = new ArrayList<SudokuPanel>(81);
    SudokuPanel(int x, int y, char c) {
        super();

        this.x = x;
        this.y = y;
        this.digit = c;
        	JTextArea tmp = new JTextArea(1,1);
        	tmp.setEditable(true);
        	tmp.setDocument(new SudokuTextField(1));
        	tmp.setSize(50, 50);
        	//tmp.setBackground(Color.TRANSLUCENT);
        	tmp.setMaximumSize(new Dimension(50,50));
        	tmp.setMinimumSize(new Dimension(50,50));
        	tmp.setAlignmentX(CENTER_ALIGNMENT);
        	tmp.setAlignmentY(CENTER_ALIGNMENT);
        	tmp.setFont(new Font("Arial",Font.BOLD,30));
        	this.add(tmp);
        /** create a black border */
        setBorder(BorderFactory.createLineBorder(Color.black));
        /** set size to 50x50 pixel for one square */
        setSize(150,150);
    }

    
    private void setPreferredSize(int i, int j) {
    	this.setSize(i, j);
	}
    
    public char getDigit() { return digit; }

    //getters for x and y

    public void setDigit(int num) { 
    	digit = (char) num;
    }


	public boolean isEditable() {
		JTextArea jt = (JTextArea) this.getComponent(0);
		return jt.isEditable();
	}

	public String getPanelText() {
		JTextArea jt = (JTextArea) this.getComponent(0);
		return jt.getText();
	}
	
	public void setPanelText(String str) {
		JTextArea jt = (JTextArea) this.getComponent(0);
		jt.setText(str);
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
		JTextArea jt = (JTextArea) this.getComponent(0);
		jt.setEditable(editable);
	}


	public boolean isProblem() {
		return isProblem;
	}


	public void setProblem(boolean isProblem) {
		this.isProblem = isProblem;
	}


	public void setFontColor(Color col) {
		JTextArea jt = (JTextArea) this.getComponent(0);
		jt.setForeground(col);
	}
	
	public Color getFontColor() {
		// TODO Auto-generated method stub
		JTextArea jt = (JTextArea) this.getComponent(0);

		return jt.getForeground();
	}

}