package Entity.Entity_Creatures;

import Entity.Entity;
import Entity.Entity_Items.Tile;
import main.Handler;

public abstract class Creature extends Entity {

	public static final int DEFAULT_HEALTH = 100;
	public static final float DEFAULT_SPEED = 3.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 64, DEFAULT_CREATURE_HEIGHT = 64;

	protected int health;
	protected float speed;
	protected float xMove, yMove;

	public Creature(Handler handler, float posX, float posY, int width, int height) {
		super(handler, posX, posY, width, height); // passes them along from the entity class
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}

	public void move() {
		moveX();
		moveY();
	}

	public void moveX() {
		if (xMove > 0) { // moving right
			int tx = (int) (posX + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
			if (!collisionWithTile(tx, (int) (posY + bounds.y) / Tile.TILEHEIGHT)
					&& !collisionWithTile(tx, (int) (posY + bounds.y + bounds.height) / Tile.TILEHEIGHT)) { // check if
																											// top right
																											// is
																											// colliding
				posX += xMove;
			} else { // there was a collision, so we reset the x position of the player and line it
						// up next to the tile
				posX = (tx * Tile.TILEWIDTH) - bounds.x - bounds.width - 1; // leaves a 1px gap
			}

		} else if (xMove < 0) { // moving left
			int tx = (int) (posX + xMove + bounds.x) / Tile.TILEWIDTH;
			if (!collisionWithTile(tx, (int) (posY + bounds.y) / Tile.TILEHEIGHT)
					&& !collisionWithTile(tx, (int) (posY + bounds.y + bounds.height) / Tile.TILEHEIGHT)) { // check if
																											// top right
																											// is
																											// colliding
				posX += xMove;
			} else {
				posX = (tx * Tile.TILEWIDTH) + Tile.TILEWIDTH - bounds.x;
			}
		}
	}

	public void moveY() {
		if (yMove < 0) { // up
			int ty = (int) (posY + yMove + bounds.y) / Tile.TILEHEIGHT; // sets to top of collision rectangle

			if (!collisionWithTile((int) (posX + bounds.x) / Tile.TILEWIDTH, ty) && // checks top left and top right for
																					// collisions
					!collisionWithTile((int) (posX + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				posY += yMove;
			} else {
				posY = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
			}

		} else if (yMove > 0) { // down
			int ty = (int) (posY + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT; // set to bottom of collision
																						// rectangle

			if (!collisionWithTile((int) (posX + bounds.x) / Tile.TILEWIDTH, ty) && // checks bottom left and right for
																					// collisions
					!collisionWithTile((int) (posX + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				posY += yMove;
			} else {
				posY = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
			}
		}
	}

	protected boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid(); // get true or false whether it is sold
	}

	// getters and setters for protected variables
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

}
