package rubot.src.communication;

import java.util.BitSet;

public class FixedBitSet extends BitSet {
	private static final long serialVersionUID = 1L;

	private int fixedSize;
	
	public FixedBitSet(int size) {
		super(size);
		fixedSize = size;
	}
	
	public FixedBitSet(String bits) {
		this(bits.length());
		if (!bits.matches("[0-1]*")) {
			throw new IllegalArgumentException("bit strings can only be 1's and 0's");
		}
		set(bits);
	}
	
	public void rightShift(int n) {
		if (n == 0) {
			return;
		} else if (n < 0) {
	        throw new IllegalArgumentException("'n' must be >= 0");
		} 
		
	    
	}
	
	public void append(FixedBitSet bits) {
		if (fixedSize + bits.fixedSize > Integer.SIZE) {
			throw new RuntimeException("Message too large. " + (fixedSize + bits.fixedSize)
			+ " is greater than Integer.size("+Integer.SIZE+")!");
		}
		
		rightShift(bits.fixedSize);
		
		for (int i = 0; i < bits.fixedSize; i++) {
			set(i, bits.get(i));
		}
	}
	
	private void set(long[] array) {
		for (int i = 0; i < array.length; i++) {
			set(fixedSize - 1 - i, array[i] == 1);
		}
	}
	
	private void set(String bits) {
		for (int i = 0; i < bits.length(); i++) {
			set(fixedSize - 1 - i, bits.charAt(i) == '1');
		}
	}
	
	public int getFixedSize() {
		return fixedSize;
	}

	public int toInt() {
		int result = 0;
		for (int i = 0; i < fixedSize; i++) {
			result |= get(i) ? (1 << i) : 0;
		}
		return result;
	}
	
	public String toString() {
		String result = "";
		for (int i = fixedSize - 1; i >= 0; i--) {
			result += get(i) ? '1' : '0';
		}
		return result;
	}
}
