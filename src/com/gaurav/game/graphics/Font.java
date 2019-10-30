package com.gaurav.game.graphics;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * 
 * @author gaura
 *	
 *TODO:
 *Create margin variable so text can restart on next line
 */


public class Font extends Pixels{

	public int w;
	public int h;

	public Font(String file, int w, int h) {
		super(file,w,h);
		this.w = w;
		this.h= h;
	}
	
	public BufferedImage getFont(char letter) {
		int value =  letter - 65;
		
		int x = value % getWItem();
		int y = value / getWItem();
		return getItem(x,y);
	}
	
	
}
