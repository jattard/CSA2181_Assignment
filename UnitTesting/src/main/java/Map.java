package main.java;

import java.awt.Color;
import java.util.Random;

public class Map {
	
	private int size;
	private static Color[][] tiles;
	
	public Map() {
		
	}
	
	public Map(int x, int y) {
		
		if (setMapSize(x, y))
		{
			// generate();
			generate();
		}
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public static Color[][] getTiles() {
		return tiles;
	}

	public static void setTiles(Color[][] tiles) {
		Map.tiles = tiles;
	}
	
	public boolean setMapSize(int x, int y)
	{
		// max map size - 50 x 50
		if (x != y || x > 50)
			return false;
		
		int noOfPlayers = Game.getPlayers().length;
	
		// 2-4 - 5 x 5
		if (noOfPlayers >= 2 && noOfPlayers <= 4)
		{
			if (x < 5)
			{
				return false;
			}
		}
		// 5-8 - 8 x 8
		else if (noOfPlayers >= 5 && noOfPlayers <= 8)
		{
			if (x < 8)
			{
				return false;
			}
		}
		size = x;
		return true;	
	}
	
	public void gen()
	{
		tiles = new Color[size][size];
		
		for (int i=0; i < tiles.length; i++)
		{
			for (int j=0; j< tiles[i].length; j++)
			{
				if (i % 2 == 0)
				{
					tiles[i][j] = Color.GREEN;
				}
				else
				{
					tiles[i][j] = Color.BLUE;
				}
			}
		}
		
		tiles[0][4] = Color.YELLOW;	
	}
	
	public void generate()
	{
		tiles = new Color[size][size];
		Random rand = new Random();
		
		for (int i=0; i < tiles.length; i++)
		{
			for (int j=0; j < tiles[i].length; j++)
			{
				int random = rand.nextInt(2);
				
				if (random == 0)
				{
					tiles[i][j] = Color.GREEN; // grass
				}
				else if (random == 1)
				{
					tiles[i][j] = Color.BLUE; // water
				}
			}
		}
		
		int randomRow = (int) Math.floor(Math.random() * size);
		int randomCol = (int) Math.floor(Math.random() * size);
		
		tiles[randomRow][randomCol] = Color.YELLOW; // treasure
	}	
}
