/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;

	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;

	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;

	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1)
			* BRICK_SEP)
			/ NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;
	private static final int DELAY = 10;
	/** private instance variables * */
	// objects:
	private GOval ball;

	private GRect paddle;

	// velocities:
	private double vx, vy;

	private int turns, brickNum;

	/* Method: run() */
	/** Runs the Breakout program. */
	public void run() {
		boardInit();
		addMouseListeners();
		gamePlay();

	}

	private void gamePlay() {
		pitchBall();
		finalMessage();
	}

	// this method generates the final title on the screen
	private void finalMessage() {
		remove(ball);
		if (turns == 0) {
			GLabel label = new GLabel("you have lost the game, please retry!");
			add(label, getWidth() / 2 - label.getWidth() / 2, getHeight() / 2
					- label.getAscent());
		} else if (brickNum == 0) {
			GLabel label = new GLabel(
					"you have cleared the breakout,congratulations!");
			add(label, getWidth() / 2 - label.getWidth() / 2, getHeight() / 2
					- label.getAscent());
		}
	}

	// This method drops ball from the center of the screen and starts the game
	private void pitchBall() {

		makeBall();
		while (brickNum > 0 && turns > 0) {
			checkCollision();
			ball.move(vx, vy);
			pause(DELAY);
			if (ball.getY() + 2 * BALL_RADIUS >= getHeight()) {
				turns--;
				remove(ball);
				makeBall();
			}

		}
	}

	// this method checks whether ball collides or not with an object
	private void checkCollision() {
		wallCollision();
		collision();

	}

	// collision with bricks and the paddle
	private void collision() {
		paddleCollision();
		brickCollision();

	}

	// This method checks for brick collision
	private void brickCollision() {
		GObject collider=getCollidingObject(ball.getX(),ball.getY());
		if (collider!= null) {
			remove(collider);
			vy *=-1;
			brickNum--;
			}
	}

	// This method checks for paddleCollision
	private void paddleCollision() {
		if (getCollidingObject(ball.getX(), ball.getY()) == paddle) {
			ball.setLocation(ball.getX(), paddle.getY() - 2 * BALL_RADIUS - 1);
			vy *= -1;
		}
	}

	
	//this method tests whether ball enclosed in a circle hits any object
	//note that we can only hit paddle while vy>0 thats why we only consider those 2 cases
	private GObject getCollidingObject(double a, double b) {
		GObject collider;
		if (vy < 0) {

			collider = getElementAt(a, b);
			if (collider == null) {
				collider = getElementAt(a + 2 * BALL_RADIUS, b);
			}
		} else {
			collider = getElementAt(a, b + 2 * BALL_RADIUS);
			if (collider == null) {
				collider = getElementAt(a + 2 * BALL_RADIUS, b + 2
						* BALL_RADIUS);
			}
		}

		return collider;
	}

	// collision with walls
	private void wallCollision() {
		// upperwall
		if (ball.getY() <= 0) {
			ball.setLocation(ball.getX(), 0);
			vy *= -1;
		}
		// left wall & right wall
		if (ball.getX() <= 0) {
			ball.setLocation(0, ball.getY());
			vx *= -1;
		} else if (ball.getX() + 2 * BALL_RADIUS >= getWidth()) {
			ball.setLocation(getWidth() - 2 * BALL_RADIUS, ball.getY());
			vx *= -1;
		}
	}

	// this method generates paddle and set of bricks on empty canvas
	private void boardInit() {
		turns = NTURNS;
		brickNum = NBRICKS_PER_ROW * NBRICK_ROWS;
		makePaddle();
		makeBricks();
	}

	// this method draws bricks on canvas
	private void makeBricks() {
		for (int i = 1; i <= NBRICK_ROWS; i++) {
			for (int j = 1; j <= NBRICKS_PER_ROW; j++) {
				double tempX, tempY;
				tempX = 1 + (j - 1) * BRICK_WIDTH + (j - 1) * BRICK_SEP;
				tempY = BRICK_Y_OFFSET + (i - 1) * BRICK_HEIGHT + (i - 1)
						* BRICK_SEP;
				GRect brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
				brick.setFilled(true);
				brick.setFillColor(brickColor(i));
				add(brick, tempX, tempY);
			}
		}
	}

	private Color brickColor(int i) {
		Color col = null;
		if (i < 3) {
			col = Color.red;
		}

		if (2 < i && i < 5) {
			col = Color.orange;
		}

		if (4 < i && i < 7) {
			col = Color.yellow;
		}

		if (6 < i && i < 9) {
			col = Color.green;
		}

		if (9 <= i) {
			col = Color.cyan;
		}

		return col;
	}

	// this method draws paddle on canvas
	private void makePaddle() {
		paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle, WIDTH / 2 - PADDLE_WIDTH / 2, getHeight() - PADDLE_HEIGHT
				- PADDLE_Y_OFFSET);

	}

	// this method draws ball on canvas
	private void makeBall() {
		ball = new GOval(2 * BALL_RADIUS, 2 * BALL_RADIUS);
		ball.setFilled(true);
		add(ball, WIDTH / 2 - BALL_RADIUS, getHeight() / 2 - BALL_RADIUS);
		vy = 3;
		vx = rangen.nextDouble(1, 3);
		if (rangen.nextBoolean(0.5)) {
			vx = -vx;
		}
	}

	// this method draws paddle on canvas
	public void mouseMoved(MouseEvent e) {
		if (e.getX() >= 0 && e.getX() <= getWidth() - PADDLE_WIDTH) {
			paddle.setLocation(e.getX(), getHeight() - PADDLE_HEIGHT
					- PADDLE_Y_OFFSET);
		}
	}

	// just a random generator
	private RandomGenerator rangen = new RandomGenerator();
}
