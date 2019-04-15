package gfx;

import java.awt.image.BufferedImage;

public class Assets {

	public static BufferedImage grass, grass2, stone, mud, tree;
	private static final int width = 32, height = 32;
	public static BufferedImage[] player_right, player_left, player_down, player_up;

	public static void init() { // going to load in all the textures once
		SpriteSheet sheet = new SpriteSheet((ImageLoader.loadImage("/linkFormatted.png")));

		// initialized the character spritesheet
		player_right = new BufferedImage[8];
		player_left = new BufferedImage[8];
		player_down = new BufferedImage[8];
		player_up = new BufferedImage[8];

		// Load files
		for (int x = 0; x < player_right.length; x++) {
			player_right[x] = sheet.crop(width * x, 0, width, height);
		}
		for (int x = 0; x < player_left.length; x++) {
			player_left[x] = sheet.crop(width * x, height * 1, width, height);
		}
		for (int x = 0; x < player_down.length; x++) {
			player_down[x] = sheet.crop(width * x, height * 2, width, height);
		}
		for (int x = 0; x < player_up.length; x++) {
			player_up[x] = sheet.crop(width * x, height * 3, width, height);
		}

		SpriteSheet sheet2 = new SpriteSheet((ImageLoader.loadImage("/grass.png")));
		grass = sheet2.crop(0, 0, width, height);
		grass2 = sheet2.crop(width * 9, height * 10, width, height);

		SpriteSheet sheet3 = new SpriteSheet((ImageLoader.loadImage("/01tizeta_floor_d.png")));
		stone = sheet3.crop(0, 0, 256, 256);

		SpriteSheet sheet4 = new SpriteSheet((ImageLoader.loadImage("/mud2.png")));
		mud = sheet4.crop(0, 0, 128, 128);

		SpriteSheet sheet5 = new SpriteSheet((ImageLoader.loadImage("/baum.png")));
		tree = sheet5.crop(0, 0, 128, 128);
	}
}
