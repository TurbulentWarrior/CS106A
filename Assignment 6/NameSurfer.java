/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base and
	 * initializing the interactors at the bottom of the window.
	 */
	public void init() {

		add(graph);
		add(nameLabel, SOUTH);
		add(nameField, SOUTH);
		add(graphButton, SOUTH);
		add(clearButton, SOUTH);
		addActionListeners();
		nameField.addActionListener(this);

	}

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are clicked, so
	 * you will have to define a method to respond to button actions.
	 */
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == graphButton || source == nameField) {
			graphAct();
		} else if (source == clearButton) {
			clearAct();
		}
	}

	// this method ensures to clear the canvas
	private void clearAct() {
		// println("Clear");
		graph.clear();
	}

	// this method ensures to draw a plot for the indicated name
	private void graphAct() {
		String name = nameField.getText();
		surfer = db.findEntry(name);
		if (surfer != null) {
			// println(surfer.toString());
			graph.addEntry(surfer);
			graph.update();
		} else {
			println("indicated name does not exist in database");
		}

	}

	private NameSurferEntry surfer;

	private JButton graphButton = new JButton("Graph");

	private JButton clearButton = new JButton("Clear");

	private JLabel nameLabel = new JLabel("Name");

	private JTextField nameField = new JTextField(10);

	private NameSurferDataBase db = new NameSurferDataBase(NAMES_DATA_FILE);

	private NameSurferGraph graph = new NameSurferGraph();
}
