package rubot.src.test;

public class Testing {
	
	public static final boolean TEST_MODE = true;
	private static final boolean VERBAL = true;
	
	private static boolean hasStartedTests = false;
	
	public static void runAllTests() {
		if (!hasStartedTests) {
			
			FixedBitSetTest.run();
			MessageMathTest.run();
			
			hasStartedTests = true;
		}
	}
	
	public static void assertEquals(Object o1, Object o2) {
		if (!o1.equals(o2)) {
			String error = "ASSERT FAIL: " + o1 + " NOT EQUAL " + o2;
			System.out.println(error);
			throw new RuntimeException(error);
		} else if (VERBAL) {
			System.out.println("pass");
		}
	}
	
}
