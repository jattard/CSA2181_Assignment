package test.java.mapfactory;

import static org.junit.Assert.assertEquals;
import main.java.Game;
import main.java.mapfactory.Map;
import main.java.mapfactory.MapCreator;

import org.junit.Before;
import org.junit.Test;

public class MapCreatorTest {

	Game game = null;
	
	@Before
	public void before() { 
		game = new Game();
		game.setNumPlayers(2);
		
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void createMapTest() {
		
		MapCreator creator = new MapCreator();
		Map map = creator.createMap(1, 5, 5);
		
		Class mapClass = map.getClass();
		String className = mapClass.getSimpleName();
		
		assertEquals(className, "SafeMap");
		
		map = creator.createMap(2, 5, 5);
		
		mapClass = map.getClass();
		className = mapClass.getSimpleName();
		
		// check that 2nd instance of Map, wasn't created
		assertEquals(className, "SafeMap");
	
	}	
}
