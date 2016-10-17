// MoneyTest.java
package Money;

import junit.framework.TestCase;

public class MoneyTest extends TestCase{
	public void testMultiplication() {
		//$5 * 2 = 10
		//Dollar five = new Dollar(5);
		//Dollar five = Money.dollar(5);
		Money five = Money.dollar(5);
		//Dollar product = five.times(2);
		//five.times(2);
		//assertEquals(10, five.amount);
		//assertEquals(10, product.amount);
		//assertEquals(new Dollar(10), product);
		//assertEquals(new Dollar(10), five.times(2));
		assertEquals(Money.dollar(10), five.times(2));
		//Dollar가 변하는것 같은데
		//five.times(3);
		//product = five.times(3);
		//assertEquals(15, five.amount);
		//assertEquals(15, product.amount);
		//assertEquals(new Dollar(15), product);
		//assertEquals(new Dollar(15), five.times(3));
		assertEquals(Money.dollar(15), five.times(3));
	}
	
	//equality 체크
	public void testEquality() {
		//assertTrue(new Dollar(5).equals(new Dollar(5)));
		assertTrue(Money.dollar(5).equals(Money.dollar(5)));
		//삼각측량 시험
		//assertFalse(new Dollar(5).equals(new Dollar(6)));
		assertFalse(Money.dollar(5).equals(Money.dollar(6)));
		
		//assertTrue(new Franc(5).equals(new Franc(5)));
//		assertTrue(Money.franc(5).equals(Money.franc(5)));
		//assertFalse(new Franc(5).equals(new Franc(6)));
//		assertFalse(Money.franc(5).equals(Money.franc(6)));
		
		//Franc != Dollar
		//assertFalse(new Franc(5).equals(new Dollar(5)));
		//assertFalse(new Franc(5).equals(Money.dollar(6)));
		assertFalse(Money.franc(5).equals(Money.dollar(6)));
	}
	
//	public void testFrancMultiplication() {
//		//5CHF * 2 = 10CHF
//		//Franc five = new Franc(5);
//		//Franc five = Money.franc(5);
//		Money five = Money.franc(5);
//		//assertEquals(new Franc(10), five.times(2));
//		assertEquals(Money.franc(10), five.times(2));
//		//assertEquals(new Franc(15), five.times(3));
//		assertEquals(Money.franc(15), five.times(3));
//	}
	
	public void testCurrency() {
		assertEquals("USD", Money.dollar(1).currency());
		assertEquals("CHF", Money.franc(1).currency());
	}
	
//	public void testDifferentClassEquality() {
//		assertTrue(new Money(10, "CHF").equals(new Franc(10, "CHF")));
//	}
	
	//$5 + $5 = $10
	public void testSimpleAddition() {
//		Money sum = Money.dollar(5).plus(Money.dollar(5));
//		assertEquals(Money.dollar(10), sum);
		
		Money five = Money.dollar(5);
		Expression sum = five.plus(five);
		Bank bank = new Bank();
		Money reduced = bank.reduce(sum, "USD");
		assertEquals(Money.dollar(10), reduced);
	}
	
	public void testPlusReturnsSum() {
		Money five = Money.dollar(5);
		Expression result = five.plus(five);
		Sum sum = (Sum) result;
		assertEquals(five, sum.augend);
		assertEquals(five, sum.addend);
	}
	
	public void testReduceSum() {
		Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
		Bank bank = new Bank();
		Money result = bank.reduce(sum, "USD");
		assertEquals(Money.dollar(7), result);
	}
	
	public void testReduceMoney() {
		Bank bank = new Bank();
		Money result = bank.reduce(Money.dollar(1),  "USD");
		assertEquals(Money.dollar(1), result);
	}
}

// Money.java
package Money;

//abstract class Money{
//class Money{
class Money implements Expression {
	protected int amount;
	protected String currency;
	
	Money(int amount, String currency) {
		this.amount = amount;
		this.currency = currency;
	}
	
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
		//return amount == money.amount && getClass().equals(money.getClass());
		return amount == money.amount
				&& currency().equals(money.currency());
	}

	//static Dollar dollar(int amount) {
	static Money dollar(int amount) {
		//return new Dollar(amount);
		//return new Dollar(amount, null);
		//return new Dollar(amount, "USD");
		return new Money(amount, "USD");
	}
	
	//static Franc franc(int amount) {
	static Money franc(int amount) {
		//return new Franc(amount);
		//return new Franc(amount, null);
		//return new Franc(amount, "CHF");
		return new Money(amount, "CHF");
	}

	//abstract Money times(int multipiler);
	Money times(int multiplier) {
		//return null;
		return new Money(amount * multiplier, currency);
	}

	//abstract String currency();
	String currency() {
		//return "USD";
		return currency;
	}
	
	public String toString() {
		return amount + " " + currency;
	}

	//Money plus(Money addend) {
	Expression plus(Money addend) {
		//return null;
		//return Money.dollar(10);
//		return new Money(amount + addend.amount, currency);
		return new Sum(this, addend);
	}
	
	public Money reduce(String to) {
		return this;
	}
}

// Bank.java
package Money;

class Bank {

//	Money reduce(Expression sum, String string) {
//		//return null;
//		return Money.dollar(10);
//	}
	
	Money reduce(Expression source, String to) {
//		if (source instanceof Money) 
//			//return (Money) source;
//			return (Money) source.reduce(to);
//		Sum sum = (Sum) source;
////		int amount = sum.augend.amount + sum.addend.amount;
////		return new Money(amount, to);
//		return sum.reduce(to);
		return source.reduce(to);
	}
}

// Expression.java
package Money;

interface Expression {

	Money reduce(String to);
}

// Sum.java
package Money;

class Sum implements Expression {
	Money augend;
	Money addend;
	
	Sum(Money augend, Money addend) {
		this.augend = augend;
		this.addend = addend;
	}

	public Money reduce(String to) {
		int amount = augend.amount + addend.amount;
		return new Money(amount, to);
	}
}

// Backlog 1
$5 + 10CHF = $10 (환율이 2:1일 경우)
$5 * 2 = $10						ch01
amount를 private으로 만들기				ch04
Dollar 부작용?						ch02
Money 반올림?
equals()							ch03
hashCode()
Equal null
Equal object
5CHF * 2 = 10CHF					ch05
Dollar / Franc 중복					ch08? ch09? ch11
공용 equals							ch06
공용 times							ch10
Franc과 Dollar 비교하기				ch07
통화?								ch09
testFrancMultiplication 제거			ch11

========================================================
// Backlog 2
$5 + 10CHF = $10 (환율이 2:1일 경우)
$5 + $5 = $10 (간단한 예로 시작)			ch12 ch13
$5 + $5에서 Money 반환하기
Bank.reduce(Money)
Money에 대한 통화 변환을 수행하는 Reduce
Reduce(Bank, String)
