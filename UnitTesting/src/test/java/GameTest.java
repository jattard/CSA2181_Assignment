package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import main.java.Game;
import main.java.Player;
import main.java.mapfactory.Map;
import main.java.mapfactory.SafeMap;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

public class GameTest {

	Game game = null;
	Map map = null;
	
	@Before
	public void before() {
		
		game = new Game();
		game.setNumPlayers(2);
		map = SafeMap.getInstance(5, 5);	
		
		game.setPlayers(null);
	}
	
	@Test
	public void toHexStringTest() {
		
			
		String returnGreenhashCode = game.toHexString(Color.GREEN).toUpperCase();
		String actualGreenHashCode = "#00FF00";
		
		assertEquals(returnGreenhashCode, actualGreenHashCode);
		
		String returnBluehashCode = game.toHexString(Color.BLUE).toUpperCase();
		String actualBlueHashCode = "#0000FF";
		
		assertEquals(returnBluehashCode, actualBlueHashCode);
		
		String returnYellowhashCode = game.toHexString(Color.YELLOW).toUpperCase();
		String actualYellowHashCode = "#FFFF00";
		
		assertEquals(returnYellowhashCode, actualYellowHashCode);
	}
	
	@Test
	public void validNumberOfPlayersTest() {
				
		boolean valid1Player = game.setNumPlayers(1);
		assertEquals(valid1Player, false);
		assertNull(Game.getPlayers());
		
		boolean valid9Player = game.setNumPlayers(9);
		assertEquals(valid9Player, false);
		assertNull(Game.getPlayers());
		
		boolean valid2Player = game.setNumPlayers(2);
		assertEquals(valid2Player, true);
		assertEquals(Game.getPlayers().length, 2);
		
		boolean valid5Player = game.setNumPlayers(5);
		assertEquals(valid5Player, true);
		assertEquals(Game.getPlayers().length, 5);
	}
	
	@Test
	public void generateHTMLTest() throws IOException {
		
		game.setNumPlayers(2);
		map.setSize(5);
		Player[] players = Game.getPlayers();
		
		for (int i=0; i < players.length; i++)
		{
			players[i].setFixedStartingPosition();
			players[i].setTrail(5);
		}
		
		map.gen();
		game.generateHTMLFiles();
		
		File file1 = new File("map_player_1.html");
		File file2 = new File("fixedMap.html");
		
		assertEquals(FileUtils.readFileToString(file1, "utf-8"), FileUtils.readFileToString(file2, "utf-8"));	
	}
	
	@Test
	public void winGameTest() {
		
		game.setNumPlayers(2);
		map.setSize(5);
		Player[] players = Game.getPlayers();
		
		for (int i=0; i < players.length; i++)
		{
			players[i].setFixedStartingPosition();
			players[i].setTrail(5);
		}
		
		assertTrue(game.winGame(Color.YELLOW));
		assertFalse(game.winGame(Color.GREEN));
		assertFalse(game.winGame(Color.BLUE));
	}
	
	@Test
	public void initStartingPosTest() {

		game.setNumPlayers(2);
		map = SafeMap.getInstance(5, 5);
		Player[] players = Game.getPlayers();
		
		game.initGame(5, 1);
		
		for (int i=0; i < players.length; i++)
		{
			assertNotNull(players[i].getStartingPosition());
		}
	}
}
