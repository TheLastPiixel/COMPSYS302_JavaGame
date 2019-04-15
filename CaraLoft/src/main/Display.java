package main;

import javax.swing.*;
import java.awt.*;

public class Display {

	private JFrame frame; // this is the frame
	private Canvas canvas; // allows us to draw graphics yay
	private static int width, height; // width and height to use in the frame settings
	private String title; // title of the window

	public Display(String title, int width, int height) { // takes the inputted title width and height
		this.title = title;
		this.width = width;
		this.height = height;

		createDisplay(); // calls the below method
	}

	private void createDisplay() {
		frame = new JFrame(title); // actually initializing the jframe
		frame.setSize(width, height); // sets width and height
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // makes it exit
		frame.setResizable(false); // makes it not resizable
		frame.setLocationRelativeTo(null); // makes it at the centre
		frame.setVisible(true);

		canvas = new Canvas(); // calls canvas method to create one
		canvas.setPreferredSize(new Dimension(width, height)); // sets height using new dimension function
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false); // makes our jframe have all the focus

		frame.add(canvas); // adds our canvas to the jframe
		frame.pack(); // sets the canvas up so that we can see all of it in the frame
	}

	public Canvas getCanvas() { // allows us to access our canvas and display whatever we want in other classes
		return canvas;
	}

	public JFrame getFrame() {
		return frame; // so we can access this frame outside of the display class
	}
}
