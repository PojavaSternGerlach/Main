package SternGerlachGUI;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class MainFrame extends JFrame {

	MainFrame(){
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Stern-Gerlach Experiment");
		setSize(800,600);
		setLayout(new GridBagLayout());
		//menu
		MenuBar menuBar = new MenuBar();
		setJMenuBar(menuBar);
		//animacja i wykres
		JScrollPane scroll = new JScrollPane();
		JPanel animation = new AnimationPanel();
		JPanel graph = new GraphPanel();
		scroll.add(animation);
		scroll.add(graph);
		add(scroll);
		//lista rozwijana - ilosc magnesow
		JComboBox<String> magnets = new JComboBox<String>();
		add(magnets);
		//
	}
	
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setVisible(true);
	}
}

class AnimationPanel extends JPanel {
	AnimationPanel(){
		setBackground(Color.WHITE);
	}
}

class GraphPanel extends JPanel{
	GraphPanel(){
		setBackground(Color.WHITE);
	}
	
}
