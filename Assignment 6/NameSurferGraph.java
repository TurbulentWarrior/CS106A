/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;

import java.awt.Color;
import java.awt.event.*;
import java.util.*;

public class NameSurferGraph extends GCanvas implements NameSurferConstants,
		ComponentListener {

	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */
	private ArrayList<NameSurferEntry> surferLog;

	public NameSurferGraph() {
		surferLog = new ArrayList<NameSurferEntry>();
		addComponentListener(this);
	}

	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	public void clear() {
		surferLog.clear();
		update();
	}

	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display. Note
	 * that this method does not actually draw the graph, but simply stores the
	 * entry; the graph is drawn by calling update.
	 */
	public void addEntry(NameSurferEntry entry) {
		surferLog.add(entry);
		// System.out.println("" + surferLog.size());
	}

	/**
	 * Updates the display image by deleting all the graphical objects from the
	 * canvas and then reassembling the display according to the list of
	 * entries. Your application must call update after calling either clear or
	 * addEntry; update is also called whenever the size of the canvas changes.
	 */
	public void update() {
		removeAll();
		background();
		drawEntries();
	}

	// this method plots a graph for the every entry
	private void drawEntries() {
		int color = 0;
		for (NameSurferEntry tempEntry : surferLog) {
			// String name = tempEntry.getName();
			// System.out.println(name);
			drawPlot(tempEntry, color);
			color++;
		}
	}

	private void drawPlot(NameSurferEntry tempEntry, int i) {
		for (int j = 0; j < NDECADES - 1; j++) {
			double x1 = ((double) getWidth() / NDECADES) * j;
			double y1 = yCoord(tempEntry, j);
			double x2 = ((double) getWidth() / NDECADES) * (j + 1);
			double y2 = yCoord(tempEntry, j + 1);

			GLine line = new GLine(x1, y1, x2, y2);

			line.setColor(color(i));
			add(line);
			nameLabel(x1, y1, tempEntry, color(i), j);
			if (j == NDECADES - 2) {
				nameLabel(x2, y2, tempEntry, color(i), j + 1);
			}
		}

	}

	// this method ensures the color loop on the canvas
	private Color color(int j) {
		int ind = (j) % 4;
		switch (ind) {
		case 0:
			return Color.BLACK;
		case 1:
			return Color.RED;
		case 2:
			return Color.BLUE;
		case 3:
			return Color.MAGENTA;

		default:
			return Color.BLACK;
		}

	}

	// this method draws the corresponding label
	private void nameLabel(double x, double y, NameSurferEntry entry,
			Color col, int j) {
		GLabel label = new GLabel(entry.getName() + " " + entry.getRank(j));
		if (entry.getRank(j) == 0) {
			label.setLabel(entry.getName() + "*");
		}
		label.setColor(col);
		add(label, x, y);
	}

	// this method returns corresponding y coordinate on the plot
	private double yCoord(NameSurferEntry entry, int k) {
		double coord;
		int rank = entry.getRank(k);
		double scalarMult = ((double) getHeight() - 2 * GRAPH_MARGIN_SIZE)
				/ MAX_RANK;
		if (rank != 0) {
			coord = GRAPH_MARGIN_SIZE + rank * scalarMult;
		} else {
			coord = getHeight() - GRAPH_MARGIN_SIZE;
		}
		return coord;
	}

	// this method implements the grid on the background and margins on top and
	// bottom of the application
	private void background() {
		gridLines();
		margins();
	}

	// this method implements the gridlines on the canvas
	private void gridLines() {
		double windowWidth = getWidth() / (NDECADES);
		for (int i = 0; i < NDECADES; i++) {
			double x = (i + 1) * windowWidth;
			int decade = START_DECADE + i * 10;
			GLine line = new GLine(x, 0, x, getHeight());
			GLabel label = new GLabel("" + decade);
			if (x <= getWidth() - windowWidth) {
				add(line);
			}
			add(label, x - windowWidth, getHeight() - label.getAscent() / 2);
		}
	}

	// method to implement margins
	private void margins() {
		GLine top = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(),
				GRAPH_MARGIN_SIZE);
		GLine bottom = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE,
				getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
		add(top);
		add(bottom);
	}

	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) {
	}

	public void componentMoved(ComponentEvent e) {
	}

	public void componentResized(ComponentEvent e) {
		update();
	}

	public void componentShown(ComponentEvent e) {
	}
}
