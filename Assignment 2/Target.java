/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {
	/** Outer radius in pixels */
	public static final double OUTER_RAD = 72;

	/** Middle radius in pixels */
	public static final double MIDDLE_RAD = 72 * 1.65 / 2.54;

	/** Inner radius in pixels */
	public static final double INNER_RAD = 72 * 0.76 / 2.54;

	/** color indices */
	public static final int COLOR_1 = 1;

	public static final int COLOR_2 = 0;

	public void run() {
		drawTarget();
	}

	// this method draws a target on a blank canvas
	private void drawTarget() {
		drawCircle(OUTER_RAD, COLOR_1);
		drawCircle(MIDDLE_RAD, COLOR_2);
		drawCircle(INNER_RAD, COLOR_1);
	}

	// this method draws a circle of certain color and size
	private void drawCircle(double rad, int a) {
		double x, y, d;
		x = getWidth() / 2 - rad;
		y = getHeight() / 2 - rad;
		d = 2 * rad;
		GOval circ = new GOval(d, d);
		if (a == 1) {
			circ.setColor(Color.red);
			circ.setFilled(true);
			circ.setFillColor(Color.red);

		} else {
			circ.setColor(Color.white);
			circ.setFilled(true);
			circ.setFillColor(Color.white);
		}
		add(circ, x, y);
	}

}
