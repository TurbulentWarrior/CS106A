/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

	/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

	/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

	/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;

	public void run() {
		drawPyramid();
	}

	// this method draws full pyramid on a blank canvas
	private void drawPyramid() {
		for (int i = 1; i <= BRICKS_IN_BASE; i++) {
			drawBrickRow(i);
		}
	}

	// This method draws i-th line of pyramid with i amount of block at
	// specified place on canvas
	private void drawBrickRow(int i) {
		int brickNum = i;
		double l = getWidth() / 2 - BRICK_WIDTH / 2 * brickNum;
		double y = getHeight() - BRICK_HEIGHT * (BRICKS_IN_BASE + 1 - brickNum);
		for (int j = 1; j <= brickNum; j++) {
			double x = l + (j - 1) * BRICK_WIDTH;
			GRect brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
			add(brick, x, y);
		}

	}
}
