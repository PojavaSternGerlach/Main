package SternGerlachGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class MainFrame extends JFrame {

	MainFrame(){
		
		// set frame
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Stern-Gerlach Experiment");
		setSize(1400,800);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.GRAY);
		//setLayout(new GridBagLayout());
		setLayout(new BorderLayout());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		//menu
		MenuBar menuBar = new MenuBar();
		setJMenuBar(menuBar);
		
		//animacja i wykres
		//JScrollPane scroll = new JScrollPane();
		//JPanel animation = new AnimationPanel();
		//JPanel graph = new GraphPanel();
		//scroll.add(animation);
		//scroll.add(graph);
		//add(scroll);
		
		// pole animacji
		AnimationPanel animation = new AnimationPanel();
		add(animation, BorderLayout.NORTH);
		
		
		// interfejs
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		panel.setPreferredSize(new Dimension(1400,250));
		panel.setLayout(new BorderLayout());
		
		// sfera blocha
		GraphPanel graph = new GraphPanel();
		panel.add(graph, BorderLayout.WEST);
		
		// przyciski
		JPanel buttons = new JPanel();
		panel.add(buttons, BorderLayout.CENTER);
		buttons.setPreferredSize(new Dimension(1000,250));
		buttons.setBackground(Color.RED);
		buttons.setLayout(new FlowLayout());
		
		//lista rozwijana - ilosc magnesow
		JComboBox<String> magnets = new JComboBox<String>();
		buttons.add(magnets);
		
		// prawdopodobieñstwa
		JRadioButton probs = new JRadioButton("Show probabilities");
		buttons.add(probs);
		
		// stan magnesow, k¹t pod jakim w danym momencie s¹ ustawione
		JPanel state = new JPanel();
		panel.add(state, BorderLayout.EAST);
		state.setPreferredSize(new Dimension(250,250));
		state.setLayout(new GridLayout(3,1));
		
		JLabel magnet1 = new JLabel("Magnet 1: +z");
		state.add(magnet1);
		
		JLabel magnet2 = new JLabel("Magnet 2: 45 degrees");
		state.add(magnet2);

		JLabel magnet3 = new JLabel("Magnet 3: brak");
		state.add(magnet3);
		
	}
	
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setVisible(true);
	}
}

class AnimationPanel extends JPanel {
	AnimationPanel(){
		setBackground(Color.BLUE);
		setPreferredSize(new Dimension(1400,500));
	}
}

class GraphPanel extends JPanel{
	GraphPanel(){
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(250,250));
	}
	
}
