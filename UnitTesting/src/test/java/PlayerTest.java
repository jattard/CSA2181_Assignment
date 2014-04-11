package test.java;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import main.java.Map;
import main.java.Player;
import main.java.Position;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	
	Map map = null;
	Player player = null;
	
	@Before
	public void before() {

		map = new Map();
		map.setSize(5);
		map.generate();
		player = new Player();
		
	} 
	
	@Test
	public void moveTestLeftUppercase() {
		
		Position startPos = new Position(3, 4);
		player.setTrail(5);
		player.setStartingPosition(startPos);
		Position checkPos = new Position(3, 3);
		player.move('L', 5);
		
		assertEquals(player.getPos().toString(), checkPos.toString());
	}
	
	@Test
	public void moveTestLeftLowercase() {
		
		Position startPos = new Position(3, 4);
		player.setTrail(5);
		player.setStartingPosition(startPos);		
		Position checkPosl = new Position(3, 3);
		player.move('l', 5);
		
		assertEquals(player.getPos().toString(), checkPosl.toString());
	}		

	@Test
	public void moveTestLeftOutOfBounds() {
		//Position startPos = new Position(3, 4);
		
		Position startPos2 = new Position(0, 0);
		player.setTrail(5);
		player.setStartingPosition(startPos2);
		player.move('L', 5);
		
		// check if starting position stayed the same
		assertEquals(player.getPos().toString(), startPos2.toString());
	}
	
	@Test
	public void moveTestUpUppercase() {
		
		Position startPos = new Position(3, 3);
		player.setTrail(5);
		player.setStartingPosition(startPos);
		Position checkPos = new Position(3, 2);
		player.move('U', 5);
		
		assertEquals(player.getPos().toString(), checkPos.toString());
	}
	
	@Test
	public void moveTestUpLowercase() {
		
		Position startPos = new Position(3, 3);
		player.setTrail(5);
		player.setStartingPosition(startPos);		
		Position checkPosl = new Position(3, 2);
		player.move('u', 5);
		
		assertEquals(player.getPos().toString(), checkPosl.toString());
	}		

	@Test
	public void moveTestUpOutOfBounds() {
		//Position startPos = new Position(3, 4);
		
		Position startPos2 = new Position(0, 0);
		player.setTrail(5);
		player.setStartingPosition(startPos2);
		player.move('U', 5);
		
		// check if starting position stayed the same
		assertEquals(player.getPos().toString(), startPos2.toString());
	}
	
	@Test
	public void moveTestDownUppercase() {
		
		Position startPos = new Position(3, 3);
		player.setTrail(5);
		player.setStartingPosition(startPos);
		Position checkPos = new Position(3, 4);
		player.move('D', 5);
		
		assertEquals(player.getPos().toString(), checkPos.toString());
	}
	
	@Test
	public void moveTestDownLowercase() {
		
		Position startPos = new Position(3, 3);
		player.setTrail(5);
		player.setStartingPosition(startPos);		
		Position checkPosl = new Position(3, 4);
		player.move('d', 5);
		
		assertEquals(player.getPos().toString(), checkPosl.toString());
	}		

	@Test
	public void moveTestDownOutOfBounds() {
		//Position startPos = new Position(3, 4);
		
		Position startPos2 = new Position(4, 4);
		player.setTrail(5);
		player.setStartingPosition(startPos2);
		player.move('D', 5);
		
		// check if starting position stayed the same
		assertEquals(player.getPos().toString(), startPos2.toString());
	}
	
	@Test
	public void moveTestRightUppercase() {
		
		Position startPos = new Position(3, 3);
		player.setTrail(5);
		player.setStartingPosition(startPos);
		Position checkPos = new Position(4, 3);
		player.move('R', 5);
		
		assertEquals(player.getPos().toString(), checkPos.toString());
	}
	
	@Test
	public void moveTestRightLowercase() {
		
		Position startPos = new Position(3, 3);
		player.setTrail(5);
		player.setStartingPosition(startPos);		
		Position checkPosl = new Position(4, 3);
		player.move('r', 5);
		
		assertEquals(player.getPos().toString(), checkPosl.toString());
	}		

	@Test
	public void moveTestRightOutOfBounds() {
		//Position startPos = new Position(3, 4);
		
		Position startPos2 = new Position(4, 4);
		player.setTrail(5);
		player.setStartingPosition(startPos2);
		player.move('R', 5);
		
		// check if starting position stayed the same
		assertEquals(player.getPos().toString(), startPos2.toString());
	}
	
	@Test
	public void setStartingPositionTest() {
		
		player.setStartingPosition(5);
		Position startPos = player.getStartingPosition();
		
		assertEquals(Map.getTiles()[startPos.getY()][startPos.getX()], Color.GREEN);
	}
}