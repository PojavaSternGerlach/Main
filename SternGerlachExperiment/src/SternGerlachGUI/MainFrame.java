package SternGerlachGUI;

import java.awt.*;

import javax.swing.JButton;
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
		setSize(800,600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		setLayout(new GridBagLayout());
		GridBagConstraints mc = new GridBagConstraints();
		mc.fill = GridBagConstraints.HORIZONTAL;
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		//menu
		MenuBar menuBar = new MenuBar();
		setJMenuBar(menuBar);
		mc.ipadx=600; 
		mc.ipady=255; 
		// pole animacji
		AnimationPanel animation = new AnimationPanel();
		animation.setBackground(Color.GRAY);
		
		add(animation, mc);
		
		/*
		// interfejs
		JPanel panel = new JPanel();
		add(panel);
		panel.setPreferredSize(new Dimension(1400,250));
		panel.setLayout(new BorderLayout());
		*/
		
		// sfera blocha
		GraphPanel graph = new GraphPanel();
		graph.setBackground(Color.LIGHT_GRAY);
		
		mc.gridy = 1;
		add(graph, mc);
		
		mc.ipadx=0; 
		mc.ipady=0; 
		// przyciski
		JPanel buttons = new JPanel();
		mc.gridx = 1;
		mc.gridy = 0;
		add(buttons, mc);
		//buttons.setPreferredSize(new Dimension(1000,250));
		buttons.setBackground(Color.WHITE);
		buttons.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10,50,0,50);
		
		//lista rozwijana - ilosc magnesow
		JComboBox<String> magnets = new JComboBox<String>();
		c.gridy = 0;
		buttons.add(magnets, c);
		
		// prawdopodobieñstwa
		JRadioButton probs = new JRadioButton("Show probabilities");
		probs.setBackground(Color.WHITE);
		c.gridy = 1;
		
		
		buttons.add(probs, c);
		
		//przycisk start
		JButton startButton = new JButton("START");
		c.gridy = 2;
		c.insets = new Insets(150, 50, 0, 50);

		buttons.add(startButton,c);
		
		// stan magnesow, k¹t pod jakim w danym momencie s¹ ustawione
		JPanel state = new JPanel();
		state.setBackground(Color.WHITE);
		mc.gridy = 1;
		add(state, mc);
		state.setPreferredSize(new Dimension(250,250));
		state.setLayout(new GridLayout(3,1));
		
		JLabel magnet1 = new JLabel("Magnet 1: +z");
		magnet1.setBackground(Color.WHITE);
		state.add(magnet1);
		
		JLabel magnet2 = new JLabel("Magnet 2: 45 degrees");
		magnet2.setBackground(Color.WHITE);

		state.add(magnet2);

		JLabel magnet3 = new JLabel("Magnet 3: brak");
		magnet3.setBackground(Color.WHITE);

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
