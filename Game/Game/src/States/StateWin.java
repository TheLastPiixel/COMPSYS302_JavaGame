package States;

import Main.Main;
import Main.Handler;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Graphics.Sprites;
import Graphics.TextureLoader;

public class StateWin extends StatesAbstract {

	public StateWin(Handler Handler) {
		super(Handler);
	}
	
	public Rectangle ResumeButton = new Rectangle(520, 300, 400, 150);
	public Rectangle ResumeButtonInside = new Rectangle(525, 305, 390, 140);
	public Rectangle QuitButton = new Rectangle(520, 530, 400, 150);
	public Rectangle QuitButtonInside = new Rectangle(525, 535, 390, 140);
	
	@Override
	public void Render(Graphics GraphicsObj) {
		Graphics2D Graphics2D = (Graphics2D) GraphicsObj;
		
//		System.out.println("We are rendering");
		//Font types
		Font Font1 = new Font("monospace", Font.BOLD, 70);
		Font Font2 = new Font("monospace", Font.BOLD, 50);
		Font Font3 = new Font("monospace", Font.BOLD, 20);
		
		//Draw background image and title
		GraphicsObj.setColor(Color.black);
		GraphicsObj.setFont(Font1);
		GraphicsObj.drawString("Mission Completed!", 420, 150);	
		
		//Draw layer 1 of button
		GraphicsObj.setColor(Color.black);
		Graphics2D.fill(ResumeButton);
		
		//Draw layer 2 of button
		GraphicsObj.setColor(Color.gray);
		Graphics2D.fill(ResumeButtonInside);
		
		GraphicsObj.setFont(Font2);
		GraphicsObj.setColor(Color.white);
		GraphicsObj.drawString("Exit", 670, 390);


	}

	@Override
	public void Tick() {
		if (Handler.GetMouseInput().Resume == true || Handler.GetKeyboardInput().Esc) {
			System.exit(1);
			Handler.GetMouseInput().Refresh();
		}
		
	
	}

}
