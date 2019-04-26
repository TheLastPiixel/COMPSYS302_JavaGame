package Input;

import Main.Handler;
import Main.Main;
import States.StateGame;
import States.StatesAbstract;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{
	
	private StatesAbstract StateGame;
	public boolean Play = false;
	public boolean Settings = false;
	public boolean Exit = false;
	
	public MouseInput() {
		
	}
	
	public void Refresh() {
		Play = false;
		Settings = false;
		Exit = false;
	}
	
	public void Tick() {

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
		
		
		if (PosX >= 520 & PosX <= 920) {
			if (PosY >= 610 & PosY <= 760) {
				//Press exit
				Exit = true;
				
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent Event) {
		
		
	}
	
	//GETTERS & SETTERS
	//Play 
	public boolean GetPlayStatus() {
		return Play;
	}
	public void SetPlayStatus(boolean Status) {
		Play = Status;
	}
	
	//Settings
	public boolean GetSettingsStatus() {
		return Settings;
	}
	public void SetSettingsStatus(boolean Status) {
		Settings = Status;
	}
	
	//Exit
	public boolean GetExitStatus() {
		return Exit;
	}
	public void SetExiteStatus(boolean Status) {
		Exit = Status;
	}
}
