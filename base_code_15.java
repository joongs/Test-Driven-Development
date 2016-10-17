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
	
	//CHF2 -> $?
	public void testReduceMoneyDifferentCurrency() {		
		Bank bank = new Bank();
		bank.addRate("CHF", "USD", 2);
		Money result = bank.reduce(Money.franc(2), "USD");
		assertEquals(Money.dollar(1), result);
	}
	
//	public void testArrayEquals() {
//		assertEquals(new Object[] {"abc"}, new Object[] {"abc"});
//	}
	
	public void testIdentityRate() {
		assertEquals(1, new Bank().rate("USD",  "USD"));
	}
	
}

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
		return new Sum(this, addend);
	}
	
	public Money reduce(Bank bank, String to) {
		int rate = bank.rate(currency, to);
		return new Money(amount / rate, to);
	}
}

package Money;

import java.util.Hashtable;

class Bank {

	private Hashtable rates = new Hashtable();
	
	Money reduce(Expression source, String to) {
		return source.reduce(this, to);
	}
	
	int rate(String from, String to) {
		if (from.equals(to)) return 1;
		Integer rate = (Integer) rates.get(new Pair(from, to));
		return rate.intValue();
	}
	
	void addRate(String from, String to, int rate) {
		rates.put(new Pair(from, to),  new Integer(rate));
	}
}

package Money;

class Sum implements Expression {
	Money augend;
	Money addend;
	
	Sum(Money augend, Money addend) {
		this.augend = augend;
		this.addend = addend;
	}

	public Money reduce(Bank bank, String to) {
		int amount = augend.amount + addend.amount;
		return new Money(amount, to);
	}
}

package Money;

public interface Expression {

	Money reduce(Bank bank, String to);

}

package Money;

class Pair {
	private String from;
	private String to;
	
	Pair(String from, String to) {
		this.from = from;
		this.to = to;
	}
	
	public boolean equals(Object object) {
		Pair pair = (Pair) object;
		return from.equals(pair.from) && to.equals(pair.to);
	}
	
	public int hashCode() {
		return 0;
	}
}

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
====================================================

$5 + 10CHF = $10 (환율이 2:1일 경우)
$5 + $5 = $10 (간단한 예로 시작)			ch12 ch13
$5 + $5에서 Money로 반환하기
Bank.reduce(Money)					ch13
Money에 대한 통화 변환을 수행하는 Reduce		ch14
Reduce(Bank, String)				ch14
