package com.gaurav.game.game;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.gaurav.game.gameplay.GameStateManager;

import com.gaurav.game.util.KeyHandler;
import com.gaurav.game.util.MouseHandler;


public class GameWindow extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 3874704837878496773L;
	
	public static int width, height;
	
	private Thread thread;
	private boolean running = false;
	
	private BufferedImage image;
	private Graphics2D graphics;
	
	private MouseHandler mouse;
	private KeyHandler key;
	
	private GameStateManager gsm;
	
	GameWindow(int width, int height) {
		GameWindow.width = width;
		GameWindow.height = height;
		setPreferredSize(new Dimension(width, height));
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread( this, "GameThread");
			thread.start();
		}
	}
	
	private void init() {
		running  = true;
		
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		graphics = (Graphics2D) image.getGraphics();
		
		mouse = new MouseHandler(this);
		key = new KeyHandler(this);
		
		gsm = new GameStateManager();
	}  

	public void run() {
		init();

		final double GAME_HERTZ = 60.0;
		final double TBU = 1000000000 / GAME_HERTZ; //time before update
		final double TARGET_FPS = 60;
		final double TTBR = 1000000000 / TARGET_FPS; //total time before render
		int oldFrameCount = 0;
		long initialTime = System.nanoTime();
		final double timeU = 1000000000 / TBU;
		final double timeF = 1000000000 / TTBR;
		double deltaU = 0, deltaF = 0;
		int frames = 0, ticks = 0;
		long timer = System.currentTimeMillis();

		while (running) {

			long currentTime = System.nanoTime();
			deltaU += (currentTime - initialTime) / timeU;
			deltaF += (currentTime - initialTime) / timeF;
			initialTime = currentTime;

			if (deltaU >= 1) {
				input(mouse, key);
				update();
				ticks++;
				deltaU--;
			}

			if (deltaF >= 1) {
				render();
				draw();
				frames++;
				deltaF--;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				if (frames != oldFrameCount) {
					System.out.println(String.format("UPS: %s, FPS: %s", ticks, frames));
				}
				oldFrameCount = frames;
				frames = 0;
				ticks = 0;
				timer += 1000;
			}
		}

	 }
	public void update() {
		gsm.update();
	}
	
	private void input(MouseHandler mouse, KeyHandler key) {
		gsm.input(mouse, key);
	}
	
	private void render() {
		if(graphics != null) {
			graphics.setColor(new Color(66, 134, 244));
			graphics.fillRect(0,  0, width, height);
			gsm.render(graphics);
		}
	}
	
	private void draw() {
		Graphics graphics2 = this.getGraphics();
		graphics2.drawImage(image, 0, 0, width, height, null);
		graphics2.dispose();
	}
	
	
	
	
}
