package Entity;

import java.awt.Graphics;

import Main.Handler;
import Main.Main;

public abstract class Entity {
	
	protected float PosX;
	protected float PosY;

	protected int Width;
	protected int Height;
	protected Handler Handler;
	
	public Entity(Handler Handler, int Width, int Height, float PosX, float PosY) {
		this.Handler = Handler;
		this.PosX = PosX;
		this.PosY = PosY;
		this.Width = Width;
		this.Height = Height;
		
	}
	
	public abstract void Tick();
	
	public abstract void Render(Graphics GraphicsObj);
	
	public int GetHeight() {
		return Height;
	}

	public void SetHeight(int height) {
		Height = height;
	}

	public int GetWidth() {
		return Width;
	}

	public void SetWidth(int width) {
		Width = width;
	}
	
	public float GetPosY() {
		return PosY;
	}

	public void SetPosY(float posY) {
		PosY = posY;
	}
	
	public float GetPosX() {
		return PosX;
	}

	public void SetPosX(float posX) {
		PosX = posX;
	}

}
