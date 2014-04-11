package test.java;

import static org.junit.Assert.*;

import java.awt.Color;

import main.java.Game;
import main.java.Map;

import org.junit.Before;
import org.junit.Test;

public class MapTest {

	Map map = null;
	Game game = null;
	
	@Before
	public void before(){
		game = new Game();
		map = new Map();
	}
	
	@Test
	public void setMapSizeTestPlayers5() {
		// 5-8 - 8 x 8
		game.setNumPlayers(5);
		boolean set = map.setMapSize(5, 5);
		
		assertFalse(set);
		
		set = map.setMapSize(8, 8);
		
		assertTrue(set);
	}
	
	@Test
	public void setMapSizeTestPlayers2() {
		// 2-4 - 5 x 5
		game.setNumPlayers(2);
		boolean set = map.setMapSize(3, 3);
		
		assertFalse(set);
		
		set = map.setMapSize(5, 5);
		
		assertTrue(set);
	}
	
	@Test
	public void setMapSizeTestSquare() {
		// 5-8 - 8 x 8
		game.setNumPlayers(5);
		boolean set = map.setMapSize(8, 9);
		
		assertFalse(set);
	}
	
	@Test
	public void setMapSizeTestMax() {
		// 5-8 - 8 x 8
		game.setNumPlayers(5);
		boolean set = map.setMapSize(50, 50);
		
		assertTrue(set);
		
		set = map.setMapSize(51, 51);
		
		assertFalse(set);
	}
	
	public void generateTestSize() {
		
		game.setNumPlayers(5);
		map.setMapSize(5, 5);
		map.generate();
		
		assertEquals(Map.getTiles().length, 5);
		assertEquals(Map.getTiles()[1].length, 5);
	}
	
	public void generateTestYellow(){
		
		game.setNumPlayers(5);
		//map.setMapSize(5, 5);
		//map.generate();
		
		map = new Map(5,5);
		
		int countYellow = 0;
		
		for(int i = 0; i < 5; i++){
			for(int j =0 ; i < 5; j++){
				if(Map.getTiles()[i][j] == Color.YELLOW)
					countYellow++;
			}
		}
		
		assertEquals(countYellow, 1);
	}

}
