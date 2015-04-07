package SternGerlachGUI;

import java.awt.HeadlessException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {
	public MenuBar() throws HeadlessException {
		JMenu menu = new JMenu("Menu");
		add(menu);

		JMenuItem closeFrame = new JMenuItem("Close");
		menu.add(closeFrame);
		
		JMenuItem export = new JMenuItem("Export to csv");
		menu.add(export);
		
		JMenuItem changeLanguage = new JMenuItem("Change Language");
		menu.add(changeLanguage);
		
		}
}
