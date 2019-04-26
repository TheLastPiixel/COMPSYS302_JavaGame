package Tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tiles {
	
	//Sets an array for all the tiles 
	public static Tiles[] Tiles = new Tiles[10];
	public static Tiles Grass = new Grass(0);
	public static Tiles Sandstone = new Sandstone(1);
	public static Tiles WoodenTile = new WoodenTile(2);
	
	/*Tile IDs
	Grass = 0
	Sandstone = 1
	WoodenTile = 2
	*/
	
	public static final int TileWidth = 64;
	public static final int TileHeight = 64;
	protected BufferedImage Tile;
	protected int ID; //Should be final?????
	
	public Tiles(BufferedImage Tile, int ID) {
		this.Tile = Tile;
		this.ID = ID;
		
		Tiles[ID] = this;
	}
	
	public void Render(int PosX, int PosY, Graphics GraphicsObj) {
		GraphicsObj.drawImage(Tile,  PosX, PosY, TileWidth, TileHeight, null);
	}
	
	public void Tick() {
		
	}
	
	//Checks if the Tile is a solid structure
	public boolean IsSolid() {
		return false;
	}
	
	//Tile ID Getter
	public int GetID() {
		return ID;
	}
	
}
