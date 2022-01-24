/*
 * Kevin Drake
 * 1/24/22
 * This program recursively displays a string in reverse 
 */
import java.util.Scanner;
public class ReverseDisplay {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a string: ");
		String s = input.nextLine();
		reverseDisplay(s);
	}
		public static void reverseDisplay(String value) {
		if (value.length() > 0) {
			System.out.println(value.charAt(value.length() - 1));
			reverseDisplay(value.substring(0, value.length() - 1));
		}
	}
}
