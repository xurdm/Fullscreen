package net.xurdm.fullscreen;
import java.awt.*;
import javax.swing.JFrame;
import java.util.Random;

public class Main extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String[] colors = {"Red","Blue","Green","Yellow","Orange","Black","White"};
	private static final long DURATION = 5000;
	private static final long INTERVAL = 1000;
	private Random rand;
	
	public static void main(String args[]) {
		DisplayMode dp = new DisplayMode(800,600,32,DisplayMode.REFRESH_RATE_UNKNOWN);
		Main window = new Main();		
		window.run(dp);
	}
	
	public void run(DisplayMode dp) {
		Screen screen = new Screen();
		rand = new Random(System.currentTimeMillis());
		try {
			screen.setFullScreen(dp,this);
			try {
				setFont(new Font("Dialog", Font.PLAIN, 24));
				long startTime = System.currentTimeMillis();
				while(System.currentTimeMillis() < startTime + DURATION) {
					setBackground(Color.getColor(colors[rand.nextInt(colors.length)]));
					setForeground(Color.getColor(colors[rand.nextInt(colors.length)]));
					Thread.sleep(INTERVAL);
				}
			} catch(InterruptedException e) { }
		} finally {
			screen.restoreScreen();
		}
	}
	
	public void paint(Graphics g) {
		if(g instanceof Graphics2D) {
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}
		g.drawString("You are a fat bitch!",20,50);
	}
}
