import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
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

public class Goober extends Enemy 
{
	int type, x, y, spd, dmg, hp, dim;
	double size;
	public Goober(int type, double size, int x, int y)
	{
		this.type = type;
		this.size = size;
		this.x = x;
		this.y = y;
		spd = 8;
		dmg = (int)(5*this.size);
		hp = (int)(10*this.size);
		dim = (int)(30*this.size);
	}
	
	public int getHp() {return hp;}
	public int getdmg() {return dmg;}
	public double getSize() {return size;}
	public int getDim() {return dim;};
	public int getType() {return type;}
	public int getSpd() {return spd;}
	public int getX() {return x;}
	public int getY() {return y;}

	public void setHp(int hold) {hp = hold;}
	public void setdmg(int hold) {dmg = hold;}
	public void setSize(double hold) {size = hold; dim = (int)(30*hold);}
	public void setType(int hold) {type = hold;}
	public void setSpd(int hold) {spd = hold;}
	public void setX(int hold) {x = hold;}
	public void setY(int hold) {y = hold;}
	
	public void move(Player pl) 
	{
		if(getX()+getSize()+getSpd()<1020&&getX()+getSpd()>0) setX(getX()+getSpd());
		else setSpd(getSpd()*-1);
		if(pCollision(pl)) setSpd(getSpd()*-1);
	}
	
	public boolean pCollision(Player pl)
	{
		boolean leftX = (getX()<pl.getX()&&getX()+getSize()>pl.getX());
		boolean rightX = (getX()>pl.getX()&&getX()+getSize()<pl.getX());
		System.out.println(leftX + " " + rightX);
		return(leftX||rightX);
	}
	
	public void paint(Graphics g)
	{
		Graphics2D gb = (Graphics2D) g;
		gb.setColor(Color.blue);
		gb.fillRect(getX(), getY(), getDim(), getDim());
	}
	
	
}
