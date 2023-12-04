import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Interface extends JPanel
{
	Player pl = new Player (200, 200, 30);
	Wall[] walls = new Wall[4];
	public Interface()
	{
		/*
		for(int i = 0; i < walls.length; i++)
		{
			walls[i] = new Wall()
		}
		*/
		addKeyListener(new KeyListener() 
		{
			 @Override
			 public void keyTyped(KeyEvent e) {}
			 @Override
			 public void keyReleased(KeyEvent e) 
			 {
				 pl.keyReleased(e);
			 }
			 @Override
			 public void keyPressed(KeyEvent e) 
			 {
				pl.keyPressed(e);
			 }
			 
		}); 
		setFocusable(true);
		/*
		try 
		{
			imgAir = ImageIO.read(new File("airplane.png"));
		}catch (IOException e) {e.printStackTrace();}
		try
		{
			imgTruck = ImageIO.read(new File("truck.png"));
		}catch (IOException e) {e.printStackTrace();}
		
		try
		{
			imgQuestion = ImageIO.read(new File("question.png"));
		}catch (IOException e) {e.printStackTrace();}
		*/
		
	}
	@Override
	public void paint(Graphics g) 
	{
		super.paint(g); 
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				 RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, 1020, 640);
		pl.paint(g2d);
	}
	public static void main(String[] args) throws InterruptedException 
	{
		JFrame frame = new JFrame("Moving Square");
		Interface p = new Interface();
		frame.add(p);
		frame.setSize(1020, 640);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		while (true)
		{
			 p.move(); 
			 p.repaint(); 
			 Thread.sleep(10); 
		}
	}
	private void move()
	{
		pl.move();
	}
}
