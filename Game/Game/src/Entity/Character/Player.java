package Entity.Character;

import java.awt.*;
import java.awt.image.BufferedImage;

import Entity.Entity;
import Graphics.Sprites;
import Main.Handler;
import Graphics.Animation;

public class Player extends Character {
	// Animations
	private Animation rightCaraAnim, leftCaraAnim, downCaraAnim, upCaraAnim, lastAnim;
	private int i = 0;
	private int CurrentDirection;
	private int CurrentHealth;
	private boolean AttackButton;
	private long AttackCooldown;
	private long LastAttack;
	private long Clock;
	private Boolean hasKey = false;
	private Rectangle AttackArea = new Rectangle();
	private int Score;
	private int Eliminated = 0;

	public Player(Handler Handler,Identifier id, float PosX, float PosY) {
		super(Handler, DefaultWidth, DefaultHeight, id, PosX, PosY);

		colBoundary.x = 20;
		colBoundary.y = 32;
		colBoundary.width = 20;
		colBoundary.height = 24;
		
		AttackCooldown = 500;
		CurrentHealth = Health;

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
		
		
		Attack();
	}
	
	private void Attack() {
		if ((System.currentTimeMillis() - LastAttack) > AttackCooldown) {
		
			CurrentDirection = Handler.GetKeyboardInput().GetCurrentDirection();
			Rectangle CollisionBound = getCollisionBounds(0, 0);
	
			AttackButton = Handler.GetKeyboardInput().Space;
			int AttackRange = 32;
			AttackArea.width = AttackRange;
			AttackArea.height = AttackRange;
			
			if(CurrentDirection == 0) { //W
				AttackArea.x = CollisionBound.x + (CollisionBound.width / 2) - (AttackRange / 2);
				AttackArea.y = CollisionBound.y - AttackRange;
			}
			else if(CurrentDirection == 1) { //A
				AttackArea.x = CollisionBound.x - AttackRange;
				AttackArea.y = CollisionBound.y + (CollisionBound.height / 2) - (AttackRange / 2);
			}
			else if(CurrentDirection == 2) { //S
				AttackArea.x = CollisionBound.x + (CollisionBound.width / 2) - (AttackRange / 2);
				AttackArea.y = CollisionBound.y - CollisionBound.height + 48;
			}
			else if(CurrentDirection == 3) { //D
				AttackArea.x = CollisionBound.x - CollisionBound.width  + 40;
				AttackArea.y = CollisionBound.y + (CollisionBound.height / 2) - (AttackRange / 2);
			}
			else {
				return;
			}
			
			for(int i = 0; i < Handler.getEntities().size(); i++){
				//Checks if enemy is in player's attack range
				if(Handler.getEntities().equals(this)) {
					return;
				} 
				else if(Handler.getEntities().get(i).getCollisionBounds(0, 0).intersects(AttackArea) & AttackButton == true) {
					Handler.getEntities().get(i).Damage(35);
					LastAttack = System.currentTimeMillis();
					return;
				}
			}
		}
	}

	@Override
	public void Render(Graphics GraphicsObj) {
		//Hard coded spritesheet of 4 column, doesnt really work
		GraphicsObj.setColor(Color.RED);
		GraphicsObj.drawRect((int)(AttackArea.x - Handler.GetCamera().GetOffsetX()), (int)(AttackArea.y - Handler.GetCamera().GetOffsetY()), (int)AttackArea.width, (int)AttackArea.height);
		GraphicsObj.drawImage(getCurrentAnimationFrame(), (int)(PosX - Handler.GetCamera().GetOffsetX()), (int)(PosY - Handler.GetCamera().GetOffsetY()), Width, Height, null);
	
		if (Health <= CurrentHealth) {
			GraphicsObj.setColor(Color.RED);
//			GraphicsObj.fillRect(0, 0, 1440, 900);
			CurrentHealth = Health;
		}
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

	public Boolean getHasKey() {
		return hasKey;
	}

	public void setHasKey(Boolean hasKey) {
		this.hasKey = hasKey;
	}
	@Override
	public void Dead() {
		Handler.GetMain().SetState(Handler.GetMain().StateLose);
	}
	
	public int GetEliminated() {
		return Eliminated;
	}

	public void SetEliminated(int j) {
		this.Eliminated = j;
		
	}

}
