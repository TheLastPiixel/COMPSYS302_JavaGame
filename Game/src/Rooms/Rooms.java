package Rooms;

import java.awt.Graphics;

import Graphics.Camera;
import Main.Helpers;
import Tiles.Tiles;
import Main.Handler;

public class Rooms {
	
	private int X, Y;
	private int InitialX;
	private int InitialY;
	//The number of tiles to render out of the screen
	private int TileRenderBuffer = 1;
	private int LeftLimit = 0;
	private int RightLimit = 0;
	private int TopLimit = 0;
	private int BottomLimit = 0;
	private int Width;
	private int Height;
	private int[][] RoomLayout;
	private Handler Handler;
	
	public Rooms(Handler Handler, String FilePath) {
		this.Handler = Handler;
		LoadRoom(FilePath);
	}
	
	//Caulcuates which tiles to render
	private void CalculateRenderLimits(Camera Camera) {
		TopLimit = (int) Math.max(0, Handler.GetCamera().GetOffsetY() / Tiles.TileHeight);
		BottomLimit = (int) Math.min(Height, (Handler.GetCamera().GetOffsetY() + Handler.GetHeight()) / Tiles.TileHeight + TileRenderBuffer);
		LeftLimit = (int) Math.max(0, Handler.GetCamera().GetOffsetX() / Tiles.TileWidth);
		RightLimit = (int) Math.min(Width, (Handler.GetCamera().GetOffsetX() + Handler.GetWidth())/ Tiles.TileWidth + TileRenderBuffer);
	}
	
	public void Render(Graphics GraphicsObj) {
		CalculateRenderLimits(Handler.GetCamera());
		
		for (X = LeftLimit; X < RightLimit; X++) {
			for (Y = TopLimit; Y < BottomLimit; Y++) {
				GetTileTexture(X, Y).Render((int) (X * Tiles.TileWidth - Handler.GetCamera().GetOffsetX()), (int) (Y * Tiles.TileHeight - Handler.GetCamera().GetOffsetY()), GraphicsObj);
			}
		}
	}
	
	//Fetches the Tile texture
	public Tiles GetTileTexture(int XPos, int YPos) {
		Tiles TileTexture = Tiles.Tiles[RoomLayout[XPos][YPos]];
		
		//Checks if the tile is valid
		if (TileTexture == null) {
			return Tiles.WoodenTile;
		}
		return TileTexture;
	}
	
	public void Tick() {
		
	}
	
	//Loads the room from a selected file
	/*Room.txt File Layout
	 * [Width] [Height]
	 * [InitialPlayerPosX] [InitialPlayerPosY]
	 * [TileID] [TileID] ...........
	 * .............................
	 * .............................
	 */
	private void LoadRoom(String FilePath) {
		String RoomFile = Helpers.ReadFileToString(FilePath);
		String[] RoomTokens = RoomFile.split("\\s+");
		Width = Helpers.StringToInt(RoomTokens[0]);
		Height = Helpers.StringToInt(RoomTokens[1]);
		InitialX = Helpers.StringToInt(RoomTokens[2]);
		InitialY = Helpers.StringToInt(RoomTokens[3]);
		
		RoomLayout = new int[Width][Height];
		for (int X = 0; X < Width; X++) {
			for (int Y = 0; Y < Height; Y++) {
				RoomLayout[X][Y] = Helpers.StringToInt(RoomTokens[(X + (Y * Width)) + 4]);
			}
		}
	}

	//GETTERS & SETTERS
	public int getWidth() {
		return Width;
	}

	public void setWidth(int Width) {
		this.Width = Width;
	}

	public int getHeight() {
		return Height;
	}

	public void setHeight(int Height) {
		this.Height = Height;
	}
	
	public int GetInitialX() {
		return InitialX;
	}
	
	public int GetInitialY() {
		return InitialY;
	}
}
