package test.java.mapfactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.lang.reflect.Field;

import main.java.Game;
import main.java.mapfactory.Map;
import main.java.mapfactory.SafeMap;

import org.junit.Before;
import org.junit.Test;

public class SafeMapTest {

	Map map = null;
	Game game = null;
	
	@Before
	public void before() throws NoSuchFieldException, IllegalAccessException {
		
		game = new Game();
		game.setNumPlayers(2);
		
		Field field = Map.class.getDeclaredField("map");
		field.setAccessible(true);
		field.set(map, null);
		
		map = SafeMap.getInstance(5, 5);
		Map.setMapSize(5, 5);
		map.generate();
	}
	
	@Test
	public void generateTest() {
		
		Color[][] tiles = Map.getTiles();
		int area = (int) (5 * 5 * 0.1);
		int countBlue = 0, countYellow = 0;
		
		
		for (int i=0; i < tiles.length; i++) {
			for (int j=0; j < tiles[i].length; j++) {
				
				if (tiles[i][j] == Color.BLUE)
				{
					countBlue++;
				}
				else if (tiles[i][j] == Color.YELLOW)
				{
					countYellow++;
				}
			}
		}
		
		boolean safe = false;
		if(countBlue <= area)
			safe = true;
		
		assertTrue(safe);
		assertEquals(countYellow, 1);
	}
}