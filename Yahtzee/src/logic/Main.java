package logic;

import java.util.Scanner;

import classes.DiceCup;
import classes.Scorecard;

public class Main {

	public static void main(String[] args) {
		
		// Instantiate instances of required classes
		Scorecard scorecard = new Scorecard("Kayle");
		DiceCup dicecup = new DiceCup();
		
		// Declare variables for game loop
		int turnNumber = 1;
		int NUMBER_OF_ROUNDS = 13;
		
		System.out.println("RULES OF YAHTZEE");
		System.out.println("Yahtzee is a dice game played with five dice. The goal is to score points by rolling specific combinations.");
		System.out.println("Each player takes turns rolling the dice up to three times per turn. After each roll, the player can choose");
		System.out.println("which dice to keep and which to reroll. A player can only score each combination once per game.");
		System.out.println("The game consists of 13 rounds, with each player filling out a scorecard. The player with the highest total");
		System.out.println("score at the end of the 13 rounds wins the game.");
		
		System.out.println("\nThe dice are numbered 1 - 5, from left to right. To choose which dice to keep each roll, type in the dice");
		System.out.println("number with spaces in between and press Enter (e.g. 1 3 4)");
		System.out.println("You can just press Enter to re-roll all the dice. Type 'k' and press Enter to keep all the dice.\n");
		
		
		while (turnNumber <= NUMBER_OF_ROUNDS) {
			
			System.out.println("---------------------------------------------");
			System.out.println("  " + scorecard.getPlayerName() + "'s turn" + "\t|  " + "TURN NUMBER\t  " + turnNumber + " / " + NUMBER_OF_ROUNDS);
			System.out.println("---------------------------------------------");
			
			while (dicecup.rollsRemaining != 1) {
				
				switch (dicecup.rollsRemaining) {
				case 3:
					System.out.println("First roll:");
					break;
				case 2:
					System.out.println("\nSecond roll:");
					break;
				}
				
				dicecup.rollDice();
				dicecup.displayCurrentRoll();
				dicecup.setToBeRolledToTrue();
				
				// Prompt user to select dice to keep
				Scanner diceObj = new Scanner(System.in);
				System.out.println("\nWhat dice would you like to keep?");
				String diceString = diceObj.nextLine();
				diceObj.reset();
				
				if (diceString.equals("k")) {
					dicecup.setToBeRolledToFalse();
					break;
				} else {
					// Split string input into array of digits and convert to integer array
					String[] splitArray = (diceString == "") ? new String[0] : diceString.split(" ");
					int[] intArray = new int[splitArray.length];
					
					for (int i=0; i < splitArray.length; i++) {
						intArray[i] = Integer.parseInt(splitArray[i]);
					}
					
					dicecup.chooseDiceToKeep(intArray);
				}			
			}
			
			System.out.println("\nLast roll:");
			dicecup.rollDice();
			dicecup.displayCurrentRoll();
			
			// Display final roll and list of scoring options
			dicecup.setToBeRolledToFalse();
			System.out.println("\nHere are your dice...");
			dicecup.displayCurrentRoll();
			System.out.println("\nWhat would you like to score?\n");
			
			int[] diceResults = dicecup.getDiceArray();
			
			try {
				scorecard.getOptions();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Scanner scoreObj = new Scanner(System.in);
			System.out.println("\n>>> ");
			int scoreIndex = scoreObj.nextInt();
			scoreObj.reset();
			
			try {
				scorecard.setScore(scoreIndex, diceResults);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Increment turn number and reset dice array
			turnNumber += 1;
			dicecup.resetRolls();
			
			try {
				scorecard.printScorecard();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		// Set final scores
		scorecard.setSubtotalUpper();
		scorecard.setTotalUpper();
		scorecard.setTotalLower();
		scorecard.setGrandTotal();
		
		// Collect variables for final score sheet
		String upperBonusString = (scorecard.getUpperBonus()) ? "Well done! Upper section bonus!\t+35 points" : "Sorry, you did not get enough points to get the upper section bonus." ;
		
		boolean yahtzeeScored = (scorecard.yahtzee.getValue() > 0) ? true : false;
		int yahtzeeBonusTally = scorecard.yahtzee.getYahtzeeBonusTally();
		String yahtzeeString = new String();
		
		if (!yahtzeeScored) {
			yahtzeeString = "You did not score any Yahtzee's in this game :(";
		} else if (yahtzeeScored && yahtzeeBonusTally == 0) {
			yahtzeeString = "Well done! You scored a Yahtzee this game, worth 50 points!";
		} else {
			yahtzeeString = "Yahtzee KING !!! You scored a Yahtzee this game, PLUS " + yahtzeeBonusTally + " Yahtzee bonus(es) for a WHOPPING score of " + scorecard.yahtzee.getValue();
		}
		

		
		// Show final scores here
		System.out.println("SUBTOTAL OF UPPER SECTION\t\t" + scorecard.getSubtotalUpper());
		System.out.println(upperBonusString);
		System.out.println("TOTAL OF UPPER SECTION\t\t\t" + scorecard.getTotalUpper());
		
		System.out.println("-----------------------------------------------------------------");
		
		System.out.println(yahtzeeString);
		System.out.println("TOTAL OF LOWER SECTION\t\t\t" + scorecard.getTotalLower());
		
		System.out.println("-----------------------------------------------------------------");
		System.out.println("\nTOTAL SCORE (" + scorecard.getPlayerName() + ")\t\t\t" + scorecard.getGrandTotal());
		System.out.println("-----------------------------------------------------------------");
		
	}

}
