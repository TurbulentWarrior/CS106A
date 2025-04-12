/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		int num = readInt("Enter a number:");
		hailProc(num);
	}

	// this method makes hailstone calculations on the declared integer
	private void hailProc(int num) {
		int temp = num;
		int stepCount = 0;
		if (num > 0) {
			while (temp != 1) {
				if (temp % 2 == 0) {
					println(temp + " is even, so I take half:" + checkEven(temp));
					temp = checkEven(temp);
					stepCount++;
				} else {
					println(temp + " is odd, so I make 3n+1:" + checkOdd(temp));
					temp = checkOdd(temp);
					stepCount++;
				}
			}
		} else {
			println("Please enter a positive number ");
		}
		finalMessage(stepCount);
	}

	// this method returns how many steps it took to reach 1
	private void finalMessage(int stepCount) {
		println("The process took " + stepCount + " steps to reach 1");
	}

	// This method acts on odd integers
	private int checkOdd(int i) {
		int nextVal = i * 3 + 1;
		return nextVal;
	}

	// This method acts on even integers
	private int checkEven(int i) {
		int nextVal = i / 2;
		return nextVal;
	}
}
