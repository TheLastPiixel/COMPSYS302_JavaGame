package Graphics;

import Entity.Entity;
import Main.Main;
import Main.Handler;
import Tiles.Tiles;


public class Camera {

	private Main Game;
	private float OffsetX;
	private float OffsetY;
	private Handler handler;
	
	public Camera(Handler handler, Main Game, float OffsetX, float OffsetY) {
		this.handler = handler;
		this.Game = Game;
		this.OffsetX = OffsetX;
		this.OffsetY = OffsetY;
	}

	public void Centre(Entity Entity) {
		OffsetX = (Entity.GetPosX() - Game.GetWidth() / 2) + (Entity.GetWidth() / 2);
		OffsetY = (Entity.GetPosY() - Game.GetHeight() / 2) + (Entity.GetHeight() / 2);
		fixToScreen();
	}

	private void fixToScreen(){
		if(OffsetX < 0) {
			OffsetX = 0;
		}else if(OffsetX >  handler.getRoom().getWidth() *  Tiles.TileWidth - handler.GetWidth()){
			OffsetX = handler.getRoom().getWidth() * Tiles.TileWidth - handler.GetWidth();
		}
		if (OffsetY < 0) {
			OffsetY = 0;
		}else if(OffsetY > handler.getRoom().getHeight() * Tiles.TileHeight - handler.GetHeight()){
			OffsetY = handler.getRoom().getHeight() * Tiles.TileHeight - handler.GetHeight();
		}
	}

	public void Shift(float ShiftX, float ShiftY) {
		OffsetX += ShiftX;
		OffsetY += ShiftY;
	}
	
	
	public float GetOffsetX() {
		return OffsetX;
	}

	public void SetOffsetX(float offsetX) {
		OffsetX = offsetX;
	}

	public float GetOffsetY() {
		return OffsetY;
	}

	public void SetOffsetY(float offsetY) {
		OffsetY = offsetY;
	}
}
