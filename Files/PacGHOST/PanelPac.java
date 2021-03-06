import java.awt.Color;//Imports
import java.awt.Desktop;
import java.awt.Dimension;//Imports
import java.awt.Font;//Imports
import java.awt.Graphics;//Imports
import java.awt.event.ActionEvent;//Imports
import java.awt.event.ActionListener;//Imports
import java.awt.event.KeyAdapter;//Imports
import java.awt.event.KeyEvent;//Imports
import java.awt.image.BufferedImage;//Imports
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;//Imports
import javax.swing.JLabel;//Imports
import javax.swing.JPanel;//Imports
import javax.swing.Timer;//Imports

public class PanelPac extends JPanel
{

	private static final long serialVersionUID = 1L;
	public static final int FRAME = 1000; /** creates the static variable for the size of the frame*/
	private static final Color BACKGROUND = new Color(0, 0, 0);
	public static BufferedImage myImage = new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
	public static Graphics myBuffer; /** creates the graphics portion of the panel to draw onto*/
	private Timer t; /** initializes a timer*/
	private Pacman pm; /** initializes pacman*/
	private Ghost gt; /** initializes a ghost*/
	private JLabel label, labelScore, labelT; /** initializes a jlabel*/
	private treat t1; /** initializes the treat*/
	public static Bumper[] b1 = new Bumper[36]; /** initializes the bumper array of 35 bumpers*/
	File file1 = new File("H:\\Computer Science\\Final Project\\Title Screen\\DriverFinal.java"); /** creates the filepath for the Launcher*/


	boolean noMove = false; /** is the game over or not in progress*/
	boolean notagain = false; /** make sure a process is only done once per action*/
	boolean notitle = false; /** is the title screen up*/
	int score = 0; /** holds the score value*/
	boolean resume = false; /** checks to see if the game has restated*/
	int maxScore = 5; /** has the score reached MAXSCORE of four*/

	public PanelPac()
	{

		pm = new Pacman(1026, 55, 26, Color.YELLOW); /** creates the pacman at the specified location*/
		gt = new Ghost(1026, 25, 26); /** creates the ghost at the specified location*/

		bumpermaker(); /** this method below creates all of the bumpers at the specified locations*/
		myBuffer = myImage.getGraphics();
		myBuffer.setColor(BACKGROUND);
		myBuffer.fillRect(0, 0, FRAME, FRAME);

		t1 = new treat(500, 585);
		t = new Timer(0, new Listener());
		addKeyListener(new Key());
		setFocusable(true);
		addKeyListener(new Key1());
		setFocusable(true);

		t.start();
	}

	public static void bumpermaker() /** method that stores all of the bumper data*/
	{
		b1[0] = new Bumper(0, 0, 20, 1000); /** */
		b1[1] = new Bumper(20, 960, 15, 960);
		b1[2] = new Bumper(20, 20, 310, 15);
		b1[3] = new Bumper(20, 660, 310, 15);
		b1[4] = new Bumper(965, 20, 310, 15);
		b1[5] = new Bumper(965, 660, 310, 15);
		b1[6] = new Bumper(375, 484, 83, 250);
		b1[7] = new Bumper(375, 650, 26, 250);
		b1[8] = new Bumper(490, 615, 100, 26);
		b1[9] = new Bumper(385, 495, 63, 230, 1); /** */
		b1[10] = new Bumper(300, 675, 26, 100);
		b1[11] = new Bumper(600, 675, 26, 100);
		b1[12] = new Bumper(870, 330, 26, 110);
		b1[13] = new Bumper(870, 330 + 330, 26, 110);
		b1[14] = new Bumper(20, 330, 26, 110);
		b1[15] = new Bumper(20, 330 + 310, 26, 110);
		b1[16] = new Bumper(477, 20, 100, 26);
		b1[17] = new Bumper(865, 55 + 46 + 55, 40, 50);
		b1[18] = new Bumper();
		b1[19] = new Bumper(385, 780, 26, 230); /** lower t middle*/
		b1[20] = new Bumper(487, 796, 100, 26);
		b1[21] = new Bumper(30, 750, 26, 100); /** side bottom pieces*/
		b1[22] = new Bumper(870, 750, 26, 100);
		b1[23] = new Bumper(230, 800, 100, 26); /** bottom left T*/
		b1[24] = new Bumper(230, 300, 230, 26);
		b1[25] = new Bumper(770, 800, 100, 26);
		b1[26] = new Bumper(770, 300, 230, 26);
		b1[27] = new Bumper(385, 280, 26, 230); /** middle upper part*/
		b1[28] = new Bumper(487, 296, 100, 26);
		b1[29] = new Bumper(185, 120, 26, 200);
		b1[30] = new Bumper(610, 120, 26, 200);
		b1[31] = new Bumper(265, 146, 100, 26);
		b1[32] = new Bumper(690, 146, 100, 26);
		b1[33] = new Bumper(0, 0, 1000, 20); /** side bars*/
		b1[34] = new Bumper(0, 975, 25, 1000);
		b1[35] = new Bumper(980, 0, 1000, 20);
	}

