package Tiles;

import Graphics.Sprites;

public class Sandstone extends Tiles{
	
	public Sandstone(int ID) {
		super(Sprites.Sandstone, ID);
	}
	
	@Override
	public boolean IsSolid() {
		return true;
	}
	
}
