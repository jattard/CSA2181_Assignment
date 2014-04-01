package test.java;

import static org.junit.Assert.*;

import main.java.Calculator;

import org.junit.Test; 


public class UnitTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}


@Test
public void addTest()
{
Calculator myCalculator = new Calculator();
assertEquals(myCalculator.add(3,5),8);
}


@Test
public void subtractTest()
{
Calculator myCalculator = new Calculator();
assertEquals(myCalculator.subtract(5,2),3);
}

@Test
public void multiplyTest()
{
Calculator myCalculator = new Calculator();
assertEquals(myCalculator.multiply(2,2),4);
}


@Test
public void divisionTest()
{
Calculator myCalculator = new Calculator();
assertEquals(myCalculator.divide(6,2),3);
assertEquals(myCalculator.divide(4,0),-999);
}

}
