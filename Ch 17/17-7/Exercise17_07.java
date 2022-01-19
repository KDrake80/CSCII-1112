/*
 * Kevin Drake
 * 1/19/22
 * I rewrote this program to read Loan objects from a FileInputStream
 */
import java.io.*;

public class Exercise17_07 {
    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {
        Loan loan1 = new Loan();
        Loan loan2 = new Loan(1.8, 10, 10000);
        outputData();
        
        try (
                ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("Exercise17_07.dat"));
        ) {
            output.writeObject(loan1);
            output.writeObject(loan2);
        } 
        catch (IOException ex) {
            System.out.println("File could not be opened");
        }
    }
    
    public static void outputData() throws EOFException, IOException, ClassNotFoundException {
    	double count = 0;
    	double total = 0;
    	try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("Exercise17_07.dat"));
    			) {
    		
    		while (true) {
    		Loan newLoan = (Loan)(input.readObject());
    		count = newLoan.getLoanAmount();
    		total += count;
    		}
    	}
    	catch (EOFException ex) {
    		System.out.println("The total for the loans is " + total);
    	}
    }
}