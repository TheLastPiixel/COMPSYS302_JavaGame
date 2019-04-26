package Graphics;

import java.awt.image.BufferedImage;

public class Sprites {
	
	private static int Width = 64;
	private static int Height = 64;
	public static BufferedImage[] CaraLoftFront, CaraLoftLeft, CaraLoftRight, CaraLoftBack;
	public static BufferedImage Grass;
	public static BufferedImage Sandstone;
	public static BufferedImage WoodenTile;
	
	
	public static void LoadSprites() {
		SpriteSheet Sheet = new SpriteSheet(TextureLoader.Image("/textures/CaraLoftFront.png"));
		SpriteSheet Tiles = new SpriteSheet(TextureLoader.Image("/textures/Tiles.png"));

		// initialized the character sprite arrays
		CaraLoftFront = new BufferedImage[4];
		CaraLoftLeft = new BufferedImage[4];
		CaraLoftRight = new BufferedImage[4];
		CaraLoftBack = new BufferedImage[4];

		for(int x = 0; x < CaraLoftFront.length; x++){
			CaraLoftFront[x] = Sheet.CropSheet(Width, Height, Width * x, 0);
		}
		for(int x = 0; x < CaraLoftLeft.length; x++){
			CaraLoftLeft[x] = Sheet.CropSheet(Width, Height, Width * x, 0);
		}
		for(int x = 0; x < CaraLoftRight.length; x++){
			CaraLoftRight[x] = Sheet.CropSheet(Width, Height, Width * x, 0);
		}
		for(int x = 0; x < CaraLoftBack.length; x++){
			CaraLoftBack[x] = Sheet.CropSheet(Width, Height, Width * x, 0);
		}

		Grass = Tiles.CropSheet(Width, Height, 0, 0);
		Sandstone = Tiles.CropSheet(Width, Height, 64, 0);
		WoodenTile = Tiles.CropSheet(Width, Height, 128, 0);
		
		
	}
}
