package rubot.src.communication.messages;

import rubot.src.communication.FixedBitSet;

/**
 * Message.java - Represents a base message class that is
 * contains two 32 bit integers of data.
 * 
 * Example:
 *  type  priority  other message type specific args
 *  
 * data[0]:
 * |-------|-------|-----------------|
 * | 00000 |   01  | (25 other bits) |
 * |-------|-------|-----------------|
 * 
 * data[1]:
 * |---------------------------------|
 * | (32 other bits)                 |
 * |---------------------------------|
 * 
 * @author Ben
 */
public abstract class Message {
	/**
	 * 5 bit integer
	 */
	protected MessageType type;
	protected static final int TYPE_SIZE = 5;
	
	/**
	 * 2 bit integer [0, 3]
	 */
	protected int priority;
	protected static final int PRIORITY_SIZE = 2;
	
	protected FixedBitSet[] data;
	
	/**
	 * Encode
	 */
	public Message(MessageType _type, int _priority) {
		type = _type;
		priority = _priority;
		
		data[0] = new FixedBitSet(Integer.SIZE);
		data[1] = new FixedBitSet(Integer.SIZE);
		
		FixedBitSet typeBits = new FixedBitSet(TYPE_SIZE, type.ordinal());
		FixedBitSet priortyBits = new FixedBitSet(PRIORITY_SIZE, priority);
		
		data[0].append(new FixedBitSet[] {typeBits, priortyBits});
	}
	
	/**
	 * Decode
	 */
	public Message(int _data) {
		data[0] = new FixedBitSet(Integer.SIZE, _data);
		type = MessageType.values()[(data[0].pop(TYPE_SIZE).toInt())];
		priority = data[0].pop(PRIORITY_SIZE).toInt();
	}
	
	public int[] getData() {
		return new int[] { data[0].toInt(), data[1].toInt()};
	}
	
}
