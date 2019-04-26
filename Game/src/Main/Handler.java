package Main;

import Graphics.Camera;
import Input.KeyboardInput;
import Input.MouseInput;
import Rooms.Rooms;

public class Handler {
	
	private Rooms Room;
	private Main Game;
	
	
	public Handler(Main Game) {
		this.Game = Game;
	}

	//GETTERS & SETTERS
	//Rooms
	public Rooms GetRoom() {
		return Room;
	}
	public void SetRoom(Rooms Room) {
		this.Room = Room;
	}
	
	//Main
	public Main GetMain() {
		return Game;
	}
	public void SetGame(Main Game) {
		this.Game = Game;
	}
	
	//Width
	public int GetWidth() {
		return Game.GetWidth();
	}
	
	//Height
	public int GetHeight() {
		return Game.GetHeight();
	}
	
	//MouseInput
	public MouseInput GetMouseInput() {
		return Game.getMouseInput();
	}

	//KeyboardInput
	public KeyboardInput GetKeyboardInput() {
		return Game.GetKeyboardInput();
	}
	
	//Camera
	public Camera GetCamera() {
		return Game.GetCamera();
	}

	public Rooms getRoom() {
		return Room;
	}

	public void setRoom(Rooms room) {
		Room = room;
	}
}
