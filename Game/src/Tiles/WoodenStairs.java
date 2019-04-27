package Tiles;

import Graphics.Sprites;

public class WoodenStairs extends Tiles{

	public WoodenStairs(int ID) {
		super(Sprites.WoodenStairs, ID);
	}
	
	@Override
	public boolean IsSolid() {
		return false;
	}

}
