package rubot.src.communication;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import battlecode.common.Signal;
import rubot.src.communication.messages.Message;

public class Radio {

	private RobotController rc;
	
	public Radio(RobotController _rc) {
		rc = _rc;
	}
	
	public void sendMessage(Message msg, int radiusSquared) {
		try {
			rc.broadcastMessageSignal(msg.getData()[0], msg.getData()[1], radiusSquared);
		} catch (GameActionException e) {
			e.printStackTrace();
		}
	}
	
	public Signal[] readMessages() {
		return rc.emptySignalQueue();
	}
	
	public Signal[] readMessages(int max) {
		Signal[] messages = new Signal[max];
		int i = 0;
		boolean done = false;
		while (i < max && !done) {
			messages[i] = rc.readSignal();
					
			if (messages[i] != null) {
				done = true;
			}
			i++;
		}
		return messages;
	}
	
}
