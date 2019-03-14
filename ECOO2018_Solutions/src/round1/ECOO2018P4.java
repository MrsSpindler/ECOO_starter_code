package round1;

import java.io.*;

public class ECOO2018P4 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader fileIn = new BufferedReader(new FileReader("res/DATA42.txt"));
		
		FibSquare one = new FibSquare();
		FibSquare two = new FibSquare(one);


		//expect 10 runs of the program (not necessarily 10 lines!) in the file
		for (int k = 0; k < 10; k++) {

			String [] testCoordinate = fileIn.readLine().split(" ");
			
			Coordinate point = new Coordinate(Long.parseLong(testCoordinate[0]), Long.parseLong(testCoordinate[1]));
			
			if (one.isPointContained(point)) {
				System.out.println(1);
			} else if (two.isPointContained(point)) {
				System.out.println(2);
			} else {
				//here we go!
				FibSquare nMinusTwo = one;
				FibSquare nMinusOne = two;
				
				boolean pointFound = false;
				while (! pointFound) {
					FibSquare current = new FibSquare(nMinusOne, nMinusTwo);
					if (current.isPointContained(point)) {
						pointFound = true;
						System.out.println(current.fibPosition);
					}
					else {
						nMinusTwo = nMinusOne;
						nMinusOne = current;
					}
				}
			}
			
		}

		fileIn.close(); // close the file.
	}

}
