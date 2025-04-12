/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */


/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {
	public void run(){
		goToNewsPaper();
		pickNewsPaper();
		goBack();
			}

			private void goBack() {
				// this method returns karel to the initial point
				turnAround();
				moveThree();
				turnRight();
				move();
				turnRight();
			}

			private void pickNewsPaper() {
				// this method pick up a newspaper
				pickBeeper();
			}

			private void goToNewsPaper() {
				// this method lets karel to reach newspaper
				turnRight();
				move();
				turnLeft();
				
				moveThree();
			}

			private void moveThree() {
				// moves four plates
				for(int i=1;i<4;i++){
					move();
				}
			}
	}