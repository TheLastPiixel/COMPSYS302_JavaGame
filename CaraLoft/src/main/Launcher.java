package main;

public class Launcher {

	public static void main(String[] args) { // first thing called ever

		Game game = new Game("Cara Loft", 1024, 768); // will call the game function
		game.start(); // calls the start method on this game instance/object
	}

}
