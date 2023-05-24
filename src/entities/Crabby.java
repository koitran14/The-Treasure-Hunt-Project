/*
Member:
1. Tran Ngoc Dang Khoi - ITCSIU21197
2. Nguyen Tran Hoang Ha - ITITIU21127
3. Ha Van Uyen Nhi - ITCSIU21095
4. Nguyen Hoang Quan - ITITIU21291

*Purpose: define a "Crabby" enemy's properties in a game. */

package entities;

import static utilz.Constants.EnemyConstants.*;
import static utilz.HelpMethods.IsFloor;
import static utilz.Constants.Dialogue.*;

import gamestates.Playing;

public class Crabby extends Enemy {

	public Crabby(float x, float y) {
		super(x, y, CRABBY_WIDTH, CRABBY_HEIGHT, CRABBY);
		initHitbox(22, 19);
		initAttackBox(82, 19, 30);
	}

	//update behavior + animations of crabby
	public void update(int[][] lvlData, Playing playing) {
		updateBehavior(lvlData, playing);
		updateAnimationTick(); //update animation frame
		updateAttackBox();
	}

	//behavior of crabby
	private void updateBehavior(int[][] lvlData, Playing playing) {
		if (firstUpdate)
			firstUpdateCheck(lvlData);

		if (inAir) {
			inAirChecks(lvlData, playing);
		} else {
			switch (state) {
				case IDLE:
					if (IsFloor(hitbox, lvlData))
						newState(RUNNING);
					else
						inAir = true;
					break;
				case RUNNING:
					if (canSeePlayer(lvlData, playing.getPlayer())) {
						turnTowardsPlayer(playing.getPlayer());
						if (isPlayerCloseForAttack(playing.getPlayer()))
							newState(ATTACK);
					}
					move(lvlData);

					if (inAir)
						playing.addDialogue((int) hitbox.x, (int) hitbox.y, EXCLAMATION);

					break;
				case ATTACK:
					if (aniIndex == 0)
						attackChecked = false;
					if (aniIndex == 3 && !attackChecked)
						checkPlayerHit(attackBox, playing.getPlayer(), playing);
					break;
				case HIT:
					if (aniIndex <= GetSpriteAmount(enemyType, state) - 2)
						pushBack(pushBackDir, lvlData, 2f);
					updatePushBackDrawOffset();
					break;
			}
		}
	}
}