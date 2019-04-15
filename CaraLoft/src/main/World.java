package main;

import Entity.EntityManager;
import Entity.Entity_Creatures.Player;
import Entity.Entity_Items.Tile;
import Entity.Statics.Tree;
import main.utils.Utils;

import java.awt.*;

public class World {

	private Handler handler;
	private int width, height;
	private int[][] tilesArray; // stores the id for the tile at each location
	private int spawnX, spawnY;

	// Entities
	private EntityManager entityManager;

	public World(Handler handler, String path) {
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		entityManager.addEntity(new Tree(handler, 100, 250));

		loadWorld(path);

		entityManager.getPlayer().setPosX(spawnX);
		entityManager.getPlayer().setPosY(spawnY);
	}

	public void tick() {
		entityManager.tick();
	}

	public void render(Graphics g) {
		// This makes the game render only the tiles that are actually on the screen now

		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH); // Calculates the max
																								// between zero and the
																								// leftmost tile we can
																								// see on the camera
		int xEnd = (int) Math.min(width,
				(handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height,
				(handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

		for (int y = yStart; y < yEnd; y++) {
			for (int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		// Entities
		entityManager.render(g);
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.stoneTile;

		Tile t = Tile.textureTiles[tilesArray[x][y]]; // gets the id from the array containing ids, uses it in the tiles
														// array to know which tile is that id and sets it to t
		if (t == null) { // if we haven't set this tile
			return Tile.stoneTile; // default tile
		}
		return t;
	}

	private void loadWorld(String path) { // gets the file and gets all the data and stores it into the array
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+"); // split up all the numbers in our world1 file and split up into different
												// strings in an array

		width = Utils.parseInt(tokens[0]); // takes the value at 0 in the array tokens, which contains the numbers in
											// the world1 file, uses the parseInt method
											// and sets it to the width
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);

		// read data into the tilesArray
		tilesArray = new int[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tilesArray[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]); // (x + y * width) should set each id to
																				// the correct spot o+4 since first 4
																				// are used above
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
}
