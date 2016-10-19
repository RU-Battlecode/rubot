package rubot.src.test;

import rubot.src.communication.FixedBitSet;
import rubot.src.communication.MessageMath;

public class MessageMathTest {
	
	static void run() {
		toBitSetTest();
	}
	
	private static void toBitSetTest() {
		FixedBitSet actual = MessageMath.toBitSet(0, Integer.SIZE);
		FixedBitSet expected = new FixedBitSet(Integer.SIZE);
		
		// 0 0
		Testing.assertEquals(actual, expected);
		
		actual = MessageMath.toBitSet(1, Integer.SIZE);
		expected.set(0);
		// 1 1
		Testing.assertEquals(actual, expected);
		
		// 101 101
		actual = MessageMath.toBitSet(5, Integer.SIZE);
		expected.clear();
		expected.set(0);
		expected.set(2);
		Testing.assertEquals(actual, expected);
		
		actual = MessageMath.toBitSet(5, 5);
		Testing.assertEquals(actual.toString(), "00101");
	}

}
