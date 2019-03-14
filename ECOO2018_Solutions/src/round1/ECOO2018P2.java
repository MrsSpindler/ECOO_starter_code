package round1;

import java.io.*;
import java.util.Arrays;

public class ECOO2018P2 {

	public static void main(String[] args) throws IOException {

		BufferedReader fileIn = new BufferedReader(new FileReader("res/DATA20.txt"));

		// expect 10 runs of the program (not necessarily 10 lines!) in the file
		for (int k = 0; k < 10; k++) {

			int minimumDiameter = 70000; // max stated in problem

			int numberOfRoutes = Integer.parseInt(fileIn.readLine());

			// Keep track of all routes with smallest diameter, using a String since we don't know how many
			String minDiameterListUnsorted = "";
			
			// find the smallest diameter
			for (int i = 0; i < numberOfRoutes; i++) {
				String routesFromFile = fileIn.readLine();

				String diameters[] = routesFromFile.split(" ");

				// start at 2 to skip information elements
				for (int j = 2; j < diameters.length; j++) {
					int diameter = Integer.parseInt(diameters[j]);
					if (diameter < minimumDiameter) { //is this a new minimum?
						minimumDiameter = diameter;
						minDiameterListUnsorted = String.valueOf(diameters[0]);
					} else if (diameter == minimumDiameter) {
						minDiameterListUnsorted += "," + diameters[0];
					}
				}
			}


			// sort this list of routes
			String[] listToSort = minDiameterListUnsorted.split(",");
			int[] minRouteList = new int[listToSort.length];

			// convert to integer so list will sort correctly
			for (int i = 0; i < listToSort.length; i++) {
				minRouteList[i] = Integer.parseInt(listToSort[i]);
			}
			
			Arrays.sort(minRouteList);

			// put back into string
			String minDiameterListSorted = "";
			for (int i = 0; i < minRouteList.length; i++) {
				minDiameterListSorted += minRouteList[i] + ",";
			}

			System.out.println(minimumDiameter + " {" + minDiameterListSorted.substring(0, minDiameterListSorted.length() - 1) + "}");

		}

		fileIn.close(); // close the file.
	}

}
