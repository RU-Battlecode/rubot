package rubot;

import battlecode.common.*;

import static battlecode.common.RobotType.SOLDIER;

/**
 * Created by Travis on 9/26/2016.
 */
public class ArchonLogic extends RobotLogic {

    @Override
    public void logic() {
        determineState();
        boolean buildSuccess = build(SOLDIER);  
    }

    public void determineState(){

    }
    
    public boolean build(RobotType type) {
        boolean hasBuilt = false;
        int i = 0;
        while (!hasBuilt && i < Direction.values().length) {
            Direction dir = Direction.values()[i];
            if (rc.isCoreReady() && rc.canBuild(dir, type)) {
                try {
                    rc.build(dir, type);
                    hasBuilt = true;
                } catch (GameActionException e) {
                    e.printStackTrace();
                }
               
            }
            
            i++;
        }
        return hasBuilt;
    }
    
}
