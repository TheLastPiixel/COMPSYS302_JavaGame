package Entity.Character;

import Entity.Entity;
import Main.Main;
import Main.Handler;

public abstract class Character extends Entity{

	protected int Health;
	protected float SpeedFactor;
	protected float Speed;
	protected float XSpeed;
	protected float YSpeed;
	protected int Mode;
	public static int DefaultWidth = 64;
	public static int DefaultHeight = 64;
	public static int DefaultHealth = 100;
	public static int DefaultSpeed = 3;
	
	
	public Character(Handler Handler, int Width, int Height, float PosX, float PosY) {
		super(Handler, Width, Height, PosX, PosY);
		Health = DefaultHealth;
		Mode = 1;
		//Speed variables
		XSpeed = 0;
		YSpeed = 0;
		Speed = DefaultSpeed;
		SpeedFactor = 1;
		

	}
	
	public void MovementSpeed() {
		PosX = PosX + XSpeed;
		PosY = PosY + YSpeed;
	}

	//Health Getter & Setter
	public int GetHealth() {
		return Health;
	}
	public void SetHealth(int Health) {
		Health = Health;
	}

	//SpeedFactor Getter & Setter
	public float GetSpeedFactor() {
		return SpeedFactor;
	}
	public void SetSpeedFactor(float SpeedFactor) {
		SpeedFactor = SpeedFactor;
	}

	//Speed Getter & Setter
	public float GetSpeed() {
		return Speed;
	}
	public void SetSpeed(float Speed) {
		Speed = Speed;
	}

	//Mode Getter & Setter
	public int GetMode() {
		return Mode;
	}
	public void SetMode(int Mode) {
		Mode =Mode;
	}
	
	//XSpeed Getter & Setter
	public float GetXSpeed() {
		return XSpeed;
	}
	public void SetXSpeed(float XSpeed) {
		XSpeed = XSpeed;
	}

	//YSpeed Getter & Setter
	public float GetYSpeed() {
		return YSpeed;
	}
	public void SetYSpeed(float YSpeed) {
		YSpeed = YSpeed;
	}

}
