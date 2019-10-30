package com.gaurav.game.util;

import com.gaurav.game.entities.Entity;

public class Collision {
	
	private Vector pos;
	private float xOffset = 0;
	private float yOffset = 0;
	private float w;
	private float h;
	private float r;
	private int size;
	private Entity e;
	
	public Collision(Vector pos, int w, int h) {
		this.pos = pos;
		this.w = w;
		this.h = h;
		this.size = Math.max(w, h);
	}
	
	public Collision(Vector pos, int r, Entity e) {
		this.pos = pos;
		this.r = r;
		this.e = e;
		this.size = r;
	}

	public Vector getPos() {
		return pos;
	}

	public float getRadius() {
		return r;
	}
	
	public float getWidth() {
		return w;
	}
	
	public float getHeight() {
		return h;
	}
	
	public void setBox(Vector pos, int w, int h) {
			this.pos = pos; 
			this.w = w;
			this.h= h;
			this.size = Math.max(w, h);
	}
	
	public void setCircle(Vector pos, int r) {
		this.pos = pos;
		this.r = r;
		this.size = r;
	}

	public void setWidth(Float f) {
		w =f;
	}
	
	public void setHeight(float f) {
		h = f;
	}
	
	public void setXOffset(float f) {
		xOffset = f;
	}
	
	public void setYOffset(float f) {
		yOffset = f;
	}
	
	public boolean collides(Collision box) {
		float x1 = ((pos.getWorldVar().x + (xOffset)) + (w/2));
		float y1 = ((pos.getWorldVar().y + (yOffset)) + (h/2));
		float x2 = ((box.pos.getWorldVar().x + (box.xOffset / 2)) + (w/2));
		float y2 = ((box.pos.getWorldVar().y + (box.yOffset / 2)) + (h/2));
		
		if(Math.abs(x1 - x2) < (this.w / 2) + (box.w / 2)) {
			if(Math.abs(y1 - y2) < (this.h / 2) + (box.h / 2)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean collidesCircle(Collision box) {
		float x3 = (float) (pos.getWorldVar().x + r / Math.sqrt(2) - e.getSize() / Math.sqrt(2));
		float y3 = (float) (pos.getWorldVar().y + r / Math.sqrt(2) - e.getSize() / Math.sqrt(2));
		
		float xDiff = x3 - Math.max(box.pos.getWorldVar().x + (box.getWidth() / 2), Math.min(x3, box.pos.getWorldVar().x));
		float yDiff = y3 - Math.max(box.pos.getWorldVar().y + (box.getWidth() / 2), Math.min(y3, box.pos.getWorldVar().y));
		
		if((xDiff * xDiff + yDiff* yDiff) < ((this.r / Math.sqrt(2)) * (this.r / Math.sqrt(2)))) {
			return true;
		}
		return false;
	}
	
	
	
	
	
	
	
}
