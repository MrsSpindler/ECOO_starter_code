package round1;

/**
 * 
 */
import java.io.*;

/**
 * @author e23549
 *
 */
public class ECOO2016P1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		FileReader In;// Variable for a read stream to a file
		BufferedReader fileIn; // Variable for a buffer to read the file
								// contents.
		int numStudents;
		float grade;
		In = new FileReader("res/data11.txt"); // Creates a read stream object
		fileIn = new BufferedReader(In); // Creates the buffered reader object

		for (int k = 0; k < 10; k++) {
			int numPasses = 0;
			String[] weights, scores;
			
			// assessment weightings is the First Line
			weights = fileIn.readLine().split(" ");
			numStudents = Integer.parseInt(fileIn.readLine());

			for (int i = 0; i < numStudents; i++) {
				grade = 0;
				scores = fileIn.readLine().split(" ");
				for (int j = 0; j < 4; j++) {
					grade = grade + Float.parseFloat(scores[j]) * Float.parseFloat(weights[j])/100;
				}
				if (grade >= 50) { numPasses++; }
			}

			System.out.println(numPasses);
		}

		fileIn.close(); // close the file.
	}

}
