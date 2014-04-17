package test.java.mapfactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.lang.reflect.Field;

import main.java.Game;
import main.java.mapfactory.HazardousMap;
import main.java.mapfactory.Map;

import org.junit.Before;
import org.junit.Test;

public class HazardousMapTest {

	Map map = null;
	Game game = null;
	
	@Before
	public void before() throws NoSuchFieldException, IllegalAccessException {
		
		game = new Game();
		game.setNumPlayers(2);
		
		Field field = Map.class.getDeclaredField("map");
		field.setAccessible(true);
		field.set(map, null);
		
		map = HazardousMap.getInstance(5, 5);
	}
	
	@Test
	public void generateTest() {
				
		Color[][] tiles = Map.getTiles();
		int minArea = (int) Math.round(5 * 5 * 0.25);
		int maxArea = (int) Math.round(5 * 5 * 0.35);
		int countBlue = 0;
		int countYellow = 0;
		
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
		
		boolean hazourdous = false;
		if(countBlue >= minArea && countBlue <= maxArea)
			hazourdous = true;
		
		assertTrue(hazourdous);
		assertEquals(countYellow, 1);
	}	
}