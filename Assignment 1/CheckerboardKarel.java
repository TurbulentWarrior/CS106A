/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	public void run() {
		fillBoard();
	}

	private void fillBoard() {
		// this method fills the board
		if (frontIsClear()) {
			while(frontIsClear()){
			colorLine();
			}
		} else {
			colorSingleRow();
		}
		endCorrection();
	}

	private void colorLine() {
		// this method fills the general row
		// before the actual collision
		colorOdd();
		returnBack();

		goUp();
		colorEven();
		returnBack();
		goUp();
	}

	private void goUp() {
		// this method ensures karel to move up a row
		if (facingWest()) {
			turnRight();
			if (frontIsClear()) {
				move();
				turnRight();
			}
		}
	}

	private void returnBack() {
		// this method ensures karel to return
		turnAround();
		while (frontIsClear()) {
			move();
		}

	}

	private void colorEven() {
		// this method fills even rows
		if(frontIsClear()){
			move();
			putBeeper();
			while(frontIsClear()){
				makeNextPlate();
			}
		}
	}
	private void colorOdd() {
		// this method fills odd rows
		putBeeper();
		while(frontIsClear()){
			makeNextPlate();
		}

	}

	private void makeNextPlate() {
		// this method ensures to move twice and put a beeper if possible
		if(frontIsClear()){
			move();
		}
		if(frontIsClear()){
			move();
			putBeeper();
		}
	}

	private void colorSingleRow() {
		// this is just 1 colun edge case
		while (noBeepersPresent()) {
			colorLine();
		}
	}

	private void endCorrection() {
		// this method just makes corner correction
		if(!noBeepersPresent()){
			pickBeeper();
		}	
		if (facingSouth()) {
			putBeeper();
		}
	}

}
