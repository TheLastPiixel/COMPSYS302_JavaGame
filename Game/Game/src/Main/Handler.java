package Main;

import Entity.Entity;
import Entity.Character.Identifier;
import Entity.Character.Player;
import Graphics.Camera;
import Input.KeyboardInput;
import Input.MouseInput;
import Rooms.Rooms;
import States.StateGame;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Handler {
	public ArrayList<Entity> objects = new ArrayList<Entity>();
	private Rooms Room;
	private Main Game;
	
	public ArrayList<Entity> getEntities() {
		return objects;
	}
	
	public Handler(Main Game) {
		this.Game = Game;
		objects = new ArrayList<Entity>();
	}
	public void clearEntities(){
		for(int i = 0; i < this.getEntities().size(); i++ ){
			if (this.getEntities().get(i).getId() != Identifier.Player){
				this.removeEntity(this.getEntities().get(i));
			}
		}
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

	public void addEntity(Entity entity){
		objects.add(entity);
	}
	public void removeEntity(Entity entity){
		objects.remove(entity);
	}

	
}
