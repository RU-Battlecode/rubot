package rubot;

import battlecode.common.Clock;
import battlecode.common.GameActionException;
import battlecode.common.RobotInfo;


/**
 * Soldier range: 13
 * Created by Travis on 9/26/2016.
 */
public class SoldierLogic extends RobotLogic {

    RobotInfo[] inRangeEnemies;
    Constants constants;

    @Override
    public void run() {
        while (true) {
            inRangeEnemies = rc.senseHostileRobots(rc.getLocation(), constants.SOLDIER_RANGE);
            attack();

            Clock.yield();
        }
    }

    void attack() {
        if (rc.isCoreReady() && rc.isWeaponReady()){
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
}
