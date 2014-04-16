package main.java.mapfactory;

import java.awt.Color;

import main.java.Game;

public abstract class Map {
	
	protected int size;
	protected static Color[][] tiles;
	
	protected static Map map = null;
	
	public Map(int x, int y) {
		
		boolean validMapSize = setMapSize(x, y);
		
		if (validMapSize)
		{
			setSize(x);
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
	
	public static boolean setMapSize(int x, int y)
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
		//size = x;
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
	
	public abstract void generate();
}
