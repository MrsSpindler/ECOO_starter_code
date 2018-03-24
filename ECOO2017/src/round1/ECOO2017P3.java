package round1;

import java.io.*;

/**
 * @author e23549
 *
 */
public class ECOO2017P3 {

	private static int[] mountainHeight;
	private static int[] mountainView;
	private static int numberOfMountains;
	
	public static void main(String[] args) throws IOException {

		BufferedReader fileIn = new BufferedReader(new FileReader("res/DATA32.txt"));

		// expect 10 test cases in the file
		for (int testCase = 0; testCase < 10; testCase++) {

			numberOfMountains = Integer.parseInt(fileIn.readLine());

			// height of each mountain - from file
			mountainHeight = new int[numberOfMountains];
			String[] heightSTR = fileIn.readLine().split(" ");
			for (int i = 0; i < numberOfMountains; i++) {
				mountainHeight[i] = Integer.parseInt(heightSTR[i]);
			}
			
			//determine the number of 'views' (other mountains) that each mountain can see
			populateViews();
			
			//Determine the mountain with the best view
			System.out.println(getBestView());
		}
		fileIn.close(); // close the file.
	}

	private static void populateViews() {
		
		// view from each mountain - to be calculated
		mountainView = new int[numberOfMountains];
		
		//determine how many mountains can be seen from each peak
		for (int i = 0; i < numberOfMountains; i++) {
			for (int j = i+1; j < numberOfMountains; j++) {
				if (isMountainVisible(i, mountainHeight[i], j, mountainHeight[j])) {
					//visibility is reciprocal; update both mountain's view count
					mountainView[i]++;
					mountainView[j]++;
				}
			}
		}
	}

	private static boolean isMountainVisible(int x1, int y1, int x2, int y2) {
		
		// get equation of line of sight
		float slope = (float) (y2 - y1) / (x2 - x1);
		float yIntercept = y2 - (slope * x2);

		//check for mountains in between blocking the line of sight
		for (int i = x1 + 1; i < x2; i++) {
			//line of sight between the two mountains considered; shift to the leftmost mountain (since range keeps moving)
			if (mountainHeight[i] >= (slope * i + yIntercept)) {
				//mountain in range is blocking the view
				return false;
			}
		}
		return true;
	}

	private static int getBestView() {
		//assume first mountain is the best
		int position = 0;
		int best = mountainView[0];
		for (int i = 1; i < numberOfMountains; i++) {
			//check all mountains after the first one
			if (mountainView[i] > best) {
				//found a better one; keep its position and view #
				position = i;
				best = mountainView[i];
			}
		}
		return position + 1; // mountain positions start at 1 for output purposes
	}
}