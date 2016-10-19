package rubot.src.communication;

public final class MessageMath {

	/**
	 * Don't let anyone instantiate this class.
	 */
	private MessageMath() {}
	
	public static FixedBitSet toBitSet(int num, int size) {
		FixedBitSet bits = new FixedBitSet(size);
		int i = 0;
		while (num != 0) {
			if (num % 2 != 0) {
				bits.set(i);
			}
			i++;
			num = num >>> 1;
		}
		return bits;
	}
}
