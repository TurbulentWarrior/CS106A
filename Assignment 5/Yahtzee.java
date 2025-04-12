/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;
import java.util.Arrays;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {

	public static void main(String[] args) {
		new Yahtzee().start(args);
	}

	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		if (nPlayers <= MAX_PLAYERS) {
			categories = new int[nPlayers][TOTAL];
			categoryFilled = new Boolean[nPlayers][TOTAL];
			for (int i = 0; i < nPlayers; i++) {
				Arrays.fill(categoryFilled[i], false);
			}
			gettingReady(dialog);
		} else {
			run();
		}
	}

	// this method lets us play the game if the correct count of players are
	// participating
	private void gettingReady(IODialog dialog) {
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		for (int k = 0; k < 13; k++) {
			for (int i = 1; i <= nPlayers; i++) {
				playerName = playerNames[i - 1];
				display.printMessage("it's " + playerName + "'s turn.");
				playOneHand(i);
			}
		}
		bonus();
		afterWord();
	}

	// This method declares the winner
	private void afterWord() {
		int[] totalScores = new int[nPlayers];
		for (int i = 0; i < nPlayers; i++) {
			for (int j = 0; j < TOTAL; j++) {
				totalScores[i] += categories[i][j];
			}
			display.updateScorecard(TOTAL, i + 1, totalScores[i]);
		}
		int[] temp = Arrays.copyOf(totalScores, totalScores.length);
		Arrays.sort(temp);
		int highestScore = temp[nPlayers - 1];
		display.printMessage("The winner(s):");
		lowScore();
		for (int i = 0; i < nPlayers; i++) {
			if (totalScores[i] == highestScore) {
				display.printMessage(playerNames[i] + "wins with a score of "
						+ highestScore);
			}
		}
	}

	// this method calculates the lower score
	private void lowScore() {
		int lowScore = 0;
		for (int j = 1; j <= nPlayers; j++) {
			for (int i = THREE_OF_A_KIND; i <= CHANCE; i++) {
				lowScore += categories[j - 1][i - 1];
			}
			display.updateScorecard(LOWER_SCORE, j, lowScore);
			categories[j - 1][LOWER_SCORE - 1] = lowScore;
		}
	}

	// This method generates the bonus scores for the players:
	private void bonus() {
		for (int i = 0; i < nPlayers; i++) {
			int upperSectionScore = 0;
			for (int j = ONES; j <= SIXES; j++) {
				upperSectionScore += categories[i][j - 1];
			}
			display.updateScorecard(UPPER_SCORE - 1, i + 1, upperSectionScore);
			if (upperSectionScore >= 63) {
				display.updateScorecard(UPPER_BONUS, i + 1, 35);
				categories[i][UPPER_BONUS - 1] = 35;
			} else {
				display.updateScorecard(UPPER_BONUS, i + 1, 0);
				categories[i][UPPER_BONUS - 1] = 0;
			}
		}
	}

	private void playOneHand(int i) {
		firstPhase(i);
		secondPhase(i);
		thirdPhase(i);

	}

	// This method lets us to roll dice for the last time and
	// select the desirable category, then check wheter if it is filled or not!
	// and append the score
	private void thirdPhase(int i) {
		secondPhase(i);
		display.printMessage("it's " + playerName
				+ "'s turn. Choose the category!");
		int category;
		boolean validSelection = false;
		while (!validSelection) {
			category = display.waitForPlayerToSelectCategory();

			if (!categoryFilled[i - 1][category - 1]) {
				int score = checkCategory(dice, category);
				categories[i - 1][category - 1] = score;
				categoryFilled[i - 1][category - 1] = true;
				display.updateScorecard(category, i, score);
				validSelection = true;
			} else {
				display.printMessage("Choose a valid category!");
			}
		}
	}

	// This method checks all the possible category outcome for the given hand.
	private int checkCategory(int[] dice, int cat) {
		switch (cat) {
		case ONES:
			return singlesScore(1);
		case TWOS:
			return singlesScore(2);
		case THREES:
			return singlesScore(3);
		case FOURS:
			return singlesScore(4);
		case FIVES:
			return singlesScore(5);
		case SIXES:
			return singlesScore(6);
		case THREE_OF_A_KIND:
			return oneOfAKindScore(3);
		case FOUR_OF_A_KIND:
			return oneOfAKindScore(4);
		case FULL_HOUSE:
			return fullHouseScore();
		case SMALL_STRAIGHT:
			return smallStraightScore();
		case LARGE_STRAIGHT:
			return largeStraightScore();
		case YAHTZEE:
			return oneOfAKindScore(5);
		case CHANCE:
			return sum(dice);
		default:
			return 0;
		}
	}

	// This method can detect large straight and give the corresponding score
	private int largeStraightScore() {
		int[] tempdice = Arrays.copyOf(dice, dice.length);
		Arrays.sort(tempdice);
		int lsOne[] = { 1, 2, 3, 4, 5 };
		int lsTwo[] = { 2, 3, 4, 5, 6 };

		if (Arrays.equals(tempdice, lsOne) || Arrays.equals(tempdice, lsTwo)) {
			return 40;
		} else {
			return 0;
		}
	}

	// This method can detect small straight and give the corresponding score
	// for that we Remove duplicates in-place and get the size of the unique
	// array, Then check for small straight sequences {1,2,3,4}, {2,3,4,5},
	// {3,4,5,6}
	private int smallStraightScore() {
		int[] tempdice = Arrays.copyOf(dice, dice.length);
		Arrays.sort(tempdice);

		int uniqueCount = 0;
		for (int i = 0; i < tempdice.length; i++) {
			if (i == 0 || tempdice[i] != tempdice[i - 1]) {
				tempdice[uniqueCount++] = tempdice[i];
			}
		}

		int[][] smallStraights = { { 1, 2, 3, 4 }, { 2, 3, 4, 5 },
				{ 3, 4, 5, 6 } };

		for (int[] straight : smallStraights) {
			if (containsSequence(tempdice, uniqueCount, straight)) {
				return 30;
			}
		}

		return 0;
	}

	// method to check if a sequence exists in the array
	private boolean containsSequence(int[] dice, int uniqueCount, int[] sequence) {
		int matchIndex = 0;
		for (int i = 0; i < uniqueCount; i++) {
			if (dice[i] == sequence[matchIndex]) {
				matchIndex++;
				if (matchIndex == sequence.length) {
					return true;
				}
			}
		}
		return false;
	}

	// This method can detect full house and give the corresponding score
	private int fullHouseScore() {
		if (fullHouseChecker()) {
			return 25;
		} else {
			return 0;
		}
	}

	// This method checks whether we have a full house in our die set
	private boolean fullHouseChecker() {
		int[] tempdice = Arrays.copyOf(dice, dice.length);
		Arrays.sort(tempdice);

		if (tempdice[0] == tempdice[1] && tempdice[2] == tempdice[3]
				&& tempdice[3] == tempdice[4]) {
			return true;
		} else if (tempdice[0] == tempdice[1] && tempdice[1] == tempdice[2]
				&& tempdice[3] == tempdice[4]) {
			return true;
		} else {
			return false;
		}

	}

	// this method can detect three of a kind, four of a kind and yahtzee
	// and will return 50 for yahtzee and sum of hand at the rest of the cases.
	private int oneOfAKindScore(int q) {
		int counter = 0;
		for (int j = 1; j <= 6; j++) {
			for (int i = 0; i < dice.length; i++) {
				if (dice[i] == j) {
					counter++;
				}
			}
			if (counter >= q) {
				if (q == 5) {
					return 50;
				} else {
					return sum(dice);
				}
			} else {
				counter = 0;
			}
		}
		return 0;
	}

	private int sum(int[] arr) {
		int itt = 0;
		for (int i = 0; i < arr.length; i++) {
			itt += arr[i];
		}
		return itt;
	}

	// This method returns scores for single cells from 1-6
	private int singlesScore(int j) {
		int counter = 0;
		for (int i = 0; i < dice.length; i++) {
			if (dice[i] == j) {
				counter++;
			}
		}
		return counter * j;
	}

	// This method lets us select die and then roll it again
	private void secondPhase(int i) {
		display.printMessage("it's " + playerName
				+ "'s turn. Choose and click on dice to re-roll");
		display.waitForPlayerToSelectDice();
		rollSelected();
		display.displayDice(dice);
	}

	// This method lets us roll the firt hand and displays it
	private void firstPhase(int i) {
		display.waitForPlayerToClickRoll(i);
		rollFirstHand();
		display.displayDice(dice);
	}

	// This method rolls only select dice
	private void rollSelected() {
		for (int i = 0; i < dice.length; i++) {
			if (display.isDieSelected(i)) {
				dice[i] = rgen.nextInt(1, 6);
			}
		}
	}

	// This method rolls dice
	private void rollFirstHand() {
		for (int i = 0; i < dice.length; i++) {
			dice[i] = rgen.nextInt(1, 6);
		}
	}

	/* Private instance variables */
	private int nPlayers;

	private String[] playerNames;

	private YahtzeeDisplay display;

	private RandomGenerator rgen = new RandomGenerator();

	/* Private instance arrays for dice hand and scores */

	private int[] dice = new int[5];

	private int[][] categories;

	private Boolean[][] categoryFilled;

	private String playerName;
}
