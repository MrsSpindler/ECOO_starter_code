package round1;

import java.io.*;
import java.util.ArrayList;

public class ECOO2017P2 {

	static String[] chocolateNames;
	static int[] scoreG, scoreF, scoreP, totalScore;

	public static void main(String[] args) throws IOException {

		BufferedReader fileIn = new BufferedReader(new FileReader("res/DATA22.txt"));

		// expect 10 test cases in the file
		for (int testCase = 0; testCase < 10; testCase++) {
			
			//number of chocolates in this contest
			int numberOfChocolates = Integer.parseInt(fileIn.readLine());

			//names of the chocolates - needed for final screen output
			chocolateNames = new String[numberOfChocolates];

			//Arrays to hold scores
			scoreP = new int[numberOfChocolates];
			scoreF = new int[numberOfChocolates];
			scoreG = new int[numberOfChocolates];
			totalScore = new int[numberOfChocolates];

			//determine how each judge scored the chocolate
			for (int i = 0; i < numberOfChocolates; i++) {
				//get the name of this chocolate, store it for output
				chocolateNames[i] = fileIn.readLine();
				//fill in the scoring arrays
				populateScores(fileIn, i);
			}

			//Determine the winner - based on total score
			ArrayList<Integer> firstRoundWinners = determineWinner(totalScore);
			
			//break any ties, if needed
			ArrayList<Integer> roundGWinners = breakTie(firstRoundWinners, scoreG);
			ArrayList<Integer> roundFWinners = breakTie(roundGWinners, scoreF);
			ArrayList<Integer> roundPWinners = breakTie(roundFWinners, scoreP);
				
			//output to screen the winner or winner list
			//check the winner lists in the opposite order that they were populated
			//to ensure we get the final verdict
			if ( ! roundPWinners.isEmpty()) {
				System.out.println(printWinnerList(roundPWinners));
			} else if ( ! roundFWinners.isEmpty()) {
				System.out.println(printWinnerList(roundFWinners));
			} else if ( ! roundGWinners.isEmpty()) {
				System.out.println(printWinnerList(roundGWinners));
			} else {
				System.out.println(printWinnerList(firstRoundWinners));
			}

			// throw away the asterisk .. not even sure why it's there!
			fileIn.readLine(); 
		}
		fileIn.close();
	}

	private static void populateScores(BufferedReader fileIn, int index) throws IOException {

		//temp variable to hold next line of text in the file
		String line;
		do {
			//in the event we've read too far and have the next chocolate name, 
			//save this spot so we can return to it easily.  
			//parameter is that maximum # of characters that can be read before resetting
			fileIn.mark(100);

			//get the next line from the file
			line = fileIn.readLine();

			//check if this is a judging line
			if (line.startsWith("J ")) {
				//update scoring arrays
				String[] judging = line.split(" ");
				scoreP[index] += Integer.parseInt(judging[1]);
				scoreF[index] += Integer.parseInt(judging[2]);
				scoreG[index] += Integer.parseInt(judging[3]);
			} else {
				// whoops! 'twas a name, let's go back
				fileIn.reset();
			}

			//update the total score for this chocolate
			totalScore[index] = scoreP[index] + scoreF[index] + scoreG[index];
			
		} while (line.startsWith("J "));

	}
	
	private static ArrayList<Integer> determineWinner(int[] score) {
		
		//list of all chocolates that have the highest score
		ArrayList<Integer> winners = new ArrayList<Integer>();
		
		//start by assuming the first element is the winner
		int highestScore = score[0];		
		winners.add(0);
		
		for (int i = 1; i < score.length; i++) {
			if (score[i] > highestScore) {
				//save the new high score
				highestScore = score[i];
				//clear list of all previous winners, no tie
				winners.clear();
				winners.add(i);
			} else if (score[i] == highestScore) {
				//have a tie, add another winner to the list
				winners.add(i);
			}
		}
		return winners;
	}

	private static ArrayList<Integer> breakTie(ArrayList<Integer> previousRoundWinners, int[] scores) {

		int highestScore;
		ArrayList<Integer> thisRoundWinners = new ArrayList<Integer>();
		
		// check for ties using the next list; if no ties, don't bother
		if (previousRoundWinners.size() > 1) {
			
			// do it again, with the next round's values
			highestScore = scores[previousRoundWinners.get(0)];
			thisRoundWinners.add(previousRoundWinners.get(0));

			for (int i = 1; i < previousRoundWinners.size(); i++) {
				//Which chocolate are we looking at?
				int chocolateNumber = previousRoundWinners.get(i);
				
				if (scores[chocolateNumber] > highestScore) {
					//save the new high score
					highestScore = scores[chocolateNumber];
					//clear list of all previous winners, no tie
					thisRoundWinners.clear();
					thisRoundWinners.add(chocolateNumber);
				} else if (scores[chocolateNumber] == highestScore) {
					//have a tie, add another winner to the list
					thisRoundWinners.add(chocolateNumber);
				}
			}
		}
		return thisRoundWinners;
	}

	private static String printWinnerList(ArrayList<Integer> winnersList) {
		String output = "";
		
		for (Integer win : winnersList) 
			output += chocolateNames[win] + ",";

		//output all but the last character to remove the comma
		return output.substring(0,output.length()-1);
	}
}