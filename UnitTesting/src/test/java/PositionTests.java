package test.java;

import static org.junit.Assert.assertEquals;
import main.java.Position;

import org.junit.Before;
import org.junit.Test;

public class PositionTests {
	
	Position p;
	
	@Before
	public void before() {

		p = new Position(0,5);
	} 
	
	@Test
	public void toStringTest() {
		String toString = "x: 0 y: 5";
		assertEquals(p.toString(), toString);
	}
}