	public void paintComponent(Graphics g)
	{
		g.drawImage(myImage, 0, 0, getWidth(), getHeight(), this); /** this draws the buffered image upon running the code*/

	}

	private class Key extends KeyAdapter
	{
		private boolean isColliding(int x, int y)
		{
			if (myImage.getRGB(x, y) == -16776961) /** -16776961 is the RGB value of color.blue*/
			{

				return true;
			} else
				return false;
		}

		public void keyPressed(KeyEvent e) /** Pacmans keylistener and movement validation*/
		{

			{
				if (!noMove)
				{

					/** DOWN*/
					if (e.getKeyCode() == KeyEvent.VK_DOWN)
					{
						for (int i = 0; i < 10; i++)
						{
							if (!isColliding((int) (pm.getX() + 12), (int) (pm.getY() + 14))
									&& !isColliding((int) (pm.getX()), (int) (pm.getY() + 14))
									&& !isColliding((int) (pm.getX() - 12), (int) (pm.getY() + 14)))
							{
								pm.setY(pm.getY() + 1);
							}

						}
					}

					/** UP*/
					if (e.getKeyCode() == KeyEvent.VK_UP)
					{
						for (int i = 0; i < 10; i++)
						{
							if (!isColliding((int) (pm.getX() + 12), (int) (pm.getY()) - 14)
									&& !isColliding((int) (pm.getX() - 12), (int) (pm.getY()) - 14)
									&& !isColliding((int) (pm.getX()), (int) (pm.getY()) - 14))
								pm.setY(pm.getY() - 1);

						}
					}

					/** RIGHT*/
					if (e.getKeyCode() == KeyEvent.VK_RIGHT)
					{
						for (int i = 0; i < 10; i++)
						{
							if (!isColliding((int) (pm.getX() + 14), (int) (pm.getY() + 12))
									&& !isColliding((int) (pm.getX() + 14), (int) (pm.getY()))
									&& !isColliding((int) (pm.getX() + 14), (int) (pm.getY() - 12)))
								pm.setX(pm.getX() + 1);

						}
					}

					/** LEFT*/
					if (e.getKeyCode() == KeyEvent.VK_LEFT)
					{
						for (int i = 0; i < 10; i++)
						{
							if (!isColliding((int) (pm.getX() - 14), (int) (pm.getY() + 12))
									&& !isColliding((int) (pm.getX() - 14), (int) (pm.getY()))
									&& !isColliding((int) (pm.getX() - 14), (int) (pm.getY() - 12)))
								pm.setX(pm.getX() - 1);

						}
					}
					if (e.getKeyCode() == KeyEvent.VK_ESCAPE) /** brings up the menu*/
						notitle = false;
				}
			}
		}
	}

	private class Key1 extends KeyAdapter
	{
		private boolean isColliding(int x, int y)
		{
			if (myImage.getRGB(x, y) == -16776961) /** */
			{

				return true;
			} else
				return false;
		}

