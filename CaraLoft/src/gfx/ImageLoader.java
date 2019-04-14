package gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {

    public static BufferedImage loadImage(String path) {// object where java stores images are called bufferedimages

        try { // try and load the image file
            return ImageIO.read(ImageLoader.class.getResource(path)); // this is how we load an image
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1); // close the game if it fails
        }
        return null;
    }
}
