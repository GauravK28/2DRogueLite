package com.gaurav.game.util;

public class Vector {
	
	public float x;
	public float y;
	
	public static float worldX;
	public static float worldY;

	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public static void setWorldVar(float x, float y) {
		worldX = x;
		worldY = y;
	}
	
	public Vector getWorldVar() {
		return new Vector(x - worldX, y - worldY);
	}
}
