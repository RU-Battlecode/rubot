package rubot.src.logic;

import battlecode.common.Clock;
import battlecode.common.RobotController;

/**
 * RobotLogic.java - Base robot AI class.
 * This class holds shared code between all robot types. 
 * Created by Travis on 9/26/2016.
 */
public abstract class RobotLogic {
    protected RobotController rc;
    protected boolean active;
     
    /**
     * Called on robot creation in RobotPlayer.java.
     * Starts the robots active logic loop, that pauses the thead on completion.
     */
    public void run() {
    	active = true;
    	while (active) {
    		logic();
    		Clock.yield();
    	}
    }
    
    /**
     * This method should be overridden with the unique logic for each bot type.
     */
    public abstract void logic();

    public void setRc(RobotController _rc) {
        rc = _rc;
    }
}
