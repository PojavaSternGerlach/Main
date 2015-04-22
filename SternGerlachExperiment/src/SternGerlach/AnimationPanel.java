package SternGerlach;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;



class AnimationPanel extends JPanel {
	
	private static final long serialVersionUID = 5873595105288429707L;
	
	int x = 0;
	int y = 0;
	
	List<FilledRectangle> elements = new ArrayList<FilledRectangle>();
	
	AnimationPanel(){
		setPreferredSize(new Dimension(1400,500));
		
		FilledRectangle source = new FilledRectangle();
		source.setHeight(300);
		source.setWidth(10);
		source.setX(10);
		source.setY(10);
		elements.add(source);
		
		FilledRectangle firstMagnet = new FilledRectangle();
		firstMagnet.setHeight(30);
		firstMagnet.setWidth(90);
		firstMagnet.setX(150);
		firstMagnet.setY(120);
		elements.add(firstMagnet);
		
		FilledRectangle secondMagnet = new FilledRectangle();
		secondMagnet.setHeight(30);
		secondMagnet.setWidth(90);
		secondMagnet.setX(250);
		secondMagnet.setY(120);
		elements.add(secondMagnet);
		
		FilledRectangle thirdMagnet = new FilledRectangle();
		thirdMagnet.setHeight(30);
		thirdMagnet.setWidth(90);
		thirdMagnet.setX(350);
		thirdMagnet.setY(120);
		elements.add(thirdMagnet);
		
		FilledRectangle screen = new FilledRectangle();
		screen.setHeight(300);
		screen.setWidth(10);
		screen.setX(550);
		screen.setY(10);
		elements.add(screen);
		
		repaint();
		
		addMouseListener(new MouseAdapter() {
            @Override
			public void mouseClicked(MouseEvent e) {
                x = e.getX();
                y = e.getY();  
                
            }
            });
		
        //nie chce sie obracac
        if(y>150 && y<180){
        	if(x>180 && x<220){
        		elements.remove(1);
        		int newDeg = firstMagnet.deg + 10;
        		firstMagnet.setDeg(newDeg);
        		repaint();
        	}
        	
        }
    
	}
	
	@Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(FilledRectangle sh : elements)
        	sh.paint(g);
       
    	repaint();
    }
}


class FilledRectangle{

    private int xPos = 50;
    
    public int deg = 0;
    
    public void setDeg(int deg){
    	this.deg = deg;
    }
    
    public int getX() {
		return xPos;
	}

	public void setX(int xPos) {
		this.xPos = xPos;
	}

	private int yPos = 50;
    private int width = 20;
    private int height = 20;
  
    public void setY(int yPos){
        this.yPos = yPos;
    }

    public int getY(){
        return yPos;
    }

    public int getWidth(){
        return width;
    } 

    public int getHeight(){
        return height;
    }


	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void paint(Graphics g){

		Graphics2D g2 = (Graphics2D) g;
		g2.rotate(Math.toRadians(deg));
		g.setColor(Color.BLACK);
        g.fillRect(xPos,yPos,getWidth(),getHeight());
    }

}
