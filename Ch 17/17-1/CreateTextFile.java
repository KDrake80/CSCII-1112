/*
 * Kevin Drake
 * 1/18/2022
 * This Program creates a file and adds 100 random integers
 */
import java.io.*;
import java.util.*;
public class CreateTextFile {
	public static void main(String[] args) throws IOException{
		try (Formatter output = new Formatter(
					new FileOutputStream("Exercise17_01.txt", true));
				) {		
			for (int i = 0; i < 101; i++) {
				output.format("%d ", (int)(Math.random() * 100));
			}
		}
	}
}
