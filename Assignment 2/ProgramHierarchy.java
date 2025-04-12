
/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {
	private final static double RECTANGLE_HEIGHT = 150;
	private final static double RECTANGLE_WIDTH = 150;

	public void run() {
		double cx = getWidth() / 2.0;
		double cy = getHeight() / 2.0;
		drawGraph(cx, cy);

	}

	private void drawGraph(double cx, double cy) {
		// draws the whole graph

		drawHierarchyRectangle(cx, cy);
		drawHierarchyLine(cx, cy);
		drawHierarchyLabel(cx, cy);

	}

	// draws all the labels in the graph
	private void drawHierarchyLabel(double cx, double cy) {

		GLabel glabel = new GLabel("Program", cx, cy - RECTANGLE_HEIGHT / 2);
		add(glabel);

		double a = glabel.getAscent() / 2;
		double h = glabel.getWidth() / 2;
		glabel.move((-h), (+a));

		GLabel glabel1 = new GLabel("Graphics Program", cx - 3 * RECTANGLE_WIDTH / 2, cy + 3 * RECTANGLE_HEIGHT / 2);
		add(glabel1);

		// centre the label
		double a1 = glabel1.getAscent() / 2;
		double h1 = glabel1.getWidth() / 2;
		glabel1.move((-h1), (a1));

		GLabel glabel2 = new GLabel("Console Program", cx, cy + 3 * RECTANGLE_HEIGHT / 2);
		add(glabel2);
		// centre the label
		double a2 = glabel2.getAscent() / 2;
		double h2 = glabel2.getWidth() / 2;
		glabel2.move((-h2), (+a2));

		GLabel glabel3 = new GLabel("Dialog Program", cx + 3 * RECTANGLE_WIDTH / 2, cy + 3 * RECTANGLE_HEIGHT / 2);
		add(glabel3);
		// centre the label
		double a3 = glabel3.getAscent() / 2;
		double h3 = glabel3.getWidth() / 2;
		glabel3.move((-h3), (+a3));
	}

	// draws all the rectangles on the graph
	private void drawHierarchyRectangle(double cx, double cy) {

		GRect rect0 = new GRect(cx - RECTANGLE_WIDTH / 2, cy - RECTANGLE_HEIGHT, RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
		add(rect0);

		GRect rect = new GRect(cx - RECTANGLE_WIDTH / 2, cy + RECTANGLE_HEIGHT, RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
		add(rect);

		GRect rect1 = new GRect(cx + 2 * RECTANGLE_WIDTH / 2, cy + RECTANGLE_HEIGHT, RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
		add(rect1);

		GRect rect2 = new GRect(cx - 2 * RECTANGLE_WIDTH, cy + RECTANGLE_HEIGHT, RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
		add(rect2);
	}

	// draws all the connecting lines in the graph
	private void drawHierarchyLine(double cx, double cy) {

		GLine toConsole = new GLine(cx, cy, cx, cy + RECTANGLE_HEIGHT);
		add(toConsole);

		GLine toGraphics = new GLine(cx, cy, cx - 3 * RECTANGLE_WIDTH / 2, cy + RECTANGLE_HEIGHT);
		add(toGraphics);

		GLine toDialog = new GLine(cx, cy, cx + 3 * RECTANGLE_WIDTH / 2, cy + RECTANGLE_HEIGHT);
		add(toDialog);

	}
}
