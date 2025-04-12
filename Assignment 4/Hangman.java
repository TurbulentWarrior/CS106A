/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	private final static int TOTAL_GUESS_COUNT = 8;

	// defining the guesscount
	private int guessCount = TOTAL_GUESS_COUNT;

	private String schifr = "";

	private HangmanCanvas canvas;

	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
		canvas.reset();
	}

	public void run() {
		HangmanLexicon lexicon = new HangmanLexicon();
		play(lexicon);
	}

	// This method generates a random word and lets us play
	private void play(HangmanLexicon lexicon) {

		int secretIndex = rangen.nextInt(0, lexicon.getWordCount());
		String secretWord = lexicon.getWord(secretIndex);
		initWord(secretWord);
		makingGuess(secretWord);
		restartGame(lexicon);
	}

	// This method starts the game screen in console:
	private void initWord(String secretWord) {
		println("Welcome to Hangman: ");
		println("the wor now looks like this: " + generateSchifr(secretWord));
		println("you have " + guessCount + " guesses left.");
		canvas.displayWord(schifr);
	}

	// This method lets us to guess the word
	private void makingGuess(String secretWord) {
		while (guessCount > 0 && !schifr.equals(secretWord)) {
			char guess = enteredChar();
			checkGuess(guess, secretWord);

		}

	}

	// this method checks whether these is indicated char
	// and swaps '-' in schifr accordingly
	private void checkGuess(char guess, String secretWord) {
		char lower = Character.toLowerCase(guess);
		char upper = Character.toUpperCase(guess);
		boolean checker = false;

		for (int i = 0; i < secretWord.length(); i++) {
			if (lower == secretWord.charAt(i) || upper == secretWord.charAt(i)) {
				schifr = schifr.substring(0, i) + upper
						+ schifr.substring(i + 1, secretWord.length());
				checker = true;
			}
		}
		if (checker) {
			println("That guess is correct!");
			if (!schifr.equals(secretWord)) {
				println("The word now looks like this: " + schifr);
				println("You have " + guessCount + " guesses left");
			} else {
				println("The word was: " + secretWord);
				println("You Win.");
			}
			canvas.displayWord(schifr);

		} else {
			guessCount--;
			println("There are no " + guess + "'s in the word.");
			canvas.displayWord(schifr);
			canvas.noteIncorrectGuess(guess);
			if (guessCount > 0) {
				println("The word now looks like this: " + schifr);
				println("You have " + guessCount + " guesses left");
			} else {
				println("You're completely hung.");
				println("The word was: " + secretWord);
				println("You lose.");
			}
		}
	}

	// this method allows us to restart the game, if we indicate 'Y' we will
	// restart
	// otherwise the app will close down in 2seconds.
	private void restartGame(HangmanLexicon lexicon) {
		String restart = readLine("Enter 'Y' if you want to play again: ");
		if (restart.equals("Y") || restart.equals("y")) {
			println("----------------------------" + "----------------------");
			schifr = "";
			canvas.reset();
			guessCount = TOTAL_GUESS_COUNT;
			play(lexicon);

		} else {
			println();
			println("Thank you for playing. Goodbye!");
			pause(2000);
			System.exit(0);
		}
	}

	// This method returns the character
	private char enteredChar() {
		String entry = readLine("Your guess: ");
		if (entry.length() == 1) {
			char c = entry.charAt(0);
			if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
				return c; // Return the valid character
			} else {
				println("Please, enter a valid character (a-z or A-Z).");
			}
		} else {
			println("Please, enter a single character.");
		}
		return enteredChar(); // Recursive call for invalid input
	}

	// This method generates the initial schifr
	private String generateSchifr(String secretWord) {
		for (int i = 0; i < secretWord.length(); i++) {
			schifr = schifr + "-";
		}
		return schifr;
	}

	private RandomGenerator rangen = new RandomGenerator();
}
