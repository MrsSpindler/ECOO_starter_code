package round1;

import java.io.*;

public class ECOO2018P3 {

	public static void main(String[] args) throws IOException {

		BufferedReader fileIn = new BufferedReader(new FileReader("res/DATA32.txt"));

		// expect 10 runs of the program (not necessarily 10 lines!) in the file
		for (int k = 0; k < 10; k++) {

			boolean failed = false;

			String[] firstLine = fileIn.readLine().split(" ");

			int N = Integer.parseInt(firstLine[0]);
			int X = Integer.parseInt(firstLine[1]);
			int Y = Integer.parseInt(firstLine[2]);
			int Z = Integer.parseInt(firstLine[3]);

			String[] codes = new String[N];
			String[] converted = new String[N];
			String[] answerKeys = new String[N];

			String failList = "";

			// get the codes and figure out the correct answer
			for (int i = 0; i < N; i++) {
				codes[i] = fileIn.readLine();
				converted[i] = "";

				for (int j = 0; j < codes[i].length(); j++) {
					int digit = Character.getNumericValue(codes[i].charAt(j));
					if (digit == 0) {
						converted[i] += Z;
					} else if (digit % 2 == 0) { //even
						converted[i] += (digit + X);
					} else { //odd
						digit -= Y;
						if (digit < 0) {
							converted[i] += 0;
						} else {
							converted[i] += digit;
						}
					}
				}
			}

			// throw out the 'A'
			fileIn.readLine();

			// figure out if we have failures or not
			for (int i = 0; i < N; i++) {
				answerKeys[i] = fileIn.readLine();

				if (!answerKeys[i].equals(converted[i])) {
					failed = true;
					failList += (i + 1) + ",";
				}
			}

			if (failed) {
				System.out.println("FAIL: " + failList.substring(0, failList.length() - 1));
			} else {
				System.out.println("MATCH");
			}

			// throw out the '*'
			fileIn.readLine();

		}

		fileIn.close(); // close the file.
	}

}
