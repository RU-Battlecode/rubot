package rubot.src.communication;
import java.util.Iterator;

public class FixedBitSet implements Iterable<Boolean> {

	protected boolean[] bits;

	public FixedBitSet(int size, int num) {
		bits = new boolean[size];

		int i = 0;
		while (num != 0) {
			if (num % 2 != 0) {
				set(size - 1 - i);
			}
			i++;
			num = num >>> 1;
		}
	}

	public FixedBitSet(int size) {
		this(size, 0);
	}

	public FixedBitSet(String bitString) {
		this(bitString.length());
		set(bitString);
	}

	public void rightShift(int n) {
		String newBitString = "";
		for (int i = 0; i < bits.length; i++) {
			if (i < n) {
				newBitString += '0';
			} else {
				newBitString += bits[i - n] ? '1' : '0';
			}
		}
		set(newBitString);
	}

	public void leftShift(int n) {
		String newBitString = "";

		for (int i = 0; i < bits.length; i++) {
			if (i + n < bits.length) {
				newBitString += bits[i + n] ? '1' : '0';
			} else {
				newBitString += '0';
			}
		}

		set(newBitString);
	}

	public void flipAll() {
		for (int i = 0; i < bits.length; i++) {
			flip(i);
		}
	}

	public void flip(int index) {
		set(index, !bits[index]);
	}

	public boolean get(int index) {
		if (index < 0 || index > getSize()) {
			throw new IndexOutOfBoundsException();
		}
		return bits[index];
	}

	public void set(int index) {
		set(index, true);
	}

	public void set(int index, boolean value) {
		bits[index] = value;
	}

	protected void set(String bitString) {
		for (int i = 0; i < bitString.length(); i++) {
			set(i, bitString.charAt(i) == '1');
		}
	}

	protected int bitsForNum(int num) {
		return Integer.toBinaryString(num).length();
	}

	public int getLength() {
		int length = 0;
		boolean hasSeen1 = false;
		for (boolean bit : bits) {
			if (bit) {
				hasSeen1 = true;
			}

			if (hasSeen1) {
				length++;
			}
		}
		return length;
	}

	public int getMinValue() {
		return 0;
	}

	public int getMaxValue() {
		return (int) (Math.pow(2, getSize()) - 1);
	}

	public int getSize() {
		return bits.length;
	}

	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}
		
		if (!(object instanceof FixedBitSet)) {
			return false;
		}

		FixedBitSet other = (FixedBitSet) object;

		if (other.getSize() == getSize()) {
			return false;
		}

		for (int i = 0; i < getSize(); i++) {
			if (other.get(i) != get(i)) {
				return false;
			}
		}
		return true;
	}

	public int toInt() {
		return Integer.parseInt(toString(), 2);
	}

	public String toString() {
		String result = "";
		for (boolean bit : bits) {
			result += bit ? '1' : '0';
		}
		return result;
	}

	@Override
	public Iterator<Boolean> iterator() {
		return new Iterator<Boolean>() {

			private int index = 0;

			@Override
			public boolean hasNext() {
				return index < bits.length;
			}

			@Override
			public Boolean next() {
				return new Boolean(bits[index++]);
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
	
	public static FixedBitSet append(FixedBitSet bitSet1, FixedBitSet bitSet2) {
		return new FixedBitSet(bitSet1.toString() + bitSet2.toString());
	}
}
