package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import Model.*;
import java.util.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;


public class MainPanel extends JPanel
{
	/** We use a JPanel because it is easy to draw 2D objects 
	* on a JPanel using paintComponent() method.
	* This is thw maijn game panel
	* It has a Timer object to render the UI according to backend data after a fixed amt of time **/
	private static final int Default_Height = 800;
	private static final int Default_Width = 800;
	private static int PaddleX;
	private static int PaddleY;
	private static int PClickX;
	private static int PClickY;
	private static String Name1 = "Player1";
 	private static String Name2 = "Player2";
 	private static String Name3 = "Player3";
 	private static String Name4 = "Player4";


	public MainPanel()
	{
		/** Constructor **/
		super();
		this.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(300, 300, 300, 300, Color.BLUE), new EtchedBorder()));
		// setBorder(BorderFactory.createLineBorder(Color.black));
		this.setPreferredSize(new Dimension(Default_Width, Default_Height));
		this.setVisible(true);
		this.setBackground(Color.LIGHT_GRAY);
		this.addMouseMotionListener(new CustomMouseMotionListener());
		this.addMouseListener(new CustomMouseListener());
	}

	public int getNewX()
	{
		return PaddleX;
	}

	public int getNewY()
	{
		return PaddleY;
	}

	public int getClickX()
	{
		return PClickX;
	}

	public int getClickY()
	{
		return PClickY;
	}


// upper left x, y, width, height
	public void paintComponent(Graphics g)
	{
		/** Renders the basic view of Game Panel **/
		super.paintComponent(g);
		Color c= new Color(200, 50, 50, 1);
		Graphics2D g2 = (Graphics2D) g;
		//g.setColor(Color.argb(0.8, 200, 50, 50));
		//g.fillRect(0,0,590,590);
		g.setColor(Color.ORANGE);
		g.fillOval(240,240,120,120);
		g.setColor(Color.RED);
		g.fillOval(260,260,80,80);
		float thickness = 20;
		float thickness2 = 10;
		Stroke oldStroke = g2.getStroke();
		g2.setStroke(new BasicStroke((float)(thickness)));
		g2.setColor(Color.BLACK);
		g2.drawLine(0, 10, 580, 10);
		g2.drawLine(10, 0, 10, 580);
		g2.drawLine(0, 590, 580, 590);
		g2.drawLine(590, 0, 590, 590);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
 		g2.drawString(Name1, 750, 100);
 		g2.drawString(Name2, 750, 250);
 		g2.drawString(Name3, 750, 400);
 		g2.drawString(Name4, 750, 550);
		g2.setStroke(new BasicStroke((float)(thickness2)));
		g2.setColor(Color.BLUE);
		g2.drawLine(265,300,335,300);
		g2.drawLine(300,265,300,335);
		
		g2.setStroke(oldStroke);
		
		g.setColor(Color.ORANGE);
		Polygon p = new Polygon(new int[] {0,60,0}, new int[] {0,0,60},3);
		g.fillPolygon(p);

		Polygon p1 = new Polygon(new int[] {600,540,600}, new int []{0,0,60},3);
		g.fillPolygon(p1);

		Polygon p2 = new Polygon(new int[] {0,60,0}, new int [] {600,600,540},3);
		g.fillPolygon(p2);

		Polygon p3 = new Polygon(new int[] {600,540,600}, new int [] {600,600,540},3);
		g.fillPolygon(p3);

		// Color newColor= new Color(200, 105, 0, 1);
		// g.setColor(newColor);
		// Polygon p = new Polygon(new int[] {10,50,10}, new int[] {10,10,50},3);
		// g.fillPolygon(p);

		// Polygon p1 = new Polygon(new int[] {590,550,590}, new int []{10,10,50},3);
		// g.fillPolygon(p1);

		// Polygon p2 = new Polygon(new int[] {10,50,10}, new int [] {590,590,550},3);
		// g.fillPolygon(p2);

		// Polygon p3 = new Polygon(new int[] {590,550,590}, new int [] {590,590,550},3);
		// g.fillPolygon(p3);
	}

