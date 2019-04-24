package Graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TextureLoader {
	
	public static BufferedImage Image(String FilePath) {
		try {
			//Returns buffered image from path
			return ImageIO.read(TextureLoader.class.getResource(FilePath));
		}
		
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
		
	}
	
}
