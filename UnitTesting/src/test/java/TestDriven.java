package test.java;

import static org.junit.Assert.*;

import main.java.HelloWorld;
import main.java.StringCalculator;

import org.junit.Test;


public class TestDriven {

	@Test
	public void getMessage()
	{
		int n = 4;
		String message = "";
		for (int i = 0; i<4 ; i++)
		{
			message = message+"Hello world!!!!! \n"; 
		}
		HelloWorld hw = new HelloWorld();		
		assertEquals(hw.getMessage(n),message);
	}
	
	@Test
	public void stringCalculatorTest()
	{
		StringCalculator calc = new StringCalculator();
		//assertEquals(calc.add(""),0); // 0 numbers
		
		//assertEquals(calc.add("2"),2); // 1 number
		
		//assertEquals(calc.add("5,3"),8); // 2 numbers
	}
}
	

