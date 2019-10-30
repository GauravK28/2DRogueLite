package com.gaurav.game.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.gaurav.game.graphics.Animation;
import com.gaurav.game.graphics.Sprite;

import com.gaurav.game.util.Collision;
import com.gaurav.game.util.Vector;

public abstract class Entity {
	
	private final int UP = 3;
	private final int DOWN = 2;
	private final int RIGHT = 2;
	private final int LEFT = 0;
	
	Animation animation;
	private Sprite sprite;
	Vector pos;
	int size;
	
	protected boolean up;
	protected boolean down;
	boolean right;
	boolean left;
	boolean attack;
	protected int attackSpeed;
	protected int attackDuration;

	float dx;
	float dy;

	float maxVelocity = 4f;
	protected float acc = 3f;
	protected float deAcc = 0.8f;

	private Collision hitBounds;
	private Collision bounds;

	private int currentAnimation;

	Entity(Sprite sprite, Vector pos, int size) {
		this.sprite = sprite;
		this.pos = pos;
		this.size = size;
		
		bounds = new Collision(pos, size, size);
		hitBounds = new Collision(new Vector(pos.x + (size /2f), pos.y), size, size);
		animation = new Animation();
		setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10);
	}
	
	public int getSize() {
		return size;
	}

	private void setAnimation(int i, BufferedImage[] frames, int delay) {
		currentAnimation = i;
		animation.setFrames(frames);
		animation.setDelay(delay);
	}
	
	private void animate() {
		if(up) {
			if(currentAnimation != UP  || animation.getDelay() == -1) {
				setAnimation(UP, sprite.getSpriteArray(UP),5);
			}
		}
		else if(down) {
			if(currentAnimation != DOWN  || animation.getDelay() == -1) {
				setAnimation(DOWN, sprite.getSpriteArray(DOWN),5);
			}
		}
		else if(right) {
			if(currentAnimation != RIGHT  || animation.getDelay() == -1) {
				setAnimation(RIGHT, sprite.getSpriteArray(RIGHT),5);
			}
		}
		else if(left) {
			if(currentAnimation != LEFT  || animation.getDelay() == -1) {
				setAnimation(LEFT, sprite.getSpriteArray(LEFT),5);
			}
		}
		else {
			setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation), -1);
		}
	}
	
	private void setHitBoxDirection() {
		if(up) {
			hitBounds.setYOffset(-size / 2f);
			hitBounds.setXOffset(-size / 2f);
		}
		else if(down) {
			hitBounds.setYOffset(size/2f);
			hitBounds.setXOffset(-size/2f);
		}
		else if(left) {
			hitBounds.setYOffset(-size);
			hitBounds.setXOffset(0);
		}
		else {
			hitBounds.setYOffset(0);
			hitBounds.setXOffset(0);
		}
	}
	
	public void update() {
		animate();
		setHitBoxDirection();
		animation.update();
	}
	
	public abstract void render(Graphics2D graphics);
	
	
}
