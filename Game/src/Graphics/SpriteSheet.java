package Graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage SpriteSheet;
	private BufferedImage IndividualSprite;
	
	//Loads the sprite sheet
	public SpriteSheet(BufferedImage Sheet) {
		this.SpriteSheet = Sheet;
		
	}
		
	//Crops the sprite sheet
	public BufferedImage CropSheet(int Width, int Height, int X, int Y) {
		IndividualSprite = SpriteSheet.getSubimage(X, Y, Width, Height);
		return IndividualSprite;
		
	}
	
}
