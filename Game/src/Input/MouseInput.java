package Input;

import Main.Main;
import States.StateGame;
import States.StatesAbstract;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{
	
	private StatesAbstract StateGame;
	private boolean Play;
	private boolean Settings;
	private boolean Exit;
	
	public void Tick() {
		if (Play == true) {
			Main.SetState(StateGame);
		}
		
		if (Settings == true) {
			System.out.println("Settings");
		}
		
		if (Play == true) {
			System.exit(1);
		}
	}

	@Override
	public void mouseClicked(MouseEvent Event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent Event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent Event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent Event) {
		int PosX = Event.getX();
		int PosY = Event.getY();
		System.out.println("Clicked");
		
//		public Rectangle PlayButton = new Rectangle(520, 250, 400, 150);
//		public Rectangle SettingsButton = new Rectangle(520, 430, 400, 150);
//		public Rectangle ExitButton = new Rectangle(520, 610, 400, 150);
		
		if (PosX >= 520 & PosX <= 920) {
			if (PosY >= 250 & PosY <= 400) {
				//Press play
				Play = true;
			}
		}
		
		if (PosX >= 520 & PosX <= 920) {
			if (PosY >= 430 & PosY <= 580) {
				Settings = true;
				//Press settings
			}
		}
		Settings = false;
		
		if (PosX >= 520 & PosX <= 920) {
			if (PosY >= 610 & PosY <= 760) {
				//Press exit
				Exit = true;
				
			}
		}
		Exit = false;
	}

	@Override
	public void mouseReleased(MouseEvent Event) {
		
		
	}

}
