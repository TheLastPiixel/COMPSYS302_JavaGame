package Entity;

import java.awt.*;
import java.util.ArrayList;

import Entity.Character.Identifier;
import Main.Handler;

public abstract class Entity {

	protected int Health;
	public static int DefaultHealth = 100;
	protected float PosX;
	protected float PosY;
	protected Identifier id;
	protected Rectangle colBoundary;
	protected boolean Alive = true; 

	protected int Width;
	protected int Height;
	protected Handler Handler;
	private ArrayList<Entity> entities; // like an array but with no specific size
	
	public abstract void Dead();
	
	public abstract void Tick();

	public abstract void Render(Graphics GraphicsObj);

	public Entity(Handler Handler, int Width, int Height, Identifier id, float PosX, float PosY) {
		this.id = id;
		this.Handler = Handler;
		this.PosX = PosX;
		this.PosY = PosY;
		this.Width = Width;
		this.Height = Height;
		Health = DefaultHealth;
		colBoundary = new Rectangle(0,0, Width, Height);
		entities = new ArrayList<Entity>();
	}
	
	public void Damage(int Damage) {
		Health -= Damage;
		if(Health <= 0) {
			Alive = false;
			Dead();
		}
	}

	// Returns the boundary box
	public Rectangle getCollisionBounds(float xOffset, float yOffset){
		return new Rectangle((int)(PosX + colBoundary.x + xOffset), (int)(PosY + colBoundary.y + yOffset), colBoundary.width, colBoundary.height);
	}

	public boolean checkEntityCollisions(float xOffset, float yOffset){
		for(Entity e : Handler.getEntities()){
			// dont check collisions on urself
			if(e.equals(this)){
				continue;
			}
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))){
				return true;
			}
		}
		return false;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void addEntity(Entity entity){
		entities.add(entity);
	}
	public void removeEntity(Entity entity){
		entities.remove(entity);
	}


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
	
	public boolean GetAlive() {
		return Alive;
	}


	
	public abstract Identifier getId();

	public abstract Rectangle getBounds();
}
