package States;

import Main.Main;
import Main.Handler;
import States.StatesAbstract;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Entity.Character.Player;
import Graphics.Sprites;
import Graphics.TextureLoader;

public class StateLose extends StatesAbstract {

	public Rectangle ResumeButton = new Rectangle(520, 300, 400, 150);
	public Rectangle ResumeButtonInside = new Rectangle(525, 305, 390, 140);
	public Rectangle QuitButton = new Rectangle(520, 530, 400, 150);
	public Rectangle QuitButtonInside = new Rectangle(525, 535, 390, 140);
	private int score;
	public StateLose(Handler Handler) {
		super(Handler);
	}
	

	
	@Override
	public void Render(Graphics GraphicsObj) {
		Graphics2D Graphics2D = (Graphics2D) GraphicsObj;
		
		//Font types
		Font Font1 = new Font("monospace", Font.BOLD, 70);
		Font Font2 = new Font("monospace", Font.BOLD, 50);
		Font Font3 = new Font("monospace", Font.BOLD, 20);
		
		//Draw background image and title
		GraphicsObj.setColor(Color.black);
		GraphicsObj.setFont(Font1);
		GraphicsObj.drawString("Game Over!", 530, 150);	
		
		//Draw layer 1 of button
		GraphicsObj.setColor(Color.black);
		Graphics2D.fill(ResumeButton);
		
		//Draw layer 2 of button
		GraphicsObj.setColor(Color.gray);
		Graphics2D.fill(ResumeButtonInside);
		
		//Button title
		GraphicsObj.setFont(Font2);
		GraphicsObj.setColor(Color.white);
		GraphicsObj.drawString("Exit", 670, 390);

		//Score
		GraphicsObj.setColor(Color.BLACK);
		GraphicsObj.drawString("Score: " + score , 560, 250);
	}

	@Override
	public void Tick() {
		score = Handler.GetMain().getEliminated() * 1000;
		if (Handler.GetMouseInput().Resume == true || Handler.GetKeyboardInput().Esc) {
			System.exit(1);
			Handler.GetMouseInput().Refresh();
		}
	}

}
