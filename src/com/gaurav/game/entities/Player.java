package com.gaurav.game.entities;

import java.awt.Graphics2D;

import com.gaurav.game.graphics.Sprite;

import com.gaurav.game.util.KeyHandler;
import com.gaurav.game.util.MouseHandler;
import com.gaurav.game.util.Vector;

public class Player extends Entity{
	
	public Player(Sprite sprite, Vector origin, int size) {
		super(sprite, origin, size);
	}
	
	private void move() {
		if(up || down) {
			dy = moveSpeed(dy);
		}
		if(left || right) {
			dx = moveSpeed(dx);
		}
	}
	private float moveSpeed (float pos) {
		if (up || left) {
			pos -= acc;
			if(pos  < -maxVelocity) pos = -maxVelocity;
		} else {
			if(pos < 0) {
				pos += deAcc;
				if(pos > 0) pos = 0;
			}
		}
		if (down || right) {
			pos += acc;
			if(pos  > maxVelocity) pos = maxVelocity;
		}else {
			if(pos > 0) {
				pos -= deAcc;
				if(pos < 0) pos = 0;
			}
		}
		return pos;
	}
	
	public void update() {
		super.update();
		move();
		pos.x += dx;
		pos.y += dy;
	}
	
	public void render(Graphics2D graphics) {
		graphics.drawImage(animation.getImage(), (int) (pos.x), (int) (pos.y), size, size, null);
	}
	
	public void input(MouseHandler mouse, KeyHandler key) {
		if(mouse.getButton() == 1) {
			System.out.println("Player: " + pos.x + ", " + pos.y);
		}
		up = false;
		down = false;
		left = false;
		right = false;
		attack = false;

		if(key.up.down)
			up = true;
		if(key.down.down)
			down  =true;
		if(key.left.down)
			left = true;
		if(key.right.down)
			right = true;
		if(key.attack.down)
			attack = true;

	}
}
