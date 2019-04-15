package Entity.Entity_Items;

import gfx.Assets;

public class StoneTile extends Tile {
	public StoneTile(int id) {
		super(Assets.stone, id);
	}

	@Override
	public boolean isSolid() { // this will override the default return of being not solid
		return true;
	}
}
