package main.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils {

	public static String loadFileAsString(String path) {
		StringBuilder builder = new StringBuilder();

		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while ((line = br.readLine()) != null) // while not on the last line
				builder.append(line + "\n");

			br.close(); // close file
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString(); // converts to a string which will be our loaded file
	}

	public static int parseInt(String number) {
		try {
			return Integer.parseInt(number); // try to convert string to int
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
