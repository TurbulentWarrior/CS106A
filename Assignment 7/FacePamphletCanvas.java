/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */

import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas implements
		FacePamphletConstants {

	/**
	 * Constructor This method takes care of any initialization needed for the
	 * display
	 */
	public FacePamphletCanvas() {
		bottomMessage = new GLabel("");
	}

	/**
	 * This method displays a message string near the bottom of the canvas.
	 * Every time this method is called, the previously displayed message (if
	 * any) is replaced by the new message text passed in.
	 */
	public void showMessage(String msg) {
		bottomMessage.setLabel(msg);
		bottomMessage.setFont(MESSAGE_FONT);
		add(bottomMessage, getWidth() / 2 - bottomMessage.getWidth() / 2,
				getHeight() - BOTTOM_MESSAGE_MARGIN);
	}

	private GLabel bottomMessage;

	/**
	 * This method displays the given profile on the canvas. The canvas is first
	 * cleared of all existing items (including messages displayed near the
	 * bottom of the screen) and then the given profile is displayed. The
	 * profile display includes the name of the user from the profile, the
	 * corresponding image (or an indication that an image does not exist), the
	 * status of the user, and a list of the user's friends in the social
	 * network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		removeAll();
		if (profile != null) {
			addName(profile);
			addPfp(profile);
			addStatus(profile);
			addFriends(profile);
		}
	}

	// There are private helper methods below to add indicated components on
	// canvas at coresponding coordinates.
	private void addFriends(FacePamphletProfile profile) {
		GLabel fLabel = new GLabel("Friends:");
		fLabel.setFont(PROFILE_FRIEND_LABEL_FONT);
		add(fLabel, getWidth() / 2, TOP_MARGIN + IMAGE_MARGIN);

		Iterator<String> friendList = profile.getFriends();
		double y = TOP_MARGIN + IMAGE_MARGIN + FRIEND_GAP;
		while (friendList.hasNext()) {
			GLabel friendLabel = new GLabel(friendList.next());
			friendLabel.setFont(PROFILE_FRIEND_FONT);
			add(friendLabel, getWidth() / 2, y);
			y += FRIEND_GAP;
		}
	}

	private void addStatus(FacePamphletProfile profile) {
		GLabel status = new GLabel(profile.getStatus());
		status.setFont(PROFILE_STATUS_FONT);
		add(status, LEFT_MARGIN, TOP_MARGIN + IMAGE_MARGIN + IMAGE_HEIGHT
				+ STATUS_MARGIN);
	}

	private void addPfp(FacePamphletProfile profile) {
		GImage pfp = profile.getImage();
		if (pfp == null) {
			GRect frame = new GRect(IMAGE_WIDTH, IMAGE_HEIGHT);
			GLabel frameLabel = new GLabel("NO IMAGE");
			frameLabel.setFont(PROFILE_IMAGE_FONT);
			add(frame, LEFT_MARGIN, TOP_MARGIN + IMAGE_MARGIN);
			add(frameLabel, LEFT_MARGIN + IMAGE_WIDTH / 2
					- frameLabel.getWidth() / 2, TOP_MARGIN + IMAGE_MARGIN
					+ IMAGE_HEIGHT / 2);
		} else {
			pfp.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
			add(pfp, LEFT_MARGIN, TOP_MARGIN + IMAGE_MARGIN);
		}
	}

	private void addName(FacePamphletProfile profile) {
		GLabel nameLabel = new GLabel(profile.getName());
		nameLabel.setColor(Color.BLUE);
		nameLabel.setFont(PROFILE_NAME_FONT);
		add(nameLabel, LEFT_MARGIN, TOP_MARGIN);
	}

}
