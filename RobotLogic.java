package RUBot;

import battlecode.common.RobotController;

/**
 * Created by Travis on 9/26/2016.
 */
public abstract class RobotLogic {
    protected RobotController rc;

    public abstract void run();

    public void setRc(RobotController _rc) {
        rc = _rc;
    }
}
