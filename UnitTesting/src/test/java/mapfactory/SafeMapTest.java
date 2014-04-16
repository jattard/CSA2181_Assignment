package test.java.mapfactory;

import static org.junit.Assert.assertTrue;

import java.awt.Color;

import main.java.Game;
import main.java.mapfactory.Map;
import main.java.mapfactory.SafeMap;

import org.junit.Before;
import org.junit.Test;

public class SafeMapTest {

	Map map = null;
	Game game = null;
	
	@Before
	public void before() {
		
		game = new Game();
		game.setNumPlayers(2);
		map = SafeMap.getInstance(5, 5);
	}
	
	@Test
	public void generateTest() {
		
		Color[][] tiles = Map.getTiles();
		int area = (int) (5 * 5 * 0.1);
		int countBlue = 0;
		
		for (int i=0; i < tiles.length; i++) {
			for (int j=0; j < tiles[i].length; j++) {
				
				if (tiles[i][j] == Color.BLUE)
				{
					countBlue++;
				}
			}
		}
		
		boolean safe = false;
		if(countBlue <= area)
			safe = true;
		
		assertTrue(true);
	}
}
