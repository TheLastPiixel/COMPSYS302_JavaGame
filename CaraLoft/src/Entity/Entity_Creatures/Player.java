package Entity.Entity_Creatures;

import gfx.Animation;
import gfx.Assets;
import main.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature { // holds textures, input...

	// Animations
	private Animation animRight, animLeft, animDown, animUp;

	public Player(Handler handler, float posX, float posY) {
		super(handler, posX, posY, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT); // passing them
																										// in

		bounds.x = 12;
		bounds.y = 33;
		bounds.width = 38;
		bounds.height = 24;

		// Animations init
		animRight = new Animation(100, Assets.player_right);
		animLeft = new Animation(100, Assets.player_left);
		animDown = new Animation(100, Assets.player_down);
		animUp = new Animation(100, Assets.player_up);

	}

	@Override
	public void tick() {

		// Animations
		animRight.tick();
		animLeft.tick();
		animDown.tick();
		animUp.tick();

		// Movement
		getInput(); // initializes everything to the correct direction at the start
		move();
		handler.getGameCamera().centreOnEntity(this); // centres camera on this entity
	}

	private void getInput() {
		xMove = 0;
		yMove = 0;

		if (handler.getKeyManager().up) // if the up button is being pressed
			yMove = -speed;
		if (handler.getKeyManager().down) // if the up button is being pressed
			yMove = speed;
		if (handler.getKeyManager().left) // if the up button is being pressed
			xMove = -speed;
		if (handler.getKeyManager().right) // if the up button is being pressed
			xMove = speed;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (posX - handler.getGameCamera().getxOffset()),
				(int) (posY - handler.getGameCamera().getyOffset()), width, height, null);
	}

	private BufferedImage getCurrentAnimationFrame() { // make the correct animation come up depending on the direction

		if (xMove < 0) {
			return animLeft.getCurrentFrame();
		} else if (xMove > 0) {
			return animRight.getCurrentFrame();
		} else if (yMove < 0) {
			return animUp.getCurrentFrame();
		} else if (yMove > 0) {
			return animDown.getCurrentFrame();
		} else {
			return Assets.player_down[0];
		}
	}
}
