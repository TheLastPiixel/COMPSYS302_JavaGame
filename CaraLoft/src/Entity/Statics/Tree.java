package Entity.Statics;

import Entity.Entity_Items.Tile;
import gfx.Assets;
import main.Handler;

import java.awt.*;

public class Tree extends StaticEntity {
	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tree, (int) (posX - handler.getGameCamera().getxOffset()),
				(int) (posY - handler.getGameCamera().getyOffset()), width, height, null);
	}
}
