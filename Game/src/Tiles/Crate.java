package Tiles;

import Graphics.Sprites;

public class Crate extends Tiles{
	
	public Crate(int ID) {
		super(Sprites.Crate, ID);
	}
	
	@Override
	public boolean IsSolid() {
		return true;
	}
	
}
