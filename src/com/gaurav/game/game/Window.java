package com.gaurav.game.game;

import javax.swing.JFrame;

public class Window extends JFrame{
	
	private static final long serialVersionUID = 1227694397549086159L;

	public Window() {
		setTitle("RogueLite");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new GameWindow(1200, 720));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
