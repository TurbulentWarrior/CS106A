/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	public void run() {
		initialise();
		returnBack();
		pickUp();
	}

	private void pickUp() {
		// this method puts beeper in the middle of the row
		turnAround();
		if (frontIsClear()) {
			move();
		}
		putBeeper();
		turnAround();

	}

	private void returnBack() {
		// clearing the beeper line
		turnAround();
		pickBeeper();
		if (frontIsClear()) {
			move();
			while (beepersPresent()) {

				while (beepersPresent()) {
					move();
				}
				turnAround();
				move();
				pickBeeper();
				move();
			}
		}

	}

	private void initialise() {
		// this method initiates the motion
		if (frontIsClear()) {
			move();
			while (frontIsClear()) {
				putBeeper();
				move();

			}
			putBeeper();
		} else {
			putBeeper();
		}
	}
}
