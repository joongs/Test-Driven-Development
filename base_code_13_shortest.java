// MoneyTest.java
package Money;

import junit.framework.TestCase;

public class MoneyTest extends TestCase{
	public void testMultiplication() {
		Money five = Money.dollar(5);
		assertEquals(Money.dollar(10), five.times(2));
		assertEquals(Money.dollar(15), five.times(3));
	}

	public void testEquality() {
		assertTrue(Money.dollar(5).equals(Money.dollar(5)));
		assertFalse(Money.dollar(5).equals(Money.dollar(6)));
		assertFalse(Money.franc(5).equals(Money.dollar(6)));
	}

	public void testCurrency() {
		assertEquals("USD", Money.dollar(1).currency());
		assertEquals("CHF", Money.franc(1).currency());
	}
	//$5 + $5 = $10
	public void testSimpleAddition() {
		Money five = Money.dollar(5);
		Expression sum = five.plus(five);
		Bank bank = new Bank();
		Money reduced = bank.reduce(sum, "USD");
		assertEquals(Money.dollar(10), reduced);
	}
}

// Money.java
package Money;

class Money implements Expression {
	protected int amount;
	protected String currency;
	
	Money(int amount, String currency) {
		this.amount = amount;
		this.currency = currency;
	}
	
	public boolean equals(Object object) {
		Money money = (Money)object;
		return amount == money.amount
				&& currency().equals(money.currency());
	}

	static Money dollar(int amount) {
		return new Money(amount, "USD");
	}
	
	static Money franc(int amount) {
		return new Money(amount, "CHF");
	}

	Money times(int multiplier) {
		return new Money(amount * multiplier, currency);
	}

	String currency() {
		return currency;
	}
	
	public String toString() {
		return amount + " " + currency;
	}

	Expression plus(Money addend) {
		return new Money(amount + addend.amount, currency);
	}
}

//Expression.java
package Money;

interface Expression {

}

// Bank.java
package Money;

class Bank {

	Money reduce(Expression sum, String string) {
		return Money.dollar(10);
	}
}

//backlog.txt
$5 + 10CHF = $10 (환율이 2:1일 경우)
$5 * 2 = $10  ch01
amount를 private으로 만들기 ch04
Dollar 부작용? ch02
Money 반올림?
equals()  ch03
hashCode()
Equal null
Equal object
5CHF * 2 = 10CHF  ch05
Dollar / Franc 중복 ch08? ch09? ch11
공용 equals ch06
공용 times  ch10
Franc과 Dollar 비교하기  ch07
통화? ch09
testFrancMultiplication 제거  ch11

========================================================
$5 + 10CHF = $10 (환율이 2:1일 경우)
$5 + $5 = $10 (간단한 예로 시작) ch12

