package solver;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.FileNotFoundException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SudokuGrid extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1457183911846459882L;
	public static SudokuGrid sg ; 
	public static JPanel status;
    SudokuGrid(int n) {
//    	super(new BoxLayout(this, BoxLayout.Y_AXIS))
        super(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        //GridBagConstraints cout = new GridBagConstraints();
        /** construct the grid */
    //	SudokuPanel kk = new SudokuPanel(k, l , '1');
		
        for(int i=0;i<n;++i)
        	for(int j=0;j<n;++j)
        		SudokuPanel.list.add(null);
        int sqn = (int) Math.sqrt(n);
        for (int i=0; i<sqn; i++) 
            for (int j=0; j<sqn; j++) { 
            	JPanel sj = new JPanel(new GridBagLayout());
            	for(int k=0; k<sqn; ++k)
            		for(int l=0; l<sqn; ++l)
            		{
            			c.weightx = 1.0;
            			c.weighty = 1.0;
            			c.fill = GridBagConstraints.BOTH;
            			c.gridx = k;
            			c.gridy = l;

            			SudokuPanel tt = new SudokuPanel(k, l , '1');
            			int ind = (i*sqn + k)*n + j*sqn + l;
            			SudokuPanel.list.set(ind,tt);
            			sj.add(tt, c);
            		}
            	sj.setSize(150, 150);
            	sj.setMaximumSize(new Dimension(150,150));
            	sj.setMinimumSize(new Dimension(150,150));

            	sj.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            	c.weightx = 1.0;
    			c.weighty = 1.0;
    			c.fill = GridBagConstraints.BOTH;
    			c.gridx = i;
    			c.gridy = j;
   
            	this.add(sj,c);
            }
        
        for(int i=0;i<SudokuPanel.list.size();++i)
        	assert(SudokuPanel.list.get(i) != null);
        /** create a black border */ 
        setBorder(BorderFactory.createLineBorder(Color.black)); 
        setSize(300,300);
        setMaximumSize(new Dimension(300, 300));
        
    }
    
    private static void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new JFrame("SudokuSolver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(new MenuPanel());
        sg = new SudokuGrid(9);
        frame.add(sg);

        //Display the window.
        frame.pack();
        frame.setSize(500,500);
        frame.setMaximumSize(new Dimension(500,500));
        frame.setVisible(true);
        if(loadFile != null) {
			char[] bdchars = SolverApp.readBoardStringFromFile(loadFile);
			for(int i=0;i<SudokuPanel.list.size();++i) {
				SudokuPanel sp = SudokuPanel.list.get(i);
				sp.setEditable(true);
				if(bdchars[i] != '0' && bdchars[i] != '.') {
					sp.setPanelText(bdchars[i] + "");
					sp.setFontColor(Color.GREEN);
				}
			}
        }
        
    }
    public static String loadFile = null;
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
    	if(args.length > 0) {
    		loadFile = args[0];
    	} else {
    		loadFile = null;
    	}
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); 
            }
        });
    }


}