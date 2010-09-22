package net.xurdm.fullscreen;
import java.awt.*;
import javax.swing.JFrame;

public class Screen {
	private GraphicsDevice device;
	
	public Screen() {
		GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		device = genv.getDefaultScreenDevice();
	}
	
	public void setFullScreen(DisplayMode displayMode, JFrame window) {
		window.setUndecorated(true);
		window.setResizable(false);
		
		device.setFullScreenWindow(window);
		if(displayMode != null && device.isDisplayChangeSupported()) {
			try {
				device.setDisplayMode(displayMode);
			} catch(IllegalArgumentException e) {
				System.out.println("Illegal display mode");
			}
		}
	}
	
	public Window getFullScreenWindow() {
		return device.getFullScreenWindow();
	}
	
	public void restoreScreen() {
		Window window = device.getFullScreenWindow();
		if(window != null)
			window.dispose();
		device.setFullScreenWindow(null);
	}
}
