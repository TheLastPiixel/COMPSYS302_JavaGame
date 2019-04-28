package Tiles;

import Graphics.Sprites;

public class Grass extends Tiles {
	
	public Grass(int ID) {
		super(Sprites.Grass, ID);
	}
	
	@Override
	public boolean IsSolid() {
		return false;
	}

}
