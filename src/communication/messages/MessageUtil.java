package rubot.src.communication.messages;

import battlecode.common.MapLocation;

public class MessageUtil {

	public static int mapLocationToInt(MapLocation location) {
		return location.x << 16 | location.y;		
	}
	
	public static MapLocation intToMapLocation(int data) {
		return new MapLocation(data >> 16, data & 0xffff);
	}
		
}
