package Entity.Character;

import Entity.Entity;
import Main.Handler;
import Tiles.Tiles;


public abstract class Character extends Entity{

	protected float SpeedFactor;
	protected float Speed;
	protected float XSpeed;
	protected float YSpeed;
	protected int Mode;
	public static int DefaultWidth = 64;
	public static int DefaultHeight = 64;
	public static int DefaultSpeed = 3;
	protected int attackRange = 40;
	private int MaxHealth = DefaultHealth;


	public Character(Handler Handler, int Width, int Height, Identifier id, float PosX, float PosY) {
		super(Handler, Width, Height, id, PosX, PosY);
		Mode = 1;
		//Speed variables
		XSpeed = 0;
		YSpeed = 0;
		Speed = DefaultSpeed;
		SpeedFactor = 1;
	}

	public void MovementSpeed() {
		if(!checkEntityCollisions(XSpeed, 0f)){
			moveX();
		}
		if(!checkEntityCollisions(0, YSpeed)){
			moveY();
		}
	}

	public void moveX() {
		if (XSpeed > 0) {        //moving right
			int tx = (int) (PosX + XSpeed + colBoundary.x + colBoundary.width) / Tiles.TileWidth;
			if(!collisionWithTile(tx,(int)(PosY + colBoundary.y) / Tiles.TileHeight) && !collisionWithTile(tx, (int) (PosY + colBoundary.y + colBoundary.height)/Tiles.TileHeight)) { // check if top right is colliding
				PosX += XSpeed;
			}else{ // there was a collision, so we reset the x position of the player and line it up next to the tile
				PosX = (tx * Tiles.TileWidth) - colBoundary.x - colBoundary.width - 1; // leaves a 1px gap
			}

		} else if(XSpeed < 0){     //moving left
			int tx = (int) (PosX + XSpeed + colBoundary.x) / Tiles.TileWidth;
			if(!collisionWithTile(tx,(int)(PosY + colBoundary.y) / Tiles.TileHeight) && !collisionWithTile(tx, (int) (PosY + colBoundary.y + colBoundary.height)/Tiles.TileHeight)) { // check if top right is colliding
				PosX += XSpeed;
			}else{
				PosX = (tx * Tiles.TileWidth) + Tiles.TileWidth - colBoundary.x;
			}
		}
	}
	public void moveY(){
		if(YSpeed < 0){ //up
			int ty = (int) (PosY + YSpeed + colBoundary.y) / Tiles.TileHeight; // sets to top of collision rectangle

			if(!collisionWithTile((int)(PosX + colBoundary.x) / Tiles.TileWidth, ty) && // checks top left and top right for collisions
					!collisionWithTile((int)(PosX + colBoundary.x + colBoundary.width) / Tiles.TileWidth, ty)){
				PosY += YSpeed;
			}else{
				PosY = ty * Tiles.TileHeight + Tiles.TileHeight - colBoundary.y;
			}


		}else if (YSpeed > 0){ //down
			int ty = (int)(PosY + YSpeed + colBoundary.y + colBoundary.height) / Tiles.TileHeight; // set to bottom of collision rectangle
			if(!collisionWithTile((int)(PosX + colBoundary.x) / Tiles.TileWidth, ty) && //checks bottom left and right for collisions
					!collisionWithTile((int)(PosX + colBoundary.x + colBoundary.width) / Tiles.TileWidth, ty)){
				PosY += YSpeed;
			}else{
				PosY = ty * Tiles.TileHeight - colBoundary.y - colBoundary.height - 1;
			}
		}
	}

	//Health Getter & Setter
	public int GetHealth() {
		return Health;
	}
	public void SetHealth(int Health) {
		if (this.MaxHealth > Health)
		this.Health = Health;
	}

	//SpeedFactor Getter & Setter
	public float GetSpeedFactor() {
		return SpeedFactor;
	}
	public void SetSpeedFactor(float SpeedFactor) {
		this.SpeedFactor = SpeedFactor;
	}

	//Speed Getter & Setter
	public float GetSpeed() {
		return Speed;
	}
	public void SetSpeed(float Speed) {
		this.Speed = Speed;
	}

	//Mode Getter & Setter
	public int GetMode() {
		return Mode;
	}
	public void SetMode(int Mode) {
		this.Mode =Mode;
	}

	//XSpeed Getter & Setter
	public float GetXSpeed() {
		return XSpeed;
	}
	public void SetXSpeed(float XSpeed) {
		this.XSpeed = XSpeed;
	}

	//YSpeed Getter & Setter
	public float GetYSpeed() {
		return YSpeed;
	}
	public void SetYSpeed(float YSpeed) {
		this.YSpeed = YSpeed;
	}

	protected boolean collisionWithTile(int x, int y){
		return Handler.GetRoom().GetTileTexture(x,y).IsSolid(); // get true or false whether it is sold
	}

}
