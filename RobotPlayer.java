package rubot;

import battlecode.common.RobotController;
import rubot.logic.ArchonLogic;
import rubot.logic.GuardLogic;
import rubot.logic.RobotLogic;
import rubot.logic.ScoutLogic;
import rubot.logic.SoldierLogic;
import rubot.logic.TurretLogic;
import rubot.logic.ViperLogic;

/**
 * Created by Travis on 9/26/2016.
 */
public class RobotPlayer {

    public static void run(RobotController rc){

        RobotLogic logic = null;
        switch (rc.getType()){
            case ARCHON:
                logic = new ArchonLogic();
                break;
            case SCOUT:
            	logic = new ScoutLogic();
                break;
            case SOLDIER:
            	logic = new SoldierLogic();
                break;
            case GUARD:
            	logic = new GuardLogic();
                break;
            case VIPER:
            	logic = new ViperLogic();
                break;
            case TURRET:
            case TTM:
            	logic = new TurretLogic();
                break;
            default:
                System.out.println("Missing logic");
        }

        logic.setRc(rc);
        logic.run();
    }
}
