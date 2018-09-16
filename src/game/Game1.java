package game;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
	
	
public class Game1 extends JFrame {
	public static final int sizex = 1150;
	public static final int sizey = 850;
	public static final int posx = 50;
	public static final int posy = 50;
	public static final int bsize = 8;
			
	Random rnd = new Random();
	int bx;
	int by;
	int speedx;
	int speedy;
	
	//Speed added by mouse:
	int mspeedx;
	int mspeedy;
	
	boolean go = true;
	

	public static void main(String[] args) {
		new Game1().start();
	}
	
	Game1(){
		super();
		setSize(sizex+(posx*2), sizey+(posy*2));
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addMouseListener(new MyMouseListener());
	}
	
	void start(){
		Graphics g = getGraphics();
		g.drawRect(posx, posy, sizex+bsize, sizey+bsize);
		bx = rnd.nextInt(sizex)+posx;
		by = rnd.nextInt(sizey)+posy;
		g.fillOval(bx, by, bsize, bsize);
	
		//speedx = rnd.nextInt(8)-4;
		//speedy = rnd.nextInt(8)-4;
		while(go){
			move(g);
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {}
		}
	}
	
	void move(Graphics g){
		clear(g);
		bx += speedx;
		by += speedy;
		
		if(bx > sizex+posx){
			speedx *= -1;
			bx = sizex+posx;
		}
		
		if(bx < posx){
			speedx *= -1;
			bx = posx;
		}
		
		if(by > sizey+posy){
			speedy *= -1;
			by = sizey+posy;
		}
		
		if(by < posy){
			speedy *= -1;
			by = posy;
		}
		
		g.fillOval(bx, by, bsize, bsize);
	}
	
	void clear(Graphics g){
		g.clearRect(posx+1, posy+1, sizex+bsize-1, sizey+bsize-1);
	}
	
	
	

	public class MyMouseListener implements MouseListener {
		
		public static final int maxSpeed = 30;
		
		public MyMouseListener() {
			super();
		}

		@Override
		public void mouseClicked(MouseEvent mev) {
			int x = mev.getPoint().x - bx;
			int y = mev.getPoint().y - by;
			int c = (int)Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
			int speed = 30-(c/10);
			mspeedx = (int)Math.sqrt(Math.pow(speed, 2)/(Math.pow(x/y, 2)+1));
			mspeedy = (int)Math.sqrt(Math.pow(speed, 2)/(Math.pow(y/x, 2)+1));
			
			speedx += mspeedx*x/Math.abs(x);
			speedy += mspeedy*y/Math.abs(y);
			
			/*mspeedx = maxSpeed / ((mev.getPoint().x - bx)/10);
			mspeedy = maxSpeed / ((mev.getPoint().y - by)/10);
			speedx += mspeedx;
			speedy += mspeedy;*/
		}

		@Override
		public void mouseEntered(MouseEvent mev) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent mev) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent mev) {
			

		}

		@Override
		public void mouseReleased(MouseEvent mev) {
			// TODO Auto-generated method stub

		}

	}
}
