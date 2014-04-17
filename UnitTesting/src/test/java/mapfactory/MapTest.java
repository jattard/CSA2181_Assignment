package test.java.mapfactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import main.java.Game;
import main.java.mapfactory.Map;
import main.java.mapfactory.SafeMap;

import org.junit.Before;
import org.junit.Test;

public class MapTest {

	Map map = null;
	Game game = null;
	
	@Before
	public void before() {
		
		game = new Game();
		game.setNumPlayers(2);
		map = SafeMap.getInstance(5, 5);
	}
	
	@Test
	public void setMapSizeTestPlayers() {
		// 5-8 - 8 x 8
		game.setNumPlayers(5);
		boolean set = Map.setMapSize(5, 5);
		assertFalse(set);
		
		set = Map.setMapSize(8, 8);
		assertTrue(set);
	}
	
	@Test
	public void setMapSizeTestPlayers2() {
		// 2-4 - 5 x 5
		game.setNumPlayers(2);
		
		boolean set = Map.setMapSize(3, 3);
		assertFalse(set);
		
		set = Map.setMapSize(5, 5);
		assertTrue(set);
	}
	
	@Test
	public void setMapSizeTestSquare() {
		// 5-8 - 8 x 8
		game.setNumPlayers(5);
		
		boolean set = Map.setMapSize(8, 9);
		assertFalse(set);
	}
	
	@Test
	public void setMapSizeTestMax() {
		// 5-8 - 8 x 8
		game.setNumPlayers(5);
		
		boolean set = Map.setMapSize(50, 50);
		assertTrue(set);
		
		set = Map.setMapSize(51, 51);
		assertFalse(set);
	}
	
	@Test
	public void generateTestSize() {
		
		game.setNumPlayers(2);
		map.setSize(5);
		game.initGame(5, 1);
		
		assertEquals(Map.getTiles().length, 5);
		assertEquals(Map.getTiles()[1].length, 5);
	}
	
	@Test
	public void generateTestYellow(){
		
		game.setNumPlayers(2);
		map.setSize(5);
		game.initGame(5, 1);
		
		int countYellow = 0;
		
		for(int i=0; i < 5; i++){
			for(int j=0 ; j < 5; j++){
				if(Map.getTiles()[i][j] == Color.YELLOW)
					countYellow++;
			}
		}
			
		assertEquals(countYellow, 1);
	}
}