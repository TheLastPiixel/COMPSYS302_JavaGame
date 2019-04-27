package Main;

import Entity.Entity;
import Graphics.Camera;
import Input.KeyboardInput;
import Input.MouseInput;
import Rooms.Rooms;

import java.awt.*;
import java.util.ArrayList;

public class Handler {
	public ArrayList<Entity> objects = new ArrayList<Entity>();
	
	private Rooms Room;
	private Main Game;
	public ArrayList<Entity> getEntities() {
		return objects;
	}
	
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

	public void addObjects(Entity entity){
		objects.add(entity);
	}
}