		public void keyPressed(KeyEvent e)  /** Ghost's keylistener and movement validation*/
		{

			if (!noMove)
			{

				/** DOWN*/
				if (e.getKeyCode() == KeyEvent.VK_S)
				{
					for (int i = 0; i < 10; i++)
					{
						if (!isColliding((int) (gt.getX() + 12), (int) (gt.getY() + 14))
								&& !isColliding((int) (gt.getX()), (int) (gt.getY() + 14))
								&& !isColliding((int) (gt.getX() - 12), (int) (gt.getY() + 14)))
						{
							gt.setY(gt.getY() + 1);
						}

					}
				}

				/** UP*/

				if (e.getKeyCode() == KeyEvent.VK_W)
				{
					for (int i = 0; i < 10; i++)
					{
						if (!isColliding((int) (gt.getX() + 12), (int) (gt.getY()) - 14)
								&& !isColliding((int) (gt.getX() - 12), (int) (gt.getY()) - 14)
								&& !isColliding((int) (gt.getX()), (int) (gt.getY()) - 14))
							gt.setY(gt.getY() - 1);

					}
				}

				/** RIGHT*/
				if (e.getKeyCode() == KeyEvent.VK_D)
				{
					for (int i = 0; i < 10; i++)
					{
						if (!isColliding((int) (gt.getX() + 14), (int) (gt.getY() + 12))
								&& !isColliding((int) (gt.getX() + 14), (int) (gt.getY()))
								&& !isColliding((int) (gt.getX() + 14), (int) (gt.getY() - 12)))
							gt.setX(gt.getX() + 1);

					}
				}

				/** LEFT*/
				if (e.getKeyCode() == KeyEvent.VK_A)
				{
					for (int i = 0; i < 10; i++)
					{
						if (!isColliding((int) (gt.getX() - 14), (int) (gt.getY() + 12))
								&& !isColliding((int) (gt.getX() - 14), (int) (gt.getY()))
								&& !isColliding((int) (gt.getX() - 14), (int) (gt.getY() - 12)))
							gt.setX(gt.getX() - 1);

					}
				}

				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) /** brings up the menu*/
				{
					notitle = false;
					resume = true;
				}

			}
		}
	}

	private class Listener implements ActionListener /** this listener is called once to draw the panel at the beginning*/
	{
		public void actionPerformed(ActionEvent e)
		{

			myBuffer.setColor(BACKGROUND); //sets the background color to black
			myBuffer.fillRect(0, 0, FRAME, FRAME);
			pm.setColor(Color.YELLOW);
			pm.draw(myBuffer);
			gt.setColor(Color.ORANGE);
			collide(pm, gt);
			getTheTreat(pm, t1); /** allows pacman to touch the treat*/
			getTheTreatg(gt, t1); /** allows the ghost to be able to touch the treat*/
			titleScreen(); /** calls the title screen to be pulled up*/
			gt.draw(myBuffer);
			for (int x = 0; x < 36; x++) /** draws every bumper in the array*/
			{
				b1[x].draw(myBuffer);
			}
			t1.draw(myBuffer);
			repaint();

		}
	}

	public void titleScreen() /** */
	{
		if (notitle == false)
		{
			noMove = true;

			label = new JLabel("PACGHOST- Competitive PACMAN spinoff");
			label.setFont(new Font("Courier", Font.BOLD, 40));
			label.setForeground(Color.white);
			label.setBackground(Color.gray);
			label.setOpaque(true);
			add(label);

			JButton button3 = new JButton("QUIT");
			button3.setFont(new Font("Arial", Font.PLAIN, 40));
			button3.setPreferredSize(new Dimension(400, 100));
			button3.setBounds(500, 500, 100, 100);
			button3.addActionListener(new Listener3());
			button3.setBorderPainted(false);
			button3.setFocusPainted(false);
			button3.setBackground(Color.red);
			button3.setSize(new Dimension(100, 100));
			add(button3);

			JButton button101 = new JButton("LAUNCHER");
			button101.setFont(new Font("Arial", Font.PLAIN, 40));
			button101.setPreferredSize(new Dimension(400, 100));
			button101.setBounds(500, 500, 100, 100);
			button101.addActionListener(new ListenerTitle());
			button101.setBorderPainted(false);
			button101.setFocusPainted(false);
			button101.setBackground(Color.CYAN);
			button101.setSize(new Dimension(100, 100));
			add(button101);

			JButton button2 = new JButton("PLAY PACGHOST");
			button2.setFont(new Font("Arial", Font.PLAIN, 40));
			button2.setPreferredSize(new Dimension(400, 100));
			button2.setBounds(500, 500, 100, 100);
			button2.addActionListener(new Listener2());
			button2.setBorderPainted(false);
			button2.setFocusPainted(false);
			button2.setBackground(Color.green);
			button2.setSize(new Dimension(100, 100));
			add(button2);

			JButton button4 = new JButton("INSTRUCTIONS");
			button4.setFont(new Font("Arial", Font.PLAIN, 40));
			button4.setPreferredSize(new Dimension(400, 100));
			button4.setBounds(500, 500, 100, 100);
			button4.addActionListener(new Listener4());
			button4.setBorderPainted(false);
			button4.setFocusPainted(false);
			button4.setBackground(Color.gray);
			button4.setSize(new Dimension(100, 100));
			add(button4);

			if (resume == true)
			{
				JButton buttonR = new JButton("RESUME");
				buttonR.setFont(new Font("Arial", Font.PLAIN, 40));
				buttonR.setPreferredSize(new Dimension(400, 100));
				buttonR.setBounds(500, 500, 100, 100);
				buttonR.addActionListener(new ListenerR());
				buttonR.setBorderPainted(false);
				buttonR.setFocusPainted(false);
				buttonR.setBackground(Color.orange);
				buttonR.setSize(new Dimension(100, 100));
				add(buttonR);
			}
			revalidate();

			notitle = true;
		}
	}

	private class Listener3 implements ActionListener /**this listener is an exit listener for the exit button*/
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}

	private class ListenerR implements ActionListener /**this listener clears the screen and prevents movement*/
	{
		public void actionPerformed(ActionEvent e)
		{
			removeAll();
			noMove = false;
		}
	}

	private class Listener2 implements ActionListener /**This listener sets the game again to defaults by moving pm and gt*/
	{
		public void actionPerformed(ActionEvent e)
		{
			pm.setX(500);
			pm.setY(940);
			gt.setX(490);
			gt.setY(180);
			removeAll();
			notagain = false;
			score = 0;
			noMove = false;

		}
	}

	private class Listener4 implements ActionListener  /** this listener is for the instructions*/
	{
		public void actionPerformed(ActionEvent e)
		{
			removeAll();

			label = new JLabel("INSTRUCTIONS");
			label.setFont(new Font("Courier", Font.BOLD, 40));  /**Instructions label */
			label.setForeground(Color.white);
			label.setBackground(Color.gray);
			label.setOpaque(true);
			add(label);

			labelT = new JLabel(
					"<html> The red player uses WASD keys to move.<br> The Yellow Player moves with the arrow keys. <br> Red's objective is to touch yellow player.<br> Meanwhile, yellow's goal is to amass the highest score <br>possible by collecting treats (White Balls).<br> To begin playing, press the back button, then select PLAY. ENJOY :></html> ");
			labelT.setFont(new Font("Courier", Font.BOLD, 20));  /**Label to display instructions using html tags*/
			labelT.setForeground(Color.white);
			labelT.setBackground(Color.BLACK);
			labelT.setOpaque(true);
			labelT.setBounds(100, 100, 400, 600);
			add(labelT);

			JButton buttong = new JButton("BACK");
			buttong.setFont(new Font("Arial", Font.PLAIN, 40));
			buttong.setPreferredSize(new Dimension(400, 100));
			buttong.setBounds(500, 500, 100, 100);
			buttong.addActionListener(new Listenerg());
			buttong.setBorderPainted(false);
			buttong.setFocusPainted(false);
			buttong.setBackground(Color.GREEN);
			buttong.setSize(new Dimension(100, 100));
			add(buttong);
		}
	}

	private class Listenerg implements ActionListener /**Listener to clear the screen and add the title screen*/
	{
	
		public void actionPerformed(ActionEvent e)
		{
			removeAll();
			noMove = false;
			titleScreen();
			notitle = false;

		}
	}

	private class ListenerTitle implements ActionListener /**Listener to go back to launcher*/
	{

		public void actionPerformed(ActionEvent e)
		{
			try
			{
				Desktop.getDesktop().open(file1);
			} catch (IOException e1)
			{

				e1.printStackTrace();
			}
			System.exit(0);
		}
	}

	private double distance(double x1, double y1, double x2, double y2) /**Distance formula method*/
	{
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}

	public void collide(Pacman p, Ghost g)
	{
		double dist = distance(p.getX(), p.getY(), g.getX(), g.getY()); /**This calls the distance formula to determine if pm and gt are colliding and sets their difference to a double*/

		if (dist < (p.getLength() / 2) + (g.getLength() / 2) && notagain == false) /**if statement to determine if they are colliding, accounting for the length of the gt and pm*/
		{
			revalidate();
			label = new JLabel("Game Over. Ghost Wins");
			label.setFont(new Font("Courier", Font.BOLD, 80));  
			label.setForeground(Color.white);
			add(label);

			noMove = true;

			JButton button = new JButton("QUIT");
			button.setFont(new Font("Arial", Font.PLAIN, 40));
			button.setPreferredSize(new Dimension(400, 100));
			button.setBounds(500, 500, 100, 100);
			button.addActionListener(new Listener3());
			button.setBorderPainted(false);
			button.setFocusPainted(false);
			button.setBackground(Color.red);
			button.setSize(new Dimension(100, 100));
			add(button);

			JButton button10 = new JButton("LAUNCHER");
			button10.setFont(new Font("Arial", Font.PLAIN, 40));
			button10.setPreferredSize(new Dimension(400, 100));
			button10.setBounds(500, 500, 100, 100);
			button10.addActionListener(new ListenerTitle());
			button10.setBorderPainted(false);
			button10.setFocusPainted(false);
			button10.setBackground(Color.CYAN);
			button10.setSize(new Dimension(100, 100));
			add(button10);

			JButton button1 = new JButton("PLAY AGAIN");
			button1.setFont(new Font("Arial", Font.PLAIN, 40));
			button1.setPreferredSize(new Dimension(400, 100));
			button1.setBounds(500, 500, 100, 100);
			button1.addActionListener(new Listener13());
			button1.setBorderPainted(false);
			button1.setFocusPainted(false);
			button1.setBackground(Color.green);
			button1.setSize(new Dimension(100, 100));
			add(button1);

			revalidate();

			notagain = true;

		}

	}

	private class Listener13 implements ActionListener  /**This listener resets the location of pm and gt and jump the treat*/
	{
		public void actionPerformed(ActionEvent e)
		{
			pm.setX(500);
			pm.setY(940);
			gt.setX(520);
			gt.setY(55);
			removeAll();
			notagain = false;
			noMove = false;
			t1.jump(getX(), getY());
		}
	}

	public void getTheTreatg(Ghost g, treat t)
	{
		double dist1 = distance(g.getX(), g.getY(), t.getX(), t.getY());
		if (dist1 < (g.getLength() / 2) + 6)
		{
			t1.jump(getX(), getY());
		}

	}

	public void getTheTreat(Pacman p, treat t)
	{
		double dist = distance(p.getX(), p.getY(), t.getX(), t.getY());
		if (dist < (p.getLength() / 2) + 6)
		{
			score++;
			t1.jump(getX(), getY());

			if (score < maxScore)
			{
				removeAll();
				labelScore = new JLabel("Score: " + score);
				labelScore.setFont(new Font("Courier", Font.BOLD, 40));
				labelScore.setForeground(Color.GREEN);
				labelScore.setBounds(400, 490, 300, 60);
				add(labelScore);

			} else
			{
				label = new JLabel("Game Over. Pac Wins");
				label.setFont(new Font("Courier", Font.BOLD, 80));
				label.setForeground(Color.white);
				add(label);

				score = 0; /** resets the score */
				noMove = true;

				JButton button = new JButton("QUIT");
				button.setFont(new Font("Arial", Font.PLAIN, 40));
				button.setPreferredSize(new Dimension(400, 100));
				button.setBounds(500, 500, 100, 100);
				button.addActionListener(new Listener3());
				button.setBorderPainted(false);
				button.setFocusPainted(false);
				button.setBackground(Color.red);
				button.setSize(new Dimension(100, 100));
				add(button);
				JButton button9 = new JButton("LAUNCHER");
				button9.setFont(new Font("Arial", Font.PLAIN, 40));
				button9.setPreferredSize(new Dimension(400, 100));
				button9.setBounds(500, 500, 100, 100);
				button9.addActionListener(new ListenerTitle());
				button9.setBorderPainted(false);
				button9.setFocusPainted(false);
				button9.setBackground(Color.CYAN);
				button9.setSize(new Dimension(100, 100));
				add(button9);

				JButton button1 = new JButton("PLAY AGAIN");
				button1.setFont(new Font("Arial", Font.PLAIN, 40));
				button1.setPreferredSize(new Dimension(400, 100));
				button1.setBounds(500, 500, 100, 100);
				button1.addActionListener(new Listener13());
				button1.setBorderPainted(false);
				button1.setFocusPainted(false);
				button1.setBackground(Color.green);
				button1.setSize(new Dimension(100, 100));
				add(button1);

				revalidate();

				notagain = true;
			}

		}

	}
}