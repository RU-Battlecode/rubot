package rubot.src.communication;

/**
 * MessageType.java - enum representing different types of messages.
 * *Note*: We can only have 32 unique messages because we are sending
 * them as 5bit integers (2^5 = 32).
 * 
 * @author Ben
 */
public enum MessageType {
	INVALID,
	ROBOT_FOUND;
}
