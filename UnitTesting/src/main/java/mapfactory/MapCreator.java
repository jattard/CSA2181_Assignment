package main.java.mapfactory;


public class MapCreator {

	//Factory Method 
	public Map createMap(int type, int x, int y) {
	    //Determine which creator to use
	    MapCreator creator = findCreatorForType(type);
	    return creator.createMap(x, y);
	}
	
	public Map createMap(int x, int y) {
		return null; // create safe map
	}
	
	public MapCreator findCreatorForType(int type)
	{
		if (type == 1)
			return new SafeMapCreator(); 
		else if (type == 2)
			return new HazardousMapCreator();
		
		return null;
	}
}