package main.java.mapfactory;

import java.awt.Color;
import java.util.Random;

public class HazardousMap extends Map{
	
	private HazardousMap(int x, int y) {
		super(x, y);
	}
	
	public static Map getInstance(int x, int y){
		if(Map.map == null)
			map = new HazardousMap(x,y);
		return Map.map;
	}
	
	@Override
	public void generate() {
		
		tiles = new Color[size][size];
		int area = size * size;
		
		int maxPercentageWater = (int) Math.round(area * 0.35);		
		int maxPercentageGreen = (int) Math.round(area * 0.75);
		
		int currWater = 0;
		int currGreen = 0;
		
		Random rand = new Random();
		
		for (int i=0; i < tiles.length; i++) {
			
			for (int j=0; j < tiles[i].length; j++) {
				
				int random = rand.nextInt(2);
				
				if(random == 0) {
					
					if(currGreen <= maxPercentageGreen) {
						tiles[i][j] = Color.GREEN;
						currGreen++;
					} else {
						tiles[i][j] = Color.BLUE;
						currWater++;
					}
				}
				else if (random == 1) {
					
					if (currWater < maxPercentageWater) {
						tiles[i][j] = Color.BLUE;
						currWater++;
					} else {
						tiles[i][j] = Color.GREEN;
						currGreen++;
					}
				}
			}
		}
		
		int randomRow = (int) Math.floor(Math.random() * size);
		int randomCol = (int) Math.floor(Math.random() * size);
		
		while (tiles[randomRow][randomCol] != Color.BLUE)
		{
			tiles[randomRow][randomCol] = Color.YELLOW; // treasure	
			
			randomRow = (int) Math.floor(Math.random() * size);
			randomCol = (int) Math.floor(Math.random() * size);
		}
	}

}
