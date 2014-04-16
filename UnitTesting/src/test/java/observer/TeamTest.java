package test.java.observer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import main.java.Game;
import main.java.Player;
import main.java.mapfactory.SafeMap;
import main.java.observer.Team;

import org.junit.Test;

public class TeamTest {

	Team t = new Team();
	
	@Test
	public void registerTest() {
		Player p = new Player();
		Player p2 = new Player();
		t.register(p);
		t.register(p2);
		
		assertEquals(2, t.getPlayers().size());
	}
	
	@Test(expected = NullPointerException.class)
	public void registerNullTest(){
		t.register(null);
	}
	
	@Test
	public void teamTest() {
		
		Game game = new Game();
		game.setNumPlayers(2);
		SafeMap.getInstance(5, 5);
		
		
		Team t1 = new Team();
				
		Player p1 = new Player();
		Player p2 = new Player();
		
		t1.register(p1);
		t1.register(p2);
		
		p1.setSubject(t1);
		p2.setSubject(t1);
				
		p1.setStartingPosition(5);
		t1.setTeamTrail(p1.getPlayerTrail());
		
		p2.setStartingPosition(5);
		t1.setTeamTrail(p2.getPlayerTrail());
		
		boolean equal = Arrays.equals(p1.getPlayerTrail(), p2.getPlayerTrail());
		assertTrue(equal);

	}
	
}
