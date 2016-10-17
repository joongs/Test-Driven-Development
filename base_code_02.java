/*
 * 2장 시작 기본 코드 구조
 * */

// MoneyTest.java
import junit.framework.TestCase;

public class MoneyTest extends TestCase{
	public void testMultiplication() {
		Dollar five = new Dollar(5);
		five.times(2);
		assertEquals(10, five.amount);
		
		five.times(3);
		assertEquals(15, five.amount);
	}
}

// Dollar.java
class Dollar{
	//int amount = 10;
	int amount;
	
	Dollar (int amount) {
		this.amount = amount;
	}
	
	void times(int multiplier){
		amount = amount * multiplier;
	}
}
