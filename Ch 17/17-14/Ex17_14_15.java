/*/
 * Kevin Drake
 * 1/19/22
 * This Program prompts the user to enter a file name, then encrypts the file, then decrypts to another.
 */
import java.io.*;
import java.util.*;
public class Ex17_14_15 {
	public static void main(String[] args) throws IOException {
		// Ex17_14_15txt.dat
		// secondFile.dat
		// thirdFile.dat
		
		Scanner scans = new Scanner(System.in);
		System.out.println("Enter the File input name: ");
		File inputFile = new File(scans.nextLine());
		
		System.out.println("Enter the File output name:  ");
		File outputFile = new File(scans.nextLine());
		
		System.out.println("Press 1 for Encrypt, 2 for Decrypt: ");
		int choice = scans.nextInt();
		if (choice == 1) {
			try (
					BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(outputFile));	
					BufferedInputStream input = new BufferedInputStream(new FileInputStream(inputFile));
					) {
				encryptFile(output, input);
			}
		}
		else if (choice == 2) {
			try (
					BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(outputFile));
					BufferedInputStream input = new BufferedInputStream(new FileInputStream(inputFile))
					){
				decryptFile(output, input);
			}
		}
		
	}
	public static void encryptFile(BufferedOutputStream output, BufferedInputStream input) throws IOException {
		int value;
		try {while ((value = input.read()) != -1)
				output.write(value + 5);
			}
		catch (IOException ex) {
			System.out.println("File cannot be Read");
		}
	}
	public static void decryptFile(BufferedOutputStream output, BufferedInputStream input) throws EOFException, IOException {
		int value;
		try { while ((value = input.read()) != -1) {
			output.write(value - 5);
			}
				
		}
		catch (EOFException eo) {
			System.out.println("End of File");
		}
		catch (IOException io) {
			System.out.println("File cannot be read");
		}
	}
}
