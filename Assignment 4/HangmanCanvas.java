/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;
import acm.util.ErrorException;

public class HangmanCanvas extends GCanvas {

	/** Resets the display so that only the scaffold appears */
	public void reset() {		
		removeAll();
		incorrect = "";
		add(scaffold());
		shifrLabel.setLabel("");
		add(shifrLabel);
	}

	/**
	 * Updates the word on the screen to correspond to the current state of the
	 * game. The argument string shows what letters have been guessed so far;
	 * unguessed letters are indicated by hyphens.
	 */
	public void displayWord(String word) {
		add(shifrLabel, LABEL_OFFSET_X, LABEL_OFFSET_Y);
		shifrLabel.setLabel(word);
		
	}

	/**
	 * Updates the display to correspond to an incorrect guess by the user.
	 * Calling this method causes the next body part to appear on the scaffold
	 * and adds the letter to the list of incorrect guesses that appears at the
	 * bottom of the window.
	 */
	public void noteIncorrectGuess(char letter) {
		add(incorrectLabel, LABEL_OFFSET_1_X, LABEL_OFFSET_1_Y);
		incorrect = incorrect + Character.toUpperCase(letter);
		incorrectLabel.setLabel(incorrect);
		
		displayHangman(incorrect);
	}

	// This method draws a hangman in according progression in game
	private void displayHangman(String word) {
		int i = word.length();

		switch (i) {
		case 1:
			add(head);
			break;
		case 2:
			add(fullBody());
			break;
		case 3:
			add(leftHand());
			break;
		case 4:
			add(rightHand());
			break;
		case 5:
			add(leftLeg);
			break;
		case 6:
			add(rightLeg);
			break;
		case 7:
			add(leftFoot);
			break;
		case 8:
			add(rightFoot);
			break;
		}

	}

	/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;

	private static final int BEAM_LENGTH = 144;

	private static final int ROPE_LENGTH = 18;

	private static final int HEAD_RADIUS = 36;

	private static final int BODY_LENGTH = 144;

	private static final int ARM_OFFSET_FROM_HEAD = 28;

	private static final int UPPER_ARM_LENGTH = 72;

	private static final int LOWER_ARM_LENGTH = 44;

	private static final int HIP_WIDTH = 72;

	private static final int LEG_LENGTH = 108;

	private static final int FOOT_LENGTH = 28;

	private static final int X_OFFSET = 50;

	private static final int Y_OFFSET = 70;

	private static final int LABEL_OFFSET_X = 20;

	private static final int LABEL_OFFSET_Y = 450;

	private static final int LABEL_OFFSET_1_X = 20;

	private static final int LABEL_OFFSET_1_Y = 465;

	// Following objects are drawn on canvas as follows:
	// scaffold
	private GCompound scaffold() {
		GCompound scaffold = new GCompound();

		GLine scaffoldBody = new GLine(X_OFFSET, Y_OFFSET, X_OFFSET, Y_OFFSET
				+ SCAFFOLD_HEIGHT);
		GLine beam = new GLine(X_OFFSET, Y_OFFSET, X_OFFSET + BEAM_LENGTH,
				Y_OFFSET);
		GLine rope = new GLine(X_OFFSET + BEAM_LENGTH, Y_OFFSET, X_OFFSET
				+ BEAM_LENGTH, Y_OFFSET + ROPE_LENGTH);

		scaffold.add(scaffoldBody);
		scaffold.add(beam);
		scaffold.add(rope);

		return scaffold;
	}

	// head
	private GOval head = new GOval(X_OFFSET + BEAM_LENGTH - HEAD_RADIUS,
			Y_OFFSET + ROPE_LENGTH, 2 * HEAD_RADIUS, 2 * HEAD_RADIUS);

	// body and waist
	private GCompound fullBody() {
		GCompound bo = new GCompound();

		GLine body = new GLine(X_OFFSET + BEAM_LENGTH, Y_OFFSET + ROPE_LENGTH
				+ 2 * HEAD_RADIUS, X_OFFSET + BEAM_LENGTH, Y_OFFSET
				+ ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH);
		GLine waist = new GLine(X_OFFSET + BEAM_LENGTH - HIP_WIDTH / 2,
				Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH,
				X_OFFSET + BEAM_LENGTH + HIP_WIDTH / 2, Y_OFFSET + ROPE_LENGTH
						+ 2 * HEAD_RADIUS + BODY_LENGTH);
		bo.add(body);
		bo.add(waist);
		return bo;
	}

	// left hand
	private GCompound leftHand() {
		GCompound lh = new GCompound();
		GLine upperArm = new GLine(
				X_OFFSET + BEAM_LENGTH - UPPER_ARM_LENGTH,
				Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
				X_OFFSET + BEAM_LENGTH, Y_OFFSET + ROPE_LENGTH + 2
						* HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
		GLine lowerArm = new GLine(
				X_OFFSET + BEAM_LENGTH - UPPER_ARM_LENGTH,
				Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
				X_OFFSET + BEAM_LENGTH - UPPER_ARM_LENGTH, Y_OFFSET
						+ ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD
						+ LOWER_ARM_LENGTH);
		lh.add(upperArm);
		lh.add(lowerArm);
		return lh;
	}

	// right hand
	private GCompound rightHand() {
		GCompound rh = new GCompound();
		GLine upperArm = new GLine(
				X_OFFSET + BEAM_LENGTH + UPPER_ARM_LENGTH,
				Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
				X_OFFSET + BEAM_LENGTH, Y_OFFSET + ROPE_LENGTH + 2
						* HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
		GLine lowerArm = new GLine(
				X_OFFSET + BEAM_LENGTH + UPPER_ARM_LENGTH,
				Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
				X_OFFSET + BEAM_LENGTH + UPPER_ARM_LENGTH, Y_OFFSET
						+ ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD
						+ LOWER_ARM_LENGTH);
		rh.add(upperArm);
		rh.add(lowerArm);
		return rh;
	}

	// right leg and foot
	private GLine rightLeg = new GLine(X_OFFSET + BEAM_LENGTH - HIP_WIDTH / 2,
			Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH, X_OFFSET
					+ BEAM_LENGTH - HIP_WIDTH / 2, Y_OFFSET + ROPE_LENGTH + 2
					* HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);

	private GLine rightFoot = new GLine(X_OFFSET + BEAM_LENGTH - HIP_WIDTH / 2
			- FOOT_LENGTH, Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS
			+ BODY_LENGTH + LEG_LENGTH, X_OFFSET + BEAM_LENGTH - HIP_WIDTH / 2,
			Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);

	// left leg and foot
	private GLine leftLeg = new GLine(X_OFFSET + BEAM_LENGTH + HIP_WIDTH / 2,
			Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH, X_OFFSET
					+ BEAM_LENGTH + HIP_WIDTH / 2, Y_OFFSET + ROPE_LENGTH + 2
					* HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);

	private GLine leftFoot = new GLine(X_OFFSET + BEAM_LENGTH + HIP_WIDTH / 2
			+ FOOT_LENGTH, Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS
			+ BODY_LENGTH + LEG_LENGTH, X_OFFSET + BEAM_LENGTH + HIP_WIDTH / 2,
			Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);

	//mistake detector string
	private String incorrect = "";

	//label variables
	private GLabel shifrLabel = new GLabel("");

	private GLabel incorrectLabel = new GLabel("");
}
