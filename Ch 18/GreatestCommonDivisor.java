/*
 * Kevin Drake
 * 1/20/22
 * This program calculates the greatest common divisor of two numbers recursively
 */
import java.util.Scanner;
public class GreatestCommonDivisor {
	public static int gcd(int n, int d) {
		int gcd = 0;
		if (n % d == 0)
			return d;
		else 
			return gcd(d, n % d);
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter two numbers to find their greatest common divisor: ");
		int num = input.nextInt();
		int div = input.nextInt();
		System.out.println("The greatest common divisor is " + gcd(num, div));
	}
}
