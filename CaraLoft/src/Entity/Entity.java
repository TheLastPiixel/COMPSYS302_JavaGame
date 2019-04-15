package Entity;

import main.Handler;
import java.awt.*;

public abstract class Entity { // holds position, tick, render

	protected Handler handler;
	protected float posX, posY; // using floats to allow smooth calculations
	protected int width, height; // size of the entities
	protected Rectangle bounds; // collision bounds

	public Entity(Handler handler, float posX, float posY, int width, int height) {
		this.handler = handler;
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		bounds = new Rectangle(0, 0, width, height);
	}

	public float getPosX() {
		return posX;
	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	public float getPosY() {
		return posY;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public abstract void tick();

	public abstract void render(Graphics g);
}
