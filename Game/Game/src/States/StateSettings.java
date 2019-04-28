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

public class StateSettings extends StatesAbstract {

	public StateSettings(Handler Handler) {
		super(Handler);
	}
	
	public Rectangle ReturnButton = new Rectangle(900, 700, 250, 100);
	public Rectangle ReturnButtonInside = new Rectangle(905, 705, 240, 90);
	
	@Override
	public void Render(Graphics GraphicsObj) {
		Graphics2D Graphics2D = (Graphics2D) GraphicsObj;
		
		//Font types
		Font Font1 = new Font("Arial", Font.BOLD, 70);
		Font Font2 = new Font("Arial", Font.BOLD, 30);
		Font Font3 = new Font("Arial", Font.BOLD, 20);
		
		//Draw background image and title
		GraphicsObj.drawImage(TextureLoader.Image("/textures/Mansion.jpg"), 0, 0, null);
		GraphicsObj.drawImage(TextureLoader.Image("/textures/Settings.png"), 300, 250, null);
		GraphicsObj.setColor(Color.white);
		GraphicsObj.setFont(Font1);
		GraphicsObj.drawString("Settings", 580, 150);	
		
		//Print Setting Menu Words
		GraphicsObj.setFont(Font2);
		GraphicsObj.drawString("Walk Forward", 410, 287);
		GraphicsObj.drawString("Walk Back", 435, 337);
		GraphicsObj.drawString("Walk Left", 440, 387);
		GraphicsObj.drawString("Walk Right", 430, 437);
		GraphicsObj.drawString("Attack", 460, 487);
		GraphicsObj.drawString("Pause/Exit", 430, 537);
		GraphicsObj.drawString("Fight Boss", 430, 587);
		GraphicsObj.drawString("Inventory", 435, 637);
		
		GraphicsObj.drawString("W", 925, 287);
		GraphicsObj.drawString("S", 930, 337);
		GraphicsObj.drawString("A", 930, 387);
		GraphicsObj.drawString("D", 930, 437);
		GraphicsObj.drawString("SPACE", 890, 487);
		GraphicsObj.drawString("ESC / P", 890, 537);
		GraphicsObj.drawString("PAGE UP", 890, 587);
		GraphicsObj.drawString("I", 935, 637);
		
		//Draw Return Button
		GraphicsObj.setColor(Color.black);
		Graphics2D.fill(ReturnButton);
		GraphicsObj.setColor(Color.gray);
		Graphics2D.fill(ReturnButtonInside);
		GraphicsObj.setColor(Color.white);
		GraphicsObj.drawString("Return", 975, 760);
		;
		
		//Draw footer
		GraphicsObj.setColor(Color.white);
		GraphicsObj.setFont(Font3);
		GraphicsObj.drawString("©C.P Studios", 20, 850);
		GraphicsObj.drawString("v1.0", 1370, 850);
	}

	@Override
	public void Tick() {
		if (Handler.GetMouseInput().Return == true || Handler.GetKeyboardInput().Esc == true) {
			Handler.GetMain().SetState(Handler.GetMain().StateMenu);
			Handler.GetMouseInput().Refresh();
		}
	}

}
