package Entity.Entity_Items;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

	// STATIC VARIABLES

	public static Tile[] textureTiles = new Tile[256]; // tiles array set the the tiles
	public static Tile grassTile = new GrassTile(0);
	public static Tile stoneTile = new StoneTile(1);
	public static Tile mudTile = new MudTile(3);

	// CLASS

	protected BufferedImage texture; // img of the tile
	protected final int id; // each tile has a unique id
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;

	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		textureTiles[id] = this; // the tile at id is the current tile, can use this array to generate the tiles
	}

	public int getId() {
		return id;
	}

	public void tick() {

	}

	public void render(Graphics g, int posX, int posY) { // img and position of tile entered
		g.drawImage(texture, posX, posY, TILEWIDTH, TILEHEIGHT, null);
	}

	public boolean isSolid() { // check if u can walk through such a tile
		return false;
	}

}
