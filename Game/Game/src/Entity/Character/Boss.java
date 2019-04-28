package Entity.Character;
import Main.Handler;
import Rooms.Rooms;
import States.StateGame;
import Entity.Character.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import Entity.Entity;
import Graphics.Sprites;
import Main.Handler;
import Graphics.Animation;

public class Boss extends Character {
    // Animations
    private Animation enemyAnimRight, enemyAnimLeft, enemyAnimDown, enemyAnimUp, enemyLastAnim;
    private int animTime = 0;
    private Random random = new Random();
    private Player player;
    private int aggroDistance = 200;
    private Rooms room;
    private int CurrentDirection;
    
    private Rectangle AttackArea = new Rectangle();
	private int AttackRange;
	private long AttackCooldown;
	private long LastAttack;
	private long Clock;

    public Boss(Handler Handler,Identifier id, float PosX, float PosY, Player player) {
        super(Handler, DefaultWidth, DefaultHeight, id, PosX, PosY);

        this.player = player;
        colBoundary.x = 22;
        colBoundary.y = 32;
        colBoundary.width = 20;
        colBoundary.height = 24;
        
        AttackCooldown = 800;

        //Animations init
        enemyAnimRight = new Animation(100, Sprites.enemy_right);
        enemyAnimLeft = new Animation(100, Sprites.enemy_left);
        enemyAnimDown = new Animation(100, Sprites.enemy_down);
        enemyAnimUp = new Animation(100, Sprites.enemy_up);
        enemyLastAnim = enemyAnimDown;

    }

    @Override
    public void Tick() {
        //Animations
        enemyAnimRight.tick();
        enemyAnimLeft.tick();
        enemyAnimDown.tick();
        enemyAnimUp.tick();

        getAI(player);
        MovementSpeed();
        Attack();
    }
    
	private void Attack() {
		if ((System.currentTimeMillis() - LastAttack) > AttackCooldown) {

			Rectangle CollisionBound = getCollisionBounds(0, 0);
	
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
				else if(Handler.getEntities().get(i).getCollisionBounds(0, 0).intersects(AttackArea)) {
					Handler.getEntities().get(i).Damage(25);
					player.SetStunned(true);
					LastAttack = System.currentTimeMillis();
					return;
				}
			}
		}
	}


    @Override
    public void Render(Graphics GraphicsObj) {
        GraphicsObj.drawImage(getCurrentAnimationFrame(), (int) (PosX - Handler.GetCamera().GetOffsetX()),
                (int)(PosY - Handler.GetCamera().GetOffsetY()), Width, Height, null);
        GraphicsObj.setColor(Color.GREEN);
        GraphicsObj.drawRect((int)(AttackArea.x - Handler.GetCamera().GetOffsetX()) , (int)(AttackArea.y - Handler.GetCamera().GetOffsetY()), (int)AttackArea.width,(int) AttackArea.height);

    }

    private void getAI(Player player) {

        double xDiff = (PosX + colBoundary.x / 2) - (player.GetPosX() + 8);
        double yDiff = (PosY + colBoundary.y / 2) - (player.GetPosY() + 16);
        double totalDiff = Math.sqrt(((xDiff * xDiff) + (yDiff * yDiff)));
        if(totalDiff < aggroDistance && totalDiff > attackRange) {
            if (PosX < player.GetPosX()) {
                XSpeed = 2;
                CurrentDirection = 3;
            } 
            else if (PosX > player.GetPosX()) {
                XSpeed = -2;
                CurrentDirection = 1;
            } 
            else {
                XSpeed = 0;
            }
            if (PosY < player.GetPosY()) {
                YSpeed = 2;
                CurrentDirection = 2;
            } 
            else if (PosY > player.GetPosY()) {
                YSpeed = -2;
                CurrentDirection = 0;
            } 
            else {
                YSpeed = 0;
            }
            animTime = 0;
        }else if (totalDiff > aggroDistance){
            animTime++;
            if(animTime == 60) {

                if (new Random().nextInt(4) == 0) {
                    XSpeed = 1;
                }
                if (new Random().nextInt(4) == 1) {
                    XSpeed = -1;
                }
                if (new Random().nextInt(4) == 2) {
                    YSpeed = 1;
                }
                if (new Random().nextInt(4) == 3) {
                    YSpeed = -1;
                }
                animTime = 0;
            }
        }else if (totalDiff < attackRange){
            XSpeed = 0;
            YSpeed = 0;
            animTime = 0;
        }
    }
    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)PosX + colBoundary.x, (int)PosY - colBoundary.y, colBoundary.width, colBoundary.height);
    }

    private BufferedImage getCurrentAnimationFrame(){ // make the correct animation come up depending on the direction
        if(XSpeed < 0){
            enemyLastAnim = enemyAnimLeft;
            return enemyAnimLeft.getCurrentFrame();
        }else if(XSpeed > 0){
            enemyLastAnim = enemyAnimRight;
            return enemyAnimRight.getCurrentFrame();
        }else if(YSpeed < 0){
            enemyLastAnim = enemyAnimUp;
            return enemyAnimUp.getCurrentFrame();
        }else if(YSpeed > 0){
            enemyLastAnim = enemyAnimDown;
            return enemyAnimDown.getCurrentFrame();
        }else{
            return enemyLastAnim.getFirstFrame();
        }
    }

	@Override
	public void Dead() {
		Handler.GetMain().GetState().SetEliminated(Handler.GetMain().GetState().GetEliminated() + 0);
		Handler.GetMain().SetState(Handler.GetMain().StateWin);
	}
}
