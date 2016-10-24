package rubot;

import battlecode.common.RobotController;
import rubot.src.logic.ArchonLogic;
import rubot.src.logic.GuardLogic;
import rubot.src.logic.RobotLogic;
import rubot.src.logic.ScoutLogic;
import rubot.src.logic.SoldierLogic;
import rubot.src.logic.TurretLogic;
import rubot.src.logic.ViperLogic;

/**
 * Created by Travis on 9/26/2016.
 */
public class RobotPlayer {

	public static void run(RobotController _rc) {
		RobotLogic logic = null;
		switch (_rc.getType()) {
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

		logic.setRc(_rc);
		logic.run();
	}
	
}
