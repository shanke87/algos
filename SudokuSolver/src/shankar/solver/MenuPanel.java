package solver;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class MenuPanel extends JMenuBar {
	private static final long serialVersionUID = -6367430197444144832L;
	public static MenuPanel mp;
	public static String[][] props =  { { "Clear" }, { "ClearSol" }, { "Solve" } };
	MenuPanel() {
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		ActionListeners al = new ActionListeners();
		JButton jb ;
		for(int i=0;i<props.length;++i) {
			jb = new JButton(props[i][0]);
			jb.setName(props[i][0]);
			jb.addActionListener(al);
			this.add(jb);
		}
		this.setLocation(0, 120);
		mp = this;
	}
}
