/*
 * 3장 시작 기본 코드 구조
 * */

// MoneyTest.java
import junit.framework.TestCase;

public class MoneyTest extends TestCase{
	public void testMultiplication() {
		//$5 * 2 = 10
		Dollar five = new Dollar(5);
		Dollar product = five.times(2);
		//five.times(2);
		//assertEquals(10, five.amount);
		assertEquals(10, product.amount);
		//Dollar가 변하는것 같은데
		//five.times(3);
		product = five.times(3);
		//assertEquals(15, five.amount);
		assertEquals(15, product.amount);
	}
}

// Dollar.java
class Dollar {

	//int amount = 10;
	//int amount = 5 * 2;
	int amount;

	Dollar(int amount) {
		// TODO Auto-generated constructor stub
		this.amount = amount;
	}

	Dollar times(int multiplier) {
	//void times(int multiplier) {
		// TODO Auto-generated method stub
		//amount = 5 * 2;
		//amount = amount * 2;
		//amount = amount * multiplier;
		//amount *= multiplier;
		//return null;
		return new Dollar(amount * multiplier);
	}

}
