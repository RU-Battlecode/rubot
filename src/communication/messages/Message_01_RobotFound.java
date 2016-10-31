package rubot.src.communication.messages;

import battlecode.common.MapLocation;
import rubot.src.communication.FixedBitSet;
import rubot.src.communication.SignedFixedBitSet;

/**
 * 
 * data[0]:
 *   type  priority  robotId
 * |-------|-------|-----------------|
 * | 00000 |   01  | 1111111111111   |
 * |-------|-------|-----------------|
 * 
 * data[1]:
 *  location
 * |---------------------------------|
 * | 1111111111111111111111111111111 |
 * |---------------------------------|
 * 
 * @author Ben
 */
public class Message_01_RobotFound extends Message {

	private int robotId;
	private static final int ROBOT_ID_SIZE = 13; // bits

	private MapLocation location;
	private static final int MAP_LOCATION_SIZE = 32; // bits

	/**
	 * Encode
	 */
	public Message_01_RobotFound(int _priority, int _robotId, MapLocation _location) {
		super(MessageType.ROBOT_FOUND, _priority);
		robotId = _robotId;
		location = _location;

		FixedBitSet robotIdBits = new FixedBitSet(ROBOT_ID_SIZE, robotId);
		SignedFixedBitSet locationBits = new SignedFixedBitSet(MAP_LOCATION_SIZE,
				MessageUtil.mapLocationToInt(location));
		data[0].append(robotIdBits);
		data[1].append(locationBits);
	}

	/**
	 * Decode
	 */
	public Message_01_RobotFound(int _data) {
		super(_data);
		robotId = data[0].pop(ROBOT_ID_SIZE).toInt();
		location = MessageUtil.intToMapLocation(((SignedFixedBitSet) data[1].pop(MAP_LOCATION_SIZE)).toInt());
	}

	public int getRobotId() {
		return robotId;
	}

	public MapLocation getLocation() {
		return location;
	}

}
