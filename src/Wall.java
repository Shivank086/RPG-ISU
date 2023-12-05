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

public class Wall 
{
	int x, y, w, l, type;
	public Wall() {}
	Color[] Colors = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN};
	public Wall(int x, int y, int w, int l, int type)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.l = l;
		this.type = type;
	}
	
	public int getX() {return x;}
	public int getY() {return y;}
	public int getW() {return w;}
	public int getL() {return l;}
	public int getType() {return type;}
	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}
	public void setW(int w) {this.w = w;}
	public void setL(int l) {this.l = l;}
	public void setType(int type) {this.type = type;}
	
	public void paint(Graphics g)
	{
		Graphics2D wa = (Graphics2D) g;
		wa.setColor(Colors[type-1]);
		wa.fillRect(getX(), getY(), getW(), getL());
	}
}
