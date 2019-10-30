package com.gaurav.game.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Pixels {
    private BufferedImage sheet;
    private BufferedImage[][] array;
    private int wItem;
    private int hItem;

    public Pixels(String file, int wItem, int hItem) {
        this.wItem = wItem;
        this.hItem = hItem;

        System.out.println("Loading: " + "....." );
        sheet = loadFile(file);

        this.wItem = sheet.getWidth() / wItem;
        this.hItem = sheet.getHeight() / wItem;
        loadFileArray();
    }
    public BufferedImage loadFile(String file) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
        } catch(Exception e) {
            System.out.println("ERROR: could not load file: " + file);
        }
        return img;
    }

    public void loadFileArray() {
        array = new BufferedImage[wItem][hItem];
        for(int x = 0; x < wItem; x++) {
            for(int y = 0; y<hItem; y++) {
                array[x][y] = getItem(x,y);
            }
        }
    }

    public void setwItem(int wItem) { this.wItem = wItem; }

    public void sethItem(int hItem) { this.hItem = hItem; }

    public BufferedImage[][] getArray() { return array; }

    public int getWItem() { return wItem; }

    public BufferedImage getSheet() { return sheet; }

    public BufferedImage getItem(int x, int y) {
        return sheet.getSubimage(x*wItem,  y*hItem, wItem, hItem);
    }
}
