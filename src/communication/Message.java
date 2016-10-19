package rubot.src.communication;

import java.util.BitSet;

/**
 * 
 * 
 * Example:
 *  type  priority  other message type specific args
 * |----|-------|---------------
 * | 12 |   9   | 123449
 * |----|-------|---------------
 * 
 * max digits: 0_000_000_000
 * 
 * @author Ben
 */
public abstract class Message {
	/*
	 * 5 bit integer
	 */
	protected MessageType type;
	
	/*
	 * 2 bit integer [0, 3]
	 */
	protected int priority; 
	
	// Decode
	public Message(int data) {
		String message = data + "";
		
	}
	
	public FixedBitSet encode() {
		FixedBitSet bits = new FixedBitSet(Integer.SIZE);
		
		return bits;
	}
	
}
