package Entity.Character;
import Main.Handler;
import Rooms.Rooms;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import Entity.Entity;
import Graphics.Sprites;
import Main.Handler;
import Graphics.Animation;

public class Enemy extends Character {
    // Animations
    private Animation enemyAnimRight, enemyAnimLeft, enemyAnimDown, enemyAnimUp, enemyLastAnim;
    private int animTime = 0;
    private Random random = new Random();
    private Player player;
    private int aggroDistance = 200;
    private Rooms room;

    public Enemy(Handler Handler,Identifier id, float PosX, float PosY, Player player) {
        super(Handler, DefaultWidth, DefaultHeight, id, PosX, PosY);

        this.player = player;
        colBoundary.x = 12;
        colBoundary.y = 32;
        colBoundary.width = 38;
        colBoundary.height = 24;

        //Animations init
        enemyAnimRight = new Animation(100, Sprites.enemy_right);
        enemyAnimLeft = new Animation(100, Sprites.enemy_left);
        enemyAnimDown = new Animation(100, Sprites.enemy_down);
        enemyAnimUp = new Animation(100, Sprites.enemy_up);
        enemyLastAnim = enemyAnimDown;

    }

    private void collision(){
        for(int i = 0; i < Handler.objects.size(); i++) {
            Entity tempObject = Handler.objects.get(i);
            if (tempObject.getId() == Identifier.Wall) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    PosX += XSpeed * -1;
                    PosY += YSpeed * -1;
                }
            }
        }
    }

    @Override
    public void Tick() {
        //Animations
        enemyAnimRight.tick();
        enemyAnimLeft.tick();
        enemyAnimDown.tick();
        enemyAnimUp.tick();

        getAI(player);
        collision();
        moveX();
        moveY();
    }

    @Override
    public void Render(Graphics GraphicsObj) {
        GraphicsObj.drawImage(getCurrentAnimationFrame(), (int) (PosX - Handler.GetCamera().GetOffsetX()),
                (int)(PosY - Handler.GetCamera().GetOffsetY()), Width, Height, null);
    }

    private void getAI(Player player) {

        double xDiff = (PosX + colBoundary.x / 2) - (player.GetPosX() + 8);
        double yDiff = (PosY + colBoundary.y / 2) - (player.GetPosY() + 16);
        double totalDiff = Math.sqrt(((xDiff * xDiff) + (yDiff * yDiff)));
        if(totalDiff < aggroDistance && totalDiff > attackRange) {
            if (PosX < player.GetPosX()) {
                XSpeed = 2;
            } else if (PosX > player.GetPosX()) {
                XSpeed = -2;
            } else {
                XSpeed = 0;
            }
            if (PosY < player.GetPosY()) {
                YSpeed = 2;
            } else if (PosY > player.GetPosY()) {
                YSpeed = -2;
            } else {
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
        return new Rectangle((int)PosX, (int)PosY, Width, Height);
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
}