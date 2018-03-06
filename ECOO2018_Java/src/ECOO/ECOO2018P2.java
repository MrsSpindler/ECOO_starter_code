package ECOO;

import java.io.*;
import java.util.Arrays;

public class ECOO2018P2 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader fileIn = new BufferedReader(new FileReader("res/DATA20.txt"));

		//expect 10 runs of the program (not necessarily 10 lines!) in the file
		for (int k = 0; k < 10; k++) {

			int integerInFile = Integer.parseInt(fileIn.readLine());
			double doubleInFile = Double.parseDouble(fileIn.readLine());
			String stringInFile = fileIn.readLine();
			String [] listOfStringsInFile = fileIn.readLine().split(" ");
			
			System.out.println("Read " + (k+1) + ": " + integerInFile + " " + doubleInFile + " " + stringInFile + " " + Arrays.deepToString(listOfStringsInFile));
			
		}

		fileIn.close(); // close the file.
	}

}
