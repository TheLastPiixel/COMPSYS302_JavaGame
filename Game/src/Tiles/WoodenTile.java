package Tiles;

import Graphics.Sprites;

public class WoodenTile extends Tiles{

	public WoodenTile(int ID) {
		super(Sprites.WoodenTile, ID);
	}
	
	@Override
	public boolean IsSolid() {
		return false;
	}

}
