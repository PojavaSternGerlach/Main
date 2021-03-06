package SternGerlach;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import application.MenuBar;


public class MainFrame extends JFrame {


	private static final long serialVersionUID = 7096614736350989999L;

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
		mc.gridy = 1;
		add(buttons, mc);
		//buttons.setPreferredSize(new Dimension(1000,250));
		buttons.setBackground(Color.WHITE);
		buttons.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10,50,0,50);
		
		//lista rozwijana - ilosc magnesow
		String[] magnetNumbers = { "1 magnet","2 magnets","3 magnets" };
		JComboBox<String> magnets = new JComboBox<String>(magnetNumbers);
		c.gridy = 0;		
		buttons.add(magnets, c);
		
		// prawdopodobie�stwa
		JRadioButton probs = new JRadioButton("Show probabilities");
		probs.setBackground(Color.WHITE);
		c.gridy = 1;
		
		
		buttons.add(probs, c);
		
		//przycisk start
		JButton startButton = new JButton("START");
		c.gridy = 2;
		c.insets = new Insets(150, 50, 0, 50);
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int theta = animation.theta;
				String output = Double.toString((0.5*Math.pow((Math.cos((theta/2)*Math.PI/180)), 2))*(Math.pow((Math.cos((theta/2)*Math.PI/180)),2)));
        		System.out.println("Probablity:" + output);
				
			}
		});

		buttons.add(startButton,c);
		
		// stan magnesow, k�t pod jakim w danym momencie s� ustawione
		JPanel state = new JPanel();
		state.setBackground(Color.WHITE);
		mc.gridy = 0;
		add(state, mc);
		state.setPreferredSize(new Dimension(250,250));
		state.setLayout(new GridLayout(5,1));
		
		JLabel magnetState = new JLabel("           Magnets state:");
		state.add(magnetState);
		
		JLabel blank = new JLabel("");
		state.add(blank);
		
		JLabel magnet1 = new JLabel("           Magnet 1: z+");
		magnet1.setBackground(Color.WHITE);
		state.add(magnet1);
		
		JLabel magnet2 = new JLabel("           Magnet 2: 0 degrees");
		magnet2.setBackground(Color.WHITE);
		state.add(magnet2);

		animation.addMouseListener(new MouseAdapter() {
            @Override
			public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();  
//                if((y>firstMagnet.getY()-20 && y<(firstMagnet.getY()+firstMagnet.getHeight()+20))
//                		&& (x>firstMagnet.getX() && x<(firstMagnet.getX()+firstMagnet.getWidth()))){
//                		theta += 10;
//                		firstMagnet.rotate(10);
//                		repaint();
//                }
                if((y>animation.secondMagnet.getY()-20 && y<(animation.secondMagnet.getY()+animation.secondMagnet.getHeight()+20))
                		&& (x>animation.secondMagnet.getX() && x<(animation.secondMagnet.getX()+animation.secondMagnet.getWidth()))){
            			String magnet2State = Integer.toString(animation.theta);
            			magnet2.setText("           Magnet 2: " + magnet2State+" degrees");
                }
//                else if((y>thirdMagnet.getY()-20 && y<(thirdMagnet.getY()+thirdMagnet.getHeight()+20))
//                		&& (x>thirdMagnet.getX() && x<(thirdMagnet.getX()+thirdMagnet.getWidth()))){
//                		theta += 10;
//                		thirdMagnet.rotate(10);
//                		repaint();
//                }
            }
            });
		
		
		JLabel magnet3 = new JLabel("           Magnet 3: z+");
		magnet3.setBackground(Color.WHITE);

		state.add(magnet3);
		
	}
	
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setVisible(true);
	}
}



class GraphPanel extends JPanel{


	private static final long serialVersionUID = 2479286754458996908L;

	GraphPanel(){
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(250,250));
	}
	
}
