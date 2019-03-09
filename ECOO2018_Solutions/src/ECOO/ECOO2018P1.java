package ECOO;

import java.io.*;

public class ECOO2018P1 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader fileIn = new BufferedReader(new FileReader("res/DATA12.txt"));

		//expect 10 runs of the program (not necessarily 10 lines!) in the file
		for (int k = 0; k < 10; k++) {
			
			String [] firstLine = fileIn.readLine().split(" ");
			
			int T =  Integer.parseInt(firstLine[0]);
			int N =  Integer.parseInt(firstLine[1]);
			
			int playDaysLeft = 0;
			
			for (int i = 0; i < N; i++) {
				char box = fileIn.readLine().charAt(0);
				if (box == 'B') {
					playDaysLeft += T;
				}
				
				if (playDaysLeft > 0) {
					playDaysLeft--;
				}
			}
			System.out.println(playDaysLeft);
		}
		fileIn.close(); // close the file.
	}
}
