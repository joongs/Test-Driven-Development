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
		
		assertTrue(new Franc(5).equals(new Franc(5)));
		assertFalse(new Franc(5).equals(new Franc(6)));
	}
	
	public void testFrancMultiplication() {
		//5CHF * 2 = 10CHF
		Franc five = new Franc(5);
		assertEquals(new Franc(10), five.times(2));
		assertEquals(new Franc(15), five.times(3));
	}
}

///////////////////////////////////////////////////////////////////
package Money;

class Money{
	protected int amount;
	
	public boolean equals(Object object) {
		//return true;
		//5 == 5
		//amount == 5
		//amount == dollar.amount;
		//Dollar dollar = (Dollar)object;
		//Money dollar = (Dollar)object;
		//Money dollar = (Money)object;
		Money money = (Money)object;
		//return amount == dollar.amount;
		return amount == money.amount;
	}
}

///////////////////////////////////////////////////////
package Money;

class Dollar extends Money{

	//int amount = 10;
	//int amount = 5 * 2;
	//int amount;
	//private int amount;

	Dollar(int amount) {
		this.amount = amount;
	}

	Dollar times(int multiplier) {
	//void times(int multiplier) {
		//amount = 5 * 2;
		//amount = amount * 2;
		//amount = amount * multiplier;
		//amount *= multiplier;
		//return null;
		return new Dollar(amount * multiplier);
	}
	
//	public boolean equals(Object object) {
//		//return true;
//		//5 == 5
//		//amount == 5
//		//amount == dollar.amount;
//		//Dollar dollar = (Dollar)object;
//		//Money dollar = (Dollar)object;
//		//Money dollar = (Money)object;
//		Money money = (Money)object;
//		//return amount == dollar.amount;
//		return amount == money.amount;
//	}

}

///////////////////////////////////////////////////////
package Money;

public class Franc extends Money{
	
	//private int amount;

	Franc(int amount) {
		this.amount = amount;
	}

	Franc times(int multiplier) {
		//return null;
		return new Franc(amount * multiplier);
	}
	
//	public boolean equals(Object object) {
//		//return true;
//		//5 == 5
//		//amount == 5
//		//amount == dollar.amount;
//		//Franc franc = (Franc)object;
//		//Money franc = (Franc)object;
//		//Money franc = (Money)object;
//		Money money = (Money)object;
//		//return amount == franc.amount;
//		return amount == money.amount;
//	}

}

/////////////////////////////////////////////////////////
$5 + 10CHF = $10 (환율이 2:1일 경우)
$5 * 2 = $10						ch1
amount를 private으로 만들기				ch4
Dollar 부작용?						ch2
Money 반올림?
equals()							ch3
hashCode()
Equal null
Equal object
5CHF * 2 = 10CHF					ch5
Dollar / Franc 중복					
공용 equals							ch6
공용 times
