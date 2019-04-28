package Entity.Character;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import Graphics.Sprites;
import Main.Handler;
import Graphics.Animation;
import Graphics.Sounds;

public class Player extends Character {
	// Animations
	private Animation rightCaraAnim, leftCaraAnim, downCaraAnim, upCaraAnim, lastAnim, attackCaraAnim, hurtCaraAnim;
	private File Attack2;
	private int CurrentDirection;
	private int CurrentHealth;
	private boolean AttackButton;
	private boolean Stunned = false;
	private boolean CurrentlyStunned = false;
	private long AttackCooldown;
	private long LastAttack;
	private Boolean hasKey = false;
	private Rectangle AttackArea = new Rectangle();
	private int Eliminated = 0;
	private int damageTimer = 0;

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
		attackCaraAnim = new Animation(100, Sprites.CaraLoftAttack);
		hurtCaraAnim = new Animation(100, Sprites.CaraLoftHurt);
		lastAnim = downCaraAnim;
		Attack2 = new File("resources/sounds/Jab.wav");
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
		
		//Checks and sets player to stunned
		if (Stunned == true) {
			AttackCooldown = 5000;
			LastAttack = System.currentTimeMillis();
			Stunned = false;
			CurrentlyStunned = true;
		}
		
		Attack();
	}
	
	private void Attack() {
		if ((System.currentTimeMillis() - LastAttack) > AttackCooldown) {
			//Checks if player's stunned time is over
			if (CurrentlyStunned == true) {
				AttackCooldown = 500;
				Stunned = false;
				CurrentlyStunned = false;
			}
			
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
					Sounds.playSound(Attack2);
					return;
				}
			}
		}
	}

	@Override
	public void Render(Graphics GraphicsObj) {
		//Hard coded spritesheet of 4 column, doesnt really work
		GraphicsObj.drawImage(getCurrentAnimationFrame(), (int)(PosX - Handler.GetCamera().GetOffsetX()), (int)(PosY - Handler.GetCamera().GetOffsetY()), Width, Height, null);

		if (Health < CurrentHealth) {
			 CurrentHealth = Health;
			damageTimer = 60;
		}
		if(damageTimer > 0){
			damageTimer--;
			//GraphicsObj.drawImage(getCurrentAnimationFrame(), (int)(PosX - Handler.GetCamera().GetOffsetX()), (int)(PosY - Handler.GetCamera().GetOffsetY()), Width, Height, null);
			//GraphicsObj.setColor(Color.RED);
			//GraphicsObj.fillRect(0, 0, Handler.GetWidth(), Handler.GetHeight());
		}
	}

	private BufferedImage getCurrentAnimationFrame(){ // make the correct animation come up depending on the direction
		if(AttackButton == true){
			return attackCaraAnim.getCurrentFrame();
		} else if(damageTimer > 0){
			return hurtCaraAnim.getCurrentFrame();
		}else{
			if(XSpeed < 0) {
				lastAnim = leftCaraAnim;
				return leftCaraAnim.getCurrentFrame();
			} else if (XSpeed > 0) {
				lastAnim = rightCaraAnim;
				return rightCaraAnim.getCurrentFrame();
			} else if (YSpeed < 0) {
				lastAnim = upCaraAnim;
				return upCaraAnim.getCurrentFrame();
			} else if (YSpeed > 0) {
				lastAnim = downCaraAnim;
				return downCaraAnim.getCurrentFrame();
			} else {
				return lastAnim.getFirstFrame();
			}
		}
	}

	//GETTERS & SETTERS
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
	
	public void SetStunned (boolean Stunned) {
		this.Stunned = Stunned;
	}
	
	public boolean GetStunned() {
		return Stunned;
	}
	
	public boolean GetCurrentlyStunned() {
		return CurrentlyStunned;
	}
	
	@Override
	public Identifier getId() {
		return id;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)PosX, (int)PosY, Width, Height);
	}

}
