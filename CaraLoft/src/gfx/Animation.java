package gfx;

import java.awt.image.BufferedImage;

public class Animation {

    private int speed, index;
    private BufferedImage[] frames;
    private long lastTime, timer;

    public Animation(int speed, BufferedImage[] frames){
        this.speed = speed;
        this.frames = frames;
        index = 0;
        lastTime = System.currentTimeMillis(); // gets the time past since last time we have rum
        timer = 0;
    }

    public void tick(){ // ticks to render each specific image of the sprite
        timer += System.currentTimeMillis() - lastTime; // just gets us the difference in time that has past between ticks
        lastTime = System.currentTimeMillis(); // set new time to lastTime

        if(timer > speed){ // once enough time has gone, we change the index to display the next image
            index++;
            timer = 0;
            if(index >= frames.length){
                index = 0;
            }
        }
    }

    public BufferedImage getCurrentFrame(){
        return frames[index];
    }
}
