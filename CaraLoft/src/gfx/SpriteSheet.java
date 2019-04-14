package gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet { // load seperate sprite images

    private BufferedImage sheet, sheet2;

    public SpriteSheet(BufferedImage sheet){ // takes in the sheet
        this.sheet = sheet;
    }

    public BufferedImage crop(int x, int y, int w, int h){
        return sheet.getSubimage(x,y,w,h); // will return a new buffered image of just the region of image we want
    }
}
