package Tiles;

import Graphics.Sprites;

public class Tree extends Tiles{
	
	public Tree(int ID) {
		super(Sprites.Tree, ID);
	}
	
	@Override
	public boolean IsSolid() {
		return true;
	}
	
}
