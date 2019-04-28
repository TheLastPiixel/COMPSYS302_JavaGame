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

public class StateMenu extends StatesAbstract {

	public StateMenu(Handler Handler) {
		super(Handler);
	}
	
	public Rectangle PlayButton = new Rectangle(520, 250, 400, 150);
	public Rectangle PlayButtonInside = new Rectangle(525, 255, 390, 140);
	public Rectangle SettingsButton = new Rectangle(520, 430, 400, 150);
	public Rectangle SettingsButtonInside = new Rectangle(525, 435, 390, 140);
	public Rectangle ExitButton = new Rectangle(520, 610, 400, 150);
	public Rectangle ExitButtonInside = new Rectangle(525, 615, 390, 140);
	
	
	@Override
	public void Render(Graphics GraphicsObj) {
		Graphics2D Graphics2D = (Graphics2D) GraphicsObj;
		
		//Font types
		Font Font1 = new Font("Arial", Font.BOLD, 70);
		Font Font2 = new Font("Arial", Font.BOLD, 50);
		Font Font3 = new Font("Arial", Font.BOLD, 20);
		
		//Draw background image and title
		GraphicsObj.drawImage(TextureLoader.Image("/textures/Mansion.jpg"), 0, 0, null);
		GraphicsObj.setColor(Color.white);
		GraphicsObj.setFont(Font1);
		GraphicsObj.drawString("The Adventures of Cara Loft", 250, 150);	
		
		//Draw layer 1 of button
		GraphicsObj.setColor(Color.black);
		Graphics2D.fill(PlayButton);
		Graphics2D.fill(SettingsButton);
		Graphics2D.fill(ExitButton);
		
		//Draw layer 2 of button
		GraphicsObj.setColor(Color.gray);
		Graphics2D.fill(PlayButtonInside);
		Graphics2D.fill(SettingsButtonInside);
		Graphics2D.fill(ExitButtonInside);
		
		//Draw button names
		GraphicsObj.setColor(Color.white);
		GraphicsObj.setFont(Font2);
		GraphicsObj.drawString("Play", 670, 345);
		GraphicsObj.drawString("Settings", 630, 525);
		GraphicsObj.drawString("Exit", 670, 705);
		
		//Draw footer
		GraphicsObj.setFont(Font3);
		GraphicsObj.drawString("©C.P Studios", 20, 850);
		GraphicsObj.drawString("v1.0", 1370, 850);
	}

	@Override
	public void Tick() {
		if (Handler.GetMouseInput().Play == true) {
			Handler.GetMain().SetState(Handler.GetMain().StateGame);
			Handler.GetMouseInput().Refresh();
		}
		
		else if (Handler.GetMouseInput().Settings == true) {
			Handler.GetMain().SetState(Handler.GetMain().StateSettings);
			Handler.GetMouseInput().Refresh();
		}
	
		else if (Handler.GetMouseInput().Exit == true) {
			//Closes the game
			Handler.GetMouseInput().Refresh();
			Handler.GetMain().Stop();
			System.exit(1);
			
		}
	}

}
