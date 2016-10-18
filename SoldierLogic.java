package rubot;

import battlecode.common.*;


/**
 * Soldier range: 13 Created by Travis on 9/26/2016.
 */
public class SoldierLogic extends RobotLogic {

	RobotInfo[] inRangeEnemies;

	@Override
	public void run() {
		while (true) {
			inRangeEnemies = rc.senseHostileRobots(rc.getLocation(), rc.getType().attackRadiusSquared);
			attack();
			move();
			
			Clock.yield();
		}
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
		MapLocation[] locations = rc.getInitialArchonLocations(rc.getTeam().opponent());
			
		Direction dir = rc.getLocation().directionTo(locations[0]); 
		MapLocation moveTo = rc.getLocation().add(dir);
		
		if (rc.canMove(dir)) {
			try {
				rc.move(dir);
			} catch (GameActionException e) {
				e.printStackTrace();
			}
		} else if (rc.senseRubble(moveTo) >= GameConstants.RUBBLE_OBSTRUCTION_THRESH) {
			try {
				rc.clearRubble(dir);
			} catch (GameActionException e) {
				e.printStackTrace();
			}
		} else if (rc.canMove(dir.rotateRight())) {
			try {
				rc.move(dir.rotateRight());
			} catch (GameActionException e) {
				e.printStackTrace();
			}
		}
		
	}
}
