package Graphics;

import java.awt.image.BufferedImage;

public class Sprites {
	
	private static int Width = 160;
	private static int Height = 160;
	public static BufferedImage CaraLoftFront1;
	public static BufferedImage CaraLoftFront2;
	public static BufferedImage CaraLoftFront3;
	public static BufferedImage CaraLoftFront4;
	
	public static void LoadSprites() {
		SpriteSheet Sheet = new SpriteSheet(TextureLoader.Image("/textures/CaraLoftFront.png"));
		
		CaraLoftFront1 = Sheet.CropSheet(Width, Height, 0, 0);
		CaraLoftFront2 = Sheet.CropSheet(Width, Height, 160, 0);
		CaraLoftFront3 = Sheet.CropSheet(Width, Height, 320, 0);
		CaraLoftFront4 = Sheet.CropSheet(Width, Height, 480, 0);
		
		
	}
}
