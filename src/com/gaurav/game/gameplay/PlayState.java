package com.gaurav.game.gameplay;

import java.awt.Graphics2D;

import com.gaurav.game.entities.Player;
import com.gaurav.game.graphics.Font;
import com.gaurav.game.graphics.Sprite;

import com.gaurav.game.util.KeyHandler;
import com.gaurav.game.util.MouseHandler;
import com.gaurav.game.util.Vector;

public class PlayState extends GameState{
	
	private Font font;
	private Player player;

	PlayState(GameStateManager gsm) {
		super(gsm);
		font = new Font("font/ZeldaFont.png", 16,16);
		player = new Player(new Sprite("entity/RoguePlayer.png"), new Vector( 300, 300), 128 );
	} 

	public void update() {
		player.update();
	}

	public void input(MouseHandler mouse, KeyHandler key) {
		player.input(mouse, key);
	}
	 
	public void render(Graphics2D graphics) {
		Sprite.drawArray(graphics,  font, "World rendered.",
				new Vector(100,100), 32, 32, 16, 0);
		player.render(graphics);
	}
}
