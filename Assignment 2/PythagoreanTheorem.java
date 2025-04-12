/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		println("Enter Values to compute Pythagorean theorem");
		int a = readInt("a:");
		int b = readInt("b:");
		println("c=" + res(a, b));
	}

	// this method computes a hypothenuse value for given(a,b)
	private double res(int a, int b) {
		double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
		return c;
	}
}
