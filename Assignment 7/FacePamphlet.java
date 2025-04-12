/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends Program implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the interactors in
	 * the application, and taking care of any other initialization that needs
	 * to be performed.
	 */
	public void init() {
		canvas = new FacePamphletCanvas();
		add(canvas);

		addButtons();

		statusField.addActionListener(this);
		pictureField.addActionListener(this);
		friendField.addActionListener(this);
		addActionListeners();
	}

	// this method ensures to add buttons on canvas
	private void addButtons() {
		add(nameLabel, NORTH);
		add(nameField, NORTH);
		add(addButton, NORTH);
		add(deleteButton, NORTH);
		add(lookupButton, NORTH);

		add(statusField, WEST);
		add(statusButton, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);

		add(pictureField, WEST);
		add(pictureButton, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);

		add(friendField, WEST);
		add(friendButton, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);

	}

	/**
	 * This class is responsible for detecting when the buttons are clicked or
	 * interactors are used, so you will have to add code to respond to these
	 * actions.
	 */
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String tempName = nameField.getText();
		if (source == addButton) {
			addAction(tempName);
		} else if (source == deleteButton) {
			deleteAction(tempName);
		} else if (source == lookupButton) {
			lookupAction(tempName);
		} else if (source == statusButton || source == statusField) {
			statusAction(tempName);
		} else if (source == pictureButton || source == pictureField) {
			pictureAction(tempName);
		} else if (source == friendButton || source == friendField) {
			friendAction(tempName);
		}
	}

	// this method adds friends if both of the profiles are available and are
	// not friends yet!else gives the corresponding messagebox
	private void friendAction(String tempName) {
		String friend = friendField.getText();
		if (currProfile != null) {
			if (!friend.isEmpty() && !friend.equals(currProfile.getName())) {
				if (db.containsProfile(friend)) {
					if (db.getProfile(friend).addFriend(tempName)) {
						db.getProfile(tempName).addFriend(friend);
						canvas.displayProfile(db.getProfile(tempName));
						canvas.showMessage(friend + " has been added!");
					} else {
						canvas.showMessage(tempName + " and " + friend
								+ " are already friends!");
					}
				} else {
					canvas
							.showMessage("Indicated friend profile does not exist, can not add");
				}
			} else {
				canvas
						.showMessage("Enter a valid profile name to add as a friend!");
			}
		} else {
			canvas.showMessage("No current profile selected!");
		}

	}

	// this method changes profile picture if both profile and picture filename
	// exist in DataBase otherwise displays corresponding messagebox
	private void pictureAction(String tempName) {
		if (currProfile != null) {
			if (db.containsProfile(tempName)
					&& !pictureField.getText().isEmpty()) {
				GImage image = null;
				try {
					image = new GImage(pictureField.getText());
					db.getProfile(tempName).setImage(image);
					canvas.displayProfile(db.getProfile(tempName));
				} catch (ErrorException ex) {
					canvas.showMessage("Unable to access image file: "
							+ pictureField.getText());
				}

				if (image != null) {
					canvas.showMessage("Picture has been changed!");
				} else {
					canvas
							.showMessage("Indicated picture does not exist,picture remains unchanged!");
				}

			} else {
				canvas
						.showMessage("Indicated picture does not exist, picture can not be changed!");
			}
		} else {
			canvas.showMessage("No current profile selected!");
		}
	}

	// this method changes profile status if profile exists in DataBase
	// if status is empty it will set default to 'No current status'
	private void statusAction(String tempName) {
		if (currProfile != null) {
			if (db.containsProfile(tempName)) {
				String stat = statusField.getText();
				if (stat.isEmpty()) {
					stat = "No current status";
				}
				db.getProfile(tempName).setStatus(stat);
				canvas.displayProfile(db.getProfile(tempName));
				canvas.showMessage("Status updated to: " + stat);
			} else {
				canvas
						.showMessage("Indicated profile does not exist, status can not be changed!");
			}
		} else {
			canvas.showMessage("No current profile selected!");
		}
	}

	// this method looks up whether indicated profile exists in Database and
	// displays it
	private void lookupAction(String tempName) {
		if (!tempName.isEmpty()) {
			if (db.containsProfile(tempName)) {
				canvas.showMessage("lookup"
						+ db.getProfile(tempName).toString());
				canvas.displayProfile(db.getProfile(tempName));
				canvas.showMessage("Displaying " + tempName);
			} else {
				canvas.showMessage("A profile with a name " + tempName
						+ " does not exist!");
			}
		} else {
			canvas.showMessage("Enter a valid name to lookup the profile!");
		}

	}

	// this method deletes the existing profile in database and clears the
	// canvas
	private void deleteAction(String tempName) {
		if (!tempName.isEmpty()) {
			if (db.containsProfile(tempName)) {
				db.deleteProfile(tempName);
				canvas.removeAll();
				canvas.showMessage("The profile of " + tempName
						+ " has been deleted!");
			} else {
				canvas.showMessage("A profile with a name " + tempName
						+ " does not exist!");
			}
		} else {
			canvas.showMessage("Enter a valid name to delete the profile!");
		}
	}

	// this method ensures to add a new profile in database and puts it on
	// display
	private void addAction(String tempName) {
		if (!tempName.isEmpty()) {
			if (!db.containsProfile(tempName)) {
				FacePamphletProfile newProfile = new FacePamphletProfile(
						tempName);
				db.addProfile(newProfile);
				currProfile = newProfile;
				canvas.displayProfile(currProfile);
				canvas.showMessage("New profile has been created");
			} else {
				canvas.displayProfile(db.getProfile(tempName));
				canvas.showMessage("A profile with the name " + tempName
						+ " already exists!");
			}
		} else {
			canvas.showMessage("Enter a valid name to add the profile!");
		}
	}

	// private variable section

	private JButton addButton = new JButton("Add");

	private JButton deleteButton = new JButton("Delete");

	private JButton lookupButton = new JButton("Lookup");

	private JButton statusButton = new JButton("Change Status");

	private JButton pictureButton = new JButton("Change Picture");

	private JButton friendButton = new JButton("Add Friend");

	private JTextField nameField = new JTextField(TEXT_FIELD_SIZE);

	private JTextField statusField = new JTextField(TEXT_FIELD_SIZE);

	private JTextField pictureField = new JTextField(TEXT_FIELD_SIZE);

	private JTextField friendField = new JTextField(TEXT_FIELD_SIZE);

	private JLabel nameLabel = new JLabel("Name");

	private FacePamphletDatabase db = new FacePamphletDatabase();

	private FacePamphletCanvas canvas;

	private FacePamphletProfile currProfile;
}