/** Takes input the updated lists of Balls, Paddles, Random Objects. **/
	public void updateBoard(ArrayList<Ball> balls, ArrayList<Paddle> paddles, ArrayList<RandomObj> objs)
	{
		Graphics curr_g = this.getGraphics();
		paintComponent(curr_g);
		int no_balls = balls.size();
		int no_paddles = paddles.size();
		for (int i = 0; i < no_balls; i++)
		{
			Ball ith = balls.get(i);
			double radius = ith.getRadius();
			double center_x = ith.getCenterX();
			double center_y = ith.getCenterY();
			curr_g.setColor(Color.YELLOW);
			curr_g.fillOval((int)(center_x-radius), (int)(center_y + radius), (int)(2.0*radius), (int)(2.0*radius));
		}
		for (int i = 0; i < no_paddles; i++)
		{
			// ith at bottom. ROTATE karo
			Paddle ith = paddles.get(i);
			double P_length = ith.getPaddleLength();
			double paddle_x = ith.getPaddleX();
			double paddle_y = ith.getPaddleY();
			int loves = 5 - ith.getBallMissed();
			curr_g.setColor(Color.BLUE);
			if(i == 0)
				{curr_g.fillRect((int)(paddle_x - (P_length/2.0)), (int)( paddle_y), (int) (P_length), 20);
				Polygon p1 = new Polygon(new int[] {(int)(paddle_x-20 - (P_length/2.0)),(int)(paddle_x + 0 - (P_length/2.0)),(int)(paddle_x + 0 - (P_length/2.0))}, new int[] {20,0,20},3);
				curr_g.fillPolygon(p1);
				Polygon p2 = new Polygon(new int[] {(int)(paddle_x+20 + (P_length/2.0)),(int)(paddle_x + 0 + (P_length/2.0)),(int)(paddle_x + 0 + (P_length/2.0))}, new int[] {20,0,20},3);
				curr_g.fillPolygon(p2);
				curr_g.setColor(Color.RED);
				for (int j = 1; j<= loves; j++)
				curr_g.fillOval(50*j + 640,50,20,20);
			if(loves <= 0)
 				{
 					curr_g.setFont(new Font("Verdana", Font.BOLD, 30)); 
 					curr_g.drawString("GAME OVER", 700, 50);
 				}
			}
			else if(i ==1)
				{curr_g.fillRect((int)(paddle_x), (int)( paddle_y -(P_length/2.0)), 20, (int) (P_length));
				Polygon p1 = new Polygon(new int[] {20,0,20}, new int[] {(int)(paddle_y-20 - (P_length/2.0)),(int)(paddle_y + 0 - (P_length/2.0)),(int)(paddle_y + 0 - (P_length/2.0))},3);
				curr_g.fillPolygon(p1);
				Polygon p2 = new Polygon(new int[] {20,0,20}, new int[] {(int)(paddle_y+20 + (P_length/2.0)),(int)(paddle_y + 0 + (P_length/2.0)),(int)(paddle_y + 0 + (P_length/2.0))},3);
				curr_g.fillPolygon(p2);
				curr_g.setColor(Color.RED);
				for (int j = 1; j<=loves; j++)
				curr_g.fillOval(50*j + 640,200,20,20);
			if(loves <= 0)
 				{
 					curr_g.setFont(new Font("Verdana", Font.BOLD, 30)); 
 					curr_g.drawString("GAME OVER", 700, 200);
 				}
				}
			else if(i ==2)
				{curr_g.fillRect((int)(paddle_x - (P_length/2.0)), (int)( paddle_y-20), (int) (P_length), 20);
				Polygon p1 = new Polygon(new int[] {(int)(paddle_x-20 - (P_length/2.0)),(int)(paddle_x + 0 - (P_length/2.0)),(int)(paddle_x + 0 - (P_length/2.0))}, new int[] {580,600,580},3);
				curr_g.fillPolygon(p1);
				Polygon p2 = new Polygon(new int[] {(int)(paddle_x+20 + (P_length/2.0)),(int)(paddle_x + 0 + (P_length/2.0)),(int)(paddle_x + 0 + (P_length/2.0))}, new int[] {580,600,580},3);
				curr_g.fillPolygon(p2);
				curr_g.setColor(Color.RED);
				for (int j = 1; j<= loves; j++)
				curr_g.fillOval(50*j + 640,350,20,20);
			if(loves <= 0)
 				{
 					curr_g.setFont(new Font("Verdana", Font.BOLD, 30)); 
 					curr_g.drawString("GAME OVER", 700, 350);
 				}
				}
			else if(i ==3)
				{curr_g.fillRect((int)(paddle_x - 20), (int)( paddle_y -(P_length/2.0)), 20, (int) (P_length));
				Polygon p1 = new Polygon(new int[] {580,600,580}, new int[] {(int)(paddle_y-20 - (P_length/2.0)),(int)(paddle_y + 0 - (P_length/2.0)),(int)(paddle_y + 0 - (P_length/2.0))},3);
				curr_g.fillPolygon(p1);
				Polygon p2 = new Polygon(new int[] {580,600,580}, new int[] {(int)(paddle_y+20 + (P_length/2.0)),(int)(paddle_y + 0 + (P_length/2.0)),(int)(paddle_y + 0 + (P_length/2.0))},3);
				curr_g.fillPolygon(p2);
				curr_g.setColor(Color.RED);
				for (int j = 1; j<=loves; j++)
				curr_g.fillOval(50*j + 640,500,20,20);
			if(loves <= 0)
 				{
 					curr_g.setFont(new Font("Verdana", Font.BOLD, 30)); 
 					curr_g.drawString("GAME OVER", 700, 500);
 				}
				}
		}

		for (int j = 0; j < objs.size(); j ++)
		{
			RandomObj ith = objs.get(j);
			double center_x = ith.getCenterX();
			double center_y = ith.getCenterY();
			boolean effect = ith.getEffect();
			if (effect)
			{
				// Increase length. Good image
			}
			else
			{
				// Bad image
			}
		}
			
	}

	public static class CustomMouseMotionListener implements MouseMotionListener {
		public void mouseDragged(MouseEvent e)
		{
//System.out.println("Mouse Dragged: ("+e.getX()+", "+e.getY() +")");
        PaddleX = e.getX();
        PaddleY = e.getY();
      }

      public void mouseMoved(MouseEvent e) {
         // statusLabel.setText("Mouse Moved: ("+e.getX()+", "+e.getY() +")");
      }    
   }

	class CustomMouseListener implements MouseListener{
      public void mouseClicked(MouseEvent e) {
  //       System.out.println("Mouse Clicked: ("+e.getX()+", "+e.getY() +")");
         PClickX = e.getX();
         PClickY = e.getY();
         PaddleX = e.getX();
         PaddleY = e.getY();
      }

      public void mousePressed(MouseEvent e) {
      }

      public void mouseReleased(MouseEvent e) {
      }

      public void mouseEntered(MouseEvent e) {
      }

      public void mouseExited(MouseEvent e) {
      }
   }

}