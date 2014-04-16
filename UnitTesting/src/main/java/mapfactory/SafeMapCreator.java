package main.java.mapfactory;


public class SafeMapCreator extends MapCreator {
	
	public Map createMap(int x, int y) {
	    
		Map map = SafeMap.getInstance(x, y);		
		return map;
	}

}
