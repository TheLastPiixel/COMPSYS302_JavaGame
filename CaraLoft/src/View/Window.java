package View;

import javax.swing.JFrame;
import idk.Game;

public class Window extends JFrame{
	
	int windowWidth = 1440;
	int windowHeight = 900;
	
	private static final long serialVersionUID = -5540273607353580767L;

	public Window() {
		setTitle("Cara Loft");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new Game(windowWidth, windowHeight));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}