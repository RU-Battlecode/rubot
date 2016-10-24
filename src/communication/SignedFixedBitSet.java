package rubot.src.communication;
/**
 * SignedFixedBitSet.java - A binary representation of any size integer.
 * This allows you to make [x] bit integers because java limits you to:
 * Long   64 bit
 * Int    32 bit
 * Short  16 bit
 * Byte    8 bit
 * Boolean 1 bit?
 * 
 * SignedFixedBitSet [x] bit ;)
 * SignedXBitInteger
 * @author Ben
 *
 */
public class SignedFixedBitSet extends FixedBitSet {

	/**
	 * 
	 * @param size - number of bits that the integer has
	 * @param num - The starting value of the integer
	 */
	public SignedFixedBitSet(int size, int num) {
		super(size, num < 0 ? Math.abs(num + 1) : num);

		if (num < 0) {
			flipAll();
			set(0);
		}
	}

	public SignedFixedBitSet(int size) {
		this(size, 0);
	}

	public SignedFixedBitSet(String bitString) {
		super(bitString);
	}

	public boolean isNegative() {
		return bits.length > 0 && bits[0];
	}

	public int getMinValue() {
		return -getMaxValue() - 1;
	}

	public int getMaxValue() {
		return (int) (Math.pow(2, getSize() - 1) - 1);
	}
	
	public int toInt() {
		int value = super.toInt();
		return isNegative() ? (value - 1) - super.getMaxValue() : value;
	}

}
