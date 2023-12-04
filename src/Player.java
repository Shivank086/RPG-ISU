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

public class Player 
{
	int x, y, xa, ya, d;
	boolean jumped;
	public Player(int x, int y, int d)
	{
		this.x = x;
		this.xa = 0;
		this.y = y;
		this.ya = 0;
		this.d = d;
		jumped = false;
	}
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) xa = 4;
		if (e.getKeyCode() == KeyEvent.VK_LEFT) xa = -4;
		if (e.getKeyCode() == KeyEvent.VK_UP) if(!jumped) ya += -20;
	}
	
	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) xa = 0;
		if (e.getKeyCode() == KeyEvent.VK_LEFT) xa = 0;
	}
	
	public int getX() {return x;}
	public int getY() {return y;}
	public int getD() {return d;}
	public int getXa() {return xa;}
	public int getYa() {return ya;}
	public boolean getJumped() {return jumped;}
	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}
	public void setD(int d) {this.d = d;}
	public void setXa(int xa) {this.xa = xa;}
	public void setYa(int ya) {this.ya = ya;}
	public void setJumped(boolean jumped) {this.jumped = jumped;}
	
	public void paint(Graphics g)
	{
		Graphics2D p = (Graphics2D) g;
		p.setColor(Color.GREEN);
		p.fillRect(x, y, d, d);
	}
	
	public void move()
	{
		if(getX()+getXa()<0||getX()+getXa()>1020-getD()) setXa(0); 
		if(getY()+getYa()<0||getY()+getYa()>640-getD()) setYa(0); 
		if(getY()+getD()<640) 
		{
			setYa(getYa()+1);
			setJumped(true);
		}
		else setJumped(false);
		setX(getX()+getXa());
		setY(getY()+getYa());
	}
}
