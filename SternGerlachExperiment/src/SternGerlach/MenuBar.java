package SternGerlach;

import java.awt.HeadlessException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = -6553049437076909711L;

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
