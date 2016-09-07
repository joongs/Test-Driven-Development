package Money;

import junit.framework.TestCase;

public class MoneyTest extends TestCase{
	public void testMultiplication() {
		//$5 * 2 = 10
		Dollar five = new Dollar(5);
		//Dollar product = five.times(2);
		//five.times(2);
		//assertEquals(10, five.amount);
		//assertEquals(10, product.amount);
		//assertEquals(new Dollar(10), product);
		assertEquals(new Dollar(10), five.times(2));
		//Dollar가 변하는것 같은데
		//five.times(3);
		//product = five.times(3);
		//assertEquals(15, five.amount);
		//assertEquals(15, product.amount);
		//assertEquals(new Dollar(15), product);
		assertEquals(new Dollar(15), five.times(3));
	}
	
	//equality 체크
	public void testEquality() {
		assertTrue(new Dollar(5).equals(new Dollar(5)));
		//삼각측량 시험
		assertFalse(new Dollar(5).equals(new Dollar(6)));
	}
}

////////////////////////////////////////////////////////////////

package Money;

class Dollar {

	//int amount = 10;
	//int amount = 5 * 2;
	//int amount;
	private int amount;

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
	
	public boolean equals(Object object) {
		//return true;
		//5 == 5
		//amount == 5
		//amount == dollar.amount;
		Dollar dollar = (Dollar)object;
		return amount == dollar.amount;
	}

}
