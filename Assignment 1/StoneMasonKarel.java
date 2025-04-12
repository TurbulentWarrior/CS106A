/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	public void run() {
		while (isMoreBlocks()) {
			checkAndFill();
			moveFourBlocks();
		}
		fillLastRow();
	}

	private void fillLastRow() {
		// fills the last stroke
		checkAndFill();
		
	}

	private void moveFourBlocks() {
		// this method moves karel 4 blocks away
		// before it gets obstructed
		for (int i = 1; i <= 4; i++) {
			if (frontIsClear()) {
				move();
			}
		}
	}

	private void freeMove() {
		// this method allows karel to move if there is no obstacle
		if (frontIsClear()) {
			move();
		}
	}

	private void checkAndFill() {
		// this method checks for missing brick and fills it while moving up
		turnLeft();
		while(frontIsClear()) {
			if (noBeepersPresent()) {
				putBeeper();
				freeMove();
			} else {
				if (frontIsClear()) {
					freeMove();
				}
			}
		}
		returnToInit();

	}

	private void returnToInit() {
		// this method returns carel to initial point before ascending
		turnAround();
		for (int i = 1; i <= 4; i++) {
			move();
		}
		
		turnLeft();
	}

	private boolean isMoreBlocks() {
		// this boolean checks if we have covered the whole block system
		if (frontIsClear()) {
			return true;
		} else {
			return false;
		}
	}

}
