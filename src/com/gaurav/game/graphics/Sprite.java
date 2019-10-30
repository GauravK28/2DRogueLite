package com.gaurav.game.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.gaurav.game.util.Vector;

public class Sprite extends Pixels {

	private final int TILE_SIZE = 32;
	private int w = TILE_SIZE;
	private int h = TILE_SIZE;
	
	public Sprite(String file) {
		super(file, 0, 0);
		setwItem(getSheet().getWidth() / w);
		sethItem(getSheet().getHeight() / h);
	}
	
	public BufferedImage[] getSpriteArray(int i) { return getArray()[i]; }

	public static void drawArray(Graphics2D graphics, ArrayList<BufferedImage> images, Vector pos, int width, int height, int xOffset, int yOffset) {
		float x = pos.x;
		float y = pos.y;
		for(BufferedImage image: images) {
			if(image != null) {
				graphics.drawImage(image, (int) x, (int) y, width, height, null);
			}
			x += xOffset;
			y += yOffset;
		}
	} 
	
	public static void drawArray(Graphics2D graphics, Font font, String text,
								 Vector pos, int width, int height, int xOffset, int yOffset) {
		float x = pos.x;
		float y = pos.y;
		
		for(int i =0; i< text.length(); i++) {
			if(text.charAt(i) != 32) {
				graphics.drawImage(font.getFont(text.charAt(i)), (int) x, (int) y, width, height, null);
			}
			x += xOffset;
			y += yOffset;
		}
	}
	
	
	
	
	
	

	
	
	
	
	
	
}
