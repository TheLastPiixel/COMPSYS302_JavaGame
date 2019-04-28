package Tiles;

import Graphics.Sprites;

public class Bush extends Tiles{
	
	public Bush(int ID) {
		super(Sprites.Bush, ID);
	}
	
	@Override
	public boolean IsSolid() {
		return true;
	}
	
}
