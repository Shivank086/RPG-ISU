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
public abstract class Enemy {
	private int hp;
	private int dmg;
	private double size;
	private int type;
	private int spd;
	private int x;
	private int y;
	
	public int getHp() {return hp;}
	public int getdmg() {return dmg;}
	public double getSize() {return size;}
	public int getType() {return type;}
	public int getSpd() {return spd;}
	public int getX() {return x;}
	public int getY() {return y;}

	public void setHp(int hold) {hp = hold;}
	public void setdmg(int hold) {dmg = hold;}
	public void setSize(double hold) {size = hold;}
	public void setType(int hold) {type = hold;}
	public void setSpd(int hold) {spd = hold;}
	public void setX(int hold) {x = hold;}
	public void setY(int hold) {y = hold;}
	public abstract void move(Player p);
	//public abstract void getsHit(Player p);
	public abstract void paint(Graphics g);
}
