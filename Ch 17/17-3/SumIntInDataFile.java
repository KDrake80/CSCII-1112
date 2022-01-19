/*
 * Kevin Drake
 * 1/18/22
 * This Creates a binary data File, populates it with 100 random numbers
 * then Returns with the sum of all the numbers
 */ 
import java.io.*;
import java.util.*;
public class SumIntInDataFile {
	public static void main(String[] args) throws IOException {
		writeData();
		readData();
	}
	public static void writeData() throws IOException {
		try (DataOutputStream output = new DataOutputStream(new FileOutputStream("Exercise17_03.dat", true));
				) {
			for (int i = 0; i < 100; i++) {
				output.write((int)(Math.random() * 1000));
			}
			output.close();
		}
	}

	public static void readData() throws IOException {
		try (DataInputStream input = 
				new DataInputStream(new FileInputStream("Exercise17_03.dat"));
				) {
			int value;
			int sum = 0;
			while ((value = input.read()) != -1) {
				System.out.print(value + " ");
				sum += value;
			}
			System.out.println("\nSum is " + sum);
		}
		catch (IOException ex ) {
			System.out.println("");
		}

	}
}
