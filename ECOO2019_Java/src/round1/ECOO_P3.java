package round1;

import java.io.*;
import java.util.Arrays;

public class ECOO_P3 {

    public static void main(String[] args) throws IOException {

        BufferedReader fileIn = new BufferedReader(new FileReader("res/DATA30.txt"));

        // expect 10 runs of the program (not necessarily 10 lines!) in the file
        for (int testCase = 0; testCase < 10; testCase++) {

            int integerInFile = Integer.parseInt(fileIn.readLine());
            double doubleInFile = Double.parseDouble(fileIn.readLine());
            String stringInFile = fileIn.readLine();
            String[] listOfStringsInFile = fileIn.readLine().split(" ");

            System.out.println("Read " + (testCase + 1) + ": " + integerInFile + " " + doubleInFile + " " + stringInFile + " " + Arrays.deepToString(listOfStringsInFile));

        }

        fileIn.close(); // close the file.
    }

}
