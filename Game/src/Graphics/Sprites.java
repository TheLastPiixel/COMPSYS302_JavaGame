package Graphics;

import java.awt.image.BufferedImage;

public class Sprites {
	
	private static int Width = 64;
	private static int Height = 64;
	public static BufferedImage CaraLoftFront1;
	public static BufferedImage CaraLoftFront2;
	public static BufferedImage CaraLoftFront3;
	public static BufferedImage CaraLoftFront4;
	public static BufferedImage Grass;
	public static BufferedImage Sandstone;
	public static BufferedImage WoodenTile;
	
	
	public static void LoadSprites() {
		SpriteSheet Sheet = new SpriteSheet(TextureLoader.Image("/textures/CaraLoftFront.png"));
		SpriteSheet Tiles = new SpriteSheet(TextureLoader.Image("/textures/Tiles.png"));
		
		CaraLoftFront1 = Sheet.CropSheet(Width, Height, 0, 0);
		CaraLoftFront2 = Sheet.CropSheet(Width, Height, 64, 0);
		CaraLoftFront3 = Sheet.CropSheet(Width, Height, 128, 0);
		CaraLoftFront4 = Sheet.CropSheet(Width, Height, 192, 0);
		Grass = Tiles.CropSheet(Width, Height, 0, 0);
		Sandstone = Tiles.CropSheet(Width, Height, 64, 0);
		WoodenTile = Tiles.CropSheet(Width, Height, 128, 0);
		
		
	}
}
