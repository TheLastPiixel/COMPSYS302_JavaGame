package Tiles;

import Graphics.Sprites;

public class Black extends Tiles{

	public Black(int ID) {
		super(Sprites.Black, ID);
	}
	
	@Override
	public boolean IsSolid() {
		return true;
	}

}
