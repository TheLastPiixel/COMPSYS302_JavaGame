package States;

import Main.Handler;
import Graphics.Sounds;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;

public class StateWin extends StatesAbstract {

	public StateWin(Handler Handler) {
		super(Handler);
	}
	
	public Rectangle QuitButton = new Rectangle(520, 400, 400, 150);
	public Rectangle QuitButtonInside = new Rectangle(525, 405, 390, 140);
	private int score;
	
	@Override
	public void Render(Graphics GraphicsObj) {
		Graphics2D Graphics2D = (Graphics2D) GraphicsObj;
		
		//Font types
		Font Font1 = new Font("monospace", Font.BOLD, 70);
		Font Font2 = new Font("monospace", Font.BOLD, 50);
		
		//Draw background image and title
		GraphicsObj.setColor(Color.black);
		GraphicsObj.setFont(Font1);
		GraphicsObj.drawString("Mission Completed!", 420, 150);	
		
		//Draw layer 1 of button
		GraphicsObj.setColor(Color.black);
		Graphics2D.fill(QuitButton);
		
		//Draw layer 2 of button
		GraphicsObj.setColor(Color.gray);
		Graphics2D.fill(QuitButtonInside);
		
		GraphicsObj.setFont(Font2);
		GraphicsObj.setColor(Color.white);
		GraphicsObj.drawString("Exit", 670, 490);

		//Score
		GraphicsObj.setColor(Color.black);
		GraphicsObj.drawString("Score: " + score , 610, 260);
	}

	@Override
	public void Tick() {
		score = (Handler.GetMain().getEliminated() * 1000 + (int)(300 - Handler.GetMain().getFinalTime() * 0.86));
		if (Handler.GetMouseInput().ExitEnd == true || Handler.GetKeyboardInput().Esc) {
			System.exit(1);
			Handler.GetMouseInput().Refresh();
			Sounds.playSound(new File("resources/sounds/back_style_2_001.wav"));
		}
		
	
	}

}
