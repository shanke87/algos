package solver;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class ActionListeners implements ActionListener {
		private boolean inResponse = false;
		public static final Board bd = new Board(9);
		public static final char[] buf = new char[81]; 
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			String name = button.getName();
			
			System.out.println("Button " + name + " " + e.getActionCommand() + " " + e.getModifiers());
			if(inResponse ) {
				return;
			}
			if(e.getModifiers() == 16 || e.getModifiers() == 0) {
				inResponse = true;
				setEnableStatusForAllButtons(false);

				if(name.equals("Clear")) {
					for(int i=0;i<SudokuPanel.list.size();++i) {
						SudokuPanel sp = SudokuPanel.list.get(i);
						sp.setEditable(true);
						sp.setPanelText("");
						sp.setFontColor(Color.BLACK);
						//	sp.validate();sp.repaint();
					}
				} else if(name.equals("ClearSol")) {
					System.out.println("Clear sol pressed" );
					for(int i=0;i<SudokuPanel.list.size();++i) {
						SudokuPanel sp = SudokuPanel.list.get(i);
						//System.out.println("Font colors " + i + " " + sp.getFontColor());
						Color s = sp.getFontColor();
						
						if(s.getGreen() != 255) { 
							sp.setPanelText("");
						}
						//	sp.validate();sp.repaint();
					}
				} else if(name.equals("Solve")){
					int cnt = 0;
					for(int i=0;i<SudokuPanel.list.size();++i) {
						SudokuPanel sp = SudokuPanel.list.get(i);
						String st = sp.getPanelText();
						if(!sp.getPanelText().isEmpty()) {
							//sp.setForeground(Color.GREEN);
							sp.setFontColor(Color.GREEN);
							sp.repaint();
							buf[cnt++] = st.charAt(0);
						} else {
							buf[cnt++] = '.';		
						}
					}
					bd.clear();
					bd.readBoard(buf);
					bd.printBoard(System.out, false);
					Backtracker.callBackTrack(bd);
					bd.getBoardAsCharArray(buf);
					if(Backtracker.callBackTrack(bd)) {
						for(int i=0;i<SudokuPanel.list.size();++i) {
							SudokuPanel sp = SudokuPanel.list.get(i);
							String st = sp.getPanelText();
							if(!sp.getPanelText().isEmpty()) {
								//sp.setForeground(Color.GREEN);
								//sp.setFontColor(Color.GREEN);
							} else {
								sp.setPanelText(buf[i] + "");
							}
						}
					}

				} else if(name.equals("Hint")) {

				}
				inResponse = false;
				setEnableStatusForAllButtons(true);
				SudokuGrid.sg.repaint();
			}
		}
		
		private void setEnableStatusForAllButtons(Boolean state) {
			for(int i=0;i<MenuPanel.mp.getComponentCount();++i) 
				((JButton)MenuPanel.mp.getComponent(i)).setEnabled(state);
		}

}
