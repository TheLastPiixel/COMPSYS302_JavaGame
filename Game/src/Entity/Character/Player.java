package Entity.Character;

import java.awt.*;
import java.awt.image.BufferedImage;
import Graphics.Sprites;
import Main.Handler;
import Graphics.Animation;

public class Player extends Character {
	// Animations
	private Animation rightCaraAnim, leftCaraAnim, downCaraAnim, upCaraAnim, lastAnim;
	private int i = 0;

	public Player(Handler Handler,Identifier id, float PosX, float PosY) {
		super(Handler, DefaultWidth, DefaultHeight, id, PosX, PosY);

		colBoundary.x = 20;
		colBoundary.y = 32;
		colBoundary.width = 20;
		colBoundary.height = 24;

		//Animations init
		rightCaraAnim = new Animation(100, Sprites.CaraLoftRight);
		leftCaraAnim = new Animation(100, Sprites.CaraLoftLeft);
		downCaraAnim = new Animation(100, Sprites.CaraLoftFront);
		upCaraAnim = new Animation(100, Sprites.CaraLoftBack);
		lastAnim = downCaraAnim;

	}

	private void GetKeyboardInput() {
		XSpeed = 0;
		YSpeed = 0;


		if(Handler.GetKeyboardInput().W == true) {
			YSpeed = -Speed * SpeedFactor;
		}
		if(Handler.GetKeyboardInput().A == true) {
			XSpeed = -Speed * SpeedFactor;
		}
		if(Handler.GetKeyboardInput().S == true) {
			YSpeed = Speed * SpeedFactor;
		}
		if(Handler.GetKeyboardInput().D == true) {
			XSpeed = Speed * SpeedFactor;
		}

	}

	@Override
	public void Tick() {
		//Animations
		rightCaraAnim.tick();
		leftCaraAnim.tick();
		downCaraAnim.tick();
		upCaraAnim.tick();

		GetKeyboardInput();
		MovementSpeed();
		Handler.GetCamera().Centre(this);
	}

	@Override
	public void Render(Graphics GraphicsObj) {
		//Hard coded spritesheet of 4 column, doesnt really work
		//TODO
		GraphicsObj.drawImage(getCurrentAnimationFrame(), (int)(PosX - Handler.GetCamera().GetOffsetX()), (int)(PosY - Handler.GetCamera().GetOffsetY()), Width, Height, null);
		GraphicsObj.setColor(Color.RED);
		GraphicsObj.fillRect((int)PosX + colBoundary.x, (int)PosY + colBoundary.y, colBoundary.width, colBoundary.height);
	}

	@Override
	public Identifier getId() {
		return id;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)PosX, (int)PosY, Width, Height);
	}

	private BufferedImage getCurrentAnimationFrame(){ // make the correct animation come up depending on the direction

		if(XSpeed < 0){
			lastAnim = leftCaraAnim;
			return leftCaraAnim.getCurrentFrame();
		}else if(XSpeed > 0){
			lastAnim = rightCaraAnim;
			return rightCaraAnim.getCurrentFrame();
		}else if(YSpeed < 0){
			lastAnim = upCaraAnim;
			return upCaraAnim.getCurrentFrame();
		}else if(YSpeed > 0){
			lastAnim = downCaraAnim;
			return downCaraAnim.getCurrentFrame();
		}else{
			return lastAnim.getFirstFrame();
		}
	}
}
