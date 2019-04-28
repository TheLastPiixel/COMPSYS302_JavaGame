package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Helpers {
		
	//Gets the player's objective
	public static String GetObjective(int Room) {
		if (Room == 0) {
			return "Eliminate All Enemies Then Enter Wain Manor";
		}
		else if (Room == 1) {
			return "Eliminate Enemies, Get the key & enter Spruce's Office";
		}
		else if (Room == 2) {
			return "Defeat Spruce Wain!";
		}
		else {
			return "No Current Objectives";
		}
	}
	
	//Reads the txt file and converts it to a string
	public static String ReadFileToString(String FilePath) {
		StringBuilder StringBuilder = new StringBuilder();
		
		try {
			BufferedReader Reader = new BufferedReader(new FileReader(FilePath));
			String CurrentLine;
			
			//Checks that the current line isnt empty
			while((CurrentLine = Reader.readLine()) != null) {
				StringBuilder.append(CurrentLine + "\n");
			}
			
			Reader.close();
			
		}
		catch(IOException Error) {
			Error.printStackTrace();
		}
		
		return StringBuilder.toString();
	}
	
	
	public static int StringToInt(String String) {
		try {
			return Integer.parseInt(String);
		}
		catch(NumberFormatException Error) {
			Error.printStackTrace();
			return 0;
		}
	}
	
}
