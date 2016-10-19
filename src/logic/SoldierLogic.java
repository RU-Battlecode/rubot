package rubot.src.logic;

import battlecode.common.*;

/**
 * Soldier range: 13 Created by Travis on 9/26/2016.
 */
public final class SoldierLogic extends RobotLogic {

	RobotInfo[] inRangeEnemies;

	@Override
	public void logic() {
		inRangeEnemies = rc.senseHostileRobots(rc.getLocation(), rc.getType().attackRadiusSquared);
		attack();
		move();
	}

	void attack() {
		if (inRangeEnemies.length > 0 && rc.isCoreReady() && rc.isWeaponReady()) {
			double lowestHealth = inRangeEnemies[0].health;
			int lowestHealthIndex = 0;
			for (int i = 0; i < inRangeEnemies.length; i++) {
				if (inRangeEnemies[i].health < lowestHealth) {
					lowestHealth = inRangeEnemies[i].health;
					lowestHealthIndex = i;
				}
			}
			try {
				if (rc.canAttackLocation(inRangeEnemies[lowestHealthIndex].location))
					rc.attackLocation(inRangeEnemies[lowestHealthIndex].location);
			} catch (GameActionException e) {
				e.printStackTrace();
			}
		}
	}

	void move() {
		if (rc.isCoreReady()) {
			// Get array of all enemy archon locations.
			MapLocation[] locations = rc.getInitialArchonLocations(rc.getTeam().opponent());

			// Calculate direction to enemy archon location.
			Direction dir = rc.getLocation().directionTo(locations[0]);

			// Get the map location we are trying to move to.
			MapLocation moveTo = rc.getLocation().add(dir);
			try {
				// Try move.
				if (rc.canMove(dir)) {
					rc.move(dir);
				}
				// Check if rubble is obstructing path.
				else if (rc.senseRubble(moveTo) >= GameConstants.RUBBLE_OBSTRUCTION_THRESH) {
					rc.clearRubble(dir);
				}
				// Try to move to the next direction to the right. if dir is
				// NORTH
				// then NORTH_EAST.
				else if (rc.canMove(dir.rotateRight())) {
					rc.move(dir.rotateRight());
				}
			} catch (GameActionException e) {
				e.printStackTrace();
			}
		}
	}
}
