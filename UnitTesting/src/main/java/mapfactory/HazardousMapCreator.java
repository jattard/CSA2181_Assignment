package main.java.mapfactory;


public class HazardousMapCreator extends MapCreator {

	public Map createMap(int x, int y) {
	    
		Map map = HazardousMap.getInstance(x, y);
		return map;
	}
	
}
