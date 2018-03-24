package round1;

import java.io.*;

public class ECOO2017P1 {

	static int tripCost, totalStudents, sumOfStudents;
	static int budget;
	
	static String[] years;
	static int[] studentsPerGrade; 
	static int[] price = {12, 10, 7, 5};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader fileIn = new BufferedReader(new FileReader("res/DATA11.txt"));

		//expect 10 test cases in the file
		for (int testCase = 0; testCase < 10; testCase++) {

			tripCost = Integer.parseInt(fileIn.readLine());
			years = fileIn.readLine().split(" ");
			totalStudents = Integer.parseInt(fileIn.readLine());
			
			budget = 0;
			sumOfStudents = 0;
			studentsPerGrade = new int[4];
			
			//calculate how much money was made from the sales
			populateBudget();
			
			//add extra students to largest group
			adjustBudget();

			//determine final answer for this scenario
			if (budget/2.0 < tripCost) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
		fileIn.close(); 
	}

	private static void populateBudget() {
		
		//Loop through all years percentages
		for (int i = 0; i < 4; i++) {
			//determine number of students in this grade; always rounding down
			studentsPerGrade[i]=(int) Math.floor(Float.parseFloat(years[i])*totalStudents);
			//how much did we make from these students?
			budget += studentsPerGrade[i]*price[i];
			//keeping track of how many students in total
			sumOfStudents += studentsPerGrade[i];
		}
	}
	
	private static void adjustBudget() {
		//check to see if we have all the students
		if (sumOfStudents < totalStudents) {
			
			//too much rounding; throw extras in the largest group
			int largestGroup = 0;
			int largestAmount = studentsPerGrade[0];
			
			//finding the largest group
			for (int i = 1; i < studentsPerGrade.length; i++) {
				if (largestAmount < studentsPerGrade[i]) {
					largestGroup = i;
					largestAmount = studentsPerGrade[i];
				}
			}
			//adjust the budget
			budget += (totalStudents - sumOfStudents)*price[largestGroup];
			
			//add any extras to largest group
			studentsPerGrade[largestGroup] += (totalStudents - sumOfStudents);
		}
	}


}
