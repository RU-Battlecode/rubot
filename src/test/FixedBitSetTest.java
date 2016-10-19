package rubot.src.test;

import rubot.src.communication.FixedBitSet;

public class FixedBitSetTest {

	static void run() {
		shiftRightTest();
	}
	
	private static void shiftRightTest() {
		FixedBitSet set0 = new FixedBitSet(5); // 00000
		FixedBitSet set2 = new FixedBitSet("00010"); 
		
		Testing.assertEquals(set0.toString(), "00000");
		
		set2.rightShift(0);
		Testing.assertEquals(set2.toString(), "00010");
		set2.rightShift(1);
		Testing.assertEquals(set2.toString(), "00001");
//		set1.rightShift(2);
//		Testing.assertEquals(set1.toString(), "01000");
//		
		
	}
	
}
