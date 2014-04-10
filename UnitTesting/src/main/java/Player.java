package main.java;

import java.awt.Color;

public class Player {

	private Position pos;
	private Position startingPosition;
	private int[][] playerTrail;
	
	public Player()
	{
		
	}
	
	public Position getStartingPosition() {
		return startingPosition;
	}
	
	public Position getPos() {
		return pos;
	}
	
	public boolean move(char direction, int size)
	{
		switch (direction)
		{
			case 'u':
			case 'U': 
				if(pos.getY() - 1 >= 0)
					pos.setY(pos.getY() - 1); 
				break; 
			case 'd':
			case 'D':
				if(pos.getY() + 1 < size)
					pos.setY(pos.getY() + 1); 
				break;
			case 'l':
			case 'L':
				if(pos.getX() - 1 >= 0)
					pos.setX(pos.getX() - 1); 
				break;
			case 'r':
			case 'R':
				if(pos.getX() + 1 < size)
					pos.setX(pos.getX() + 1); 
				break;
			default: return false;
		}
		return true;
	}
	
	public void setPosition(Position pos)
	{
		// set player trail position to 1 if player opened a square
		playerTrail[pos.getY()][pos.getX()] = 1;
		this.pos = pos;
	}
	
	public void setStartingPosition(Position startingPosition)
	{
		this.startingPosition = startingPosition;
		this.pos = new Position(startingPosition.getX(), startingPosition.getY());
	}
	
	public void setStartingPosition(int size)
	{
		int randomRow;
		int randomCol;
		
		Color thisColor = null;
		
		do
		{
			randomRow = (int) Math.floor(Math.random() * size);
			randomCol = (int) Math.floor(Math.random() * size);
			
			thisColor = Map.getTiles()[randomRow][randomCol];
		}
		while (thisColor != Color.GREEN);
		
		setTrail(size);
		
		this.startingPosition = new Position(randomRow, randomCol);
		this.pos = new Position(randomRow, randomCol);
	}
	
	public void setTrail(int size)
	{
		playerTrail = new int[size][size];
		
		// init playerTrail
		for (int i=0; i < playerTrail.length; i++)
		{
			for (int j=0; j < playerTrail.length; j++)
			{
				playerTrail[i][j] = 0;
			}
		}
	}
}