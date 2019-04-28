package Main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {

	private JFrame Frame;
	public Canvas Canvas;
	
	//Screen pixel width & height
	private int width;
	private int height; 
	private String title;
	
	public Display(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		
		createDisplay();
	}
	
	private void createDisplay() {
		Frame = new JFrame(title); //Sets title of frame on top bar
		Frame.setSize(new Dimension(width, height)); //Sets the size of the screen frame
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Ends task when window is closed
		
		Frame.setLocationRelativeTo(null); //Game starts in centre of screen
		Frame.setResizable(false); //Disable's user from resizing menu
		Frame.setVisible(true); //Allows user to view the frame
		Frame.setPreferredSize(new Dimension(width, height));
		Frame.setMaximumSize(new Dimension(width, height));
		Frame.setMinimumSize(new Dimension(width, height));
		
		Canvas = new Canvas();
		Canvas.setFocusable(false);
		Canvas.setPreferredSize(new Dimension(width, height));
		Canvas.setMaximumSize(new Dimension(width, height));
		Canvas.setMinimumSize(new Dimension(width, height));
		
		Frame.add(Canvas);
		Frame.pack(); //Assures the canvas size = frame size
	}
	
	//GETTERS & SETTERS
	//Frame
	public JFrame GetFrame() {
		return Frame;
	}
	
	//Canvas
	public Canvas GetCanvas() {
		return Canvas;
	}
	
}
