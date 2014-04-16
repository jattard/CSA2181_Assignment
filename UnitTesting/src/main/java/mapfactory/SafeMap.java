package main.java.mapfactory;

import java.awt.Color;
import java.util.Random;

public class SafeMap extends Map {
		
	private SafeMap(int x, int y) {
		super(x, y);
	}
	
	public static Map getInstance(int x, int y){
		if(Map.map == null)
			map = new SafeMap(x,y);
		return Map.map;
	}
	
	@Override
	public void generate() {
		
		tiles = new Color[size][size];
		int area = size * size;
		int percentageWater = (int) (area * 0.1);
		int currWater = 0;
		
		Random rand = new Random();
		
		for (int i=0; i < tiles.length; i++)
		{
			for (int j=0; j < tiles[i].length; j++)
			{
				int random = rand.nextInt(2);
				
				if (random == 0 || currWater >= percentageWater)
				{
					tiles[i][j] = Color.GREEN; // grass
				}
				else if (random == 1 && currWater < percentageWater)
				{
					tiles[i][j] = Color.BLUE; // water
					currWater++;
				}
			}
		}
		
		int randomRow = (int) Math.floor(Math.random() * size);
		int randomCol = (int) Math.floor(Math.random() * size);
		
		tiles[randomRow][randomCol] = Color.YELLOW; // treasure		
	}
	
	

}
