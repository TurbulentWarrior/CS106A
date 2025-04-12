/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	private static final int SENTINEL = 0;

	public void run() {
		int largeInt, smallInt, temp;
		initText();

		temp = readInt("? ");

		if (temp != SENTINEL) {
			largeInt = temp;
			smallInt = temp;
			while (temp != SENTINEL) {
				temp = readInt("? ");
				if (temp != SENTINEL) {
					if (temp >= largeInt) {
						largeInt = temp;
					}
					if (temp <= smallInt) {
						smallInt = temp;
					}
				}
			}
			finalMessage(largeInt, smallInt);
		} else {
			println("You have entered the special symbol");
		}

	}

	/** this method returns final smallest and largest
	 values in the proccess*/
	
	private void finalMessage(int largeInt, int smallInt) {
		println("Smallest: " + smallInt);
		println("Largest: " + largeInt);
	}

	/** This method generates text for program title*/
	private void initText() {
		println("This program finds the largest and smallest numbers");
	}
}
