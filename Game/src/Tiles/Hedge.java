package Tiles;

import Graphics.Sprites;

public class Hedge extends Tiles{

	public Hedge(int ID) {
		super(Sprites.Hedge, ID);
	}
	
	@Override
	public boolean IsSolid() {
		return true;
	}

}
