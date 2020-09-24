package core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import states.GameStateManager;
import utils.KeyHandler;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener {
	
	public static int width;
	public static int height;
	
	private Thread thread;
	private boolean running;
	
	private BufferedImage image;
	private Graphics2D graphics;
	
	private GameStateManager gsm;
	
	public GamePanel(int _width, int _height) {
		setPreferredSize(new Dimension(_width, _height));
		addKeyListener(this);
		setFocusable(true);
		requestFocus();
		
		width = _width;
		height = _height;
		running = false;
	}
	
	// Start's up the program's thread using the JPanel's auto-called function "addNotify"
	public void addNotify() {
		super.addNotify();
		
		if (thread == null) {
			thread = new Thread(this, "GameThread");
			thread.start();
		}
	}
	
	// Initializes the panel for displaying graphics; begins the game loop as well.
	public void init() {
		running = true;
		
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		graphics = (Graphics2D) image.getGraphics();
		
		KeyHandler.instantiateKeys();
		
		gsm = new GameStateManager();
	}

	// The program's game loop, which updates at 60hz and renders as fast as possible
	public void run() {
		init();
		
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double updatesPerSecond = 60.0;
		double frameTime = 1_000_000_000 / updatesPerSecond;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / frameTime;
			lastTime = now;
			
			while(delta >= 1) {
				update();
				input();
				updates++;
				delta--;
			}
			
			render();
			draw();
			
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				System.out.println("FPS: " + frames + ", Updates: " + updates);
				timer += 1000;
				frames = 0;
				updates = 0;
			}
		}
	}
	
	// Updates all of the game's objects/input/physics etc.
	public void update() {
		gsm.update();
	}
	
	// Handles all keyboard/mouse input from the user
	public void input() {
		gsm.input();
		// Update the previous key states AFTER checking input from user
		KeyHandler.updatePrevState();
	}
	
	// Draws all of the game's graphics onto the BufferedImage
	public void render() {
		if (graphics != null) {
			graphics.setColor(new Color(0.0f, 0.0f, 0.0f));
			graphics.fillRect(0, 0, width, height);
			gsm.render(graphics);
		}
	}
	
	// Displays the BufferedImage onto the screen after drawing graphics to it for the frame
	public void draw() {
		Graphics g = (Graphics) this.getGraphics();
		g.drawImage(image, 0, 0, width, height, null);
		g.dispose();
	}

	// Keyboard input events ///////////////////////////////////////////////////////////////////////////////
	
	public void keyPressed(KeyEvent _event) {
		KeyHandler.keySet(_event.getKeyCode(), true);
	}

	public void keyReleased(KeyEvent _event) {
		KeyHandler.keySet(_event.getKeyCode(), false);
	}

	public void keyTyped(KeyEvent _event) { /* UNUSED METHOD */}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
}
