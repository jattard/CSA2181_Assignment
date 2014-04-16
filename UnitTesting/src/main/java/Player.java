package main.java;

import java.awt.Color;

import main.java.mapfactory.Map;
import main.java.observer.Observer;
import main.java.observer.Subject;

public class Player implements Observer {

	private Position pos;
	private Position startingPosition;
	private int[][] playerTrail;
	private Subject topic;
	
	public Position getStartingPosition() {
		return startingPosition;
	}
	
	public Position getPos() {
		return pos;
	}
	
	public void setTrail(Position pos)
	{
		// set player trail position to 1 if player opened a square
		playerTrail[pos.getY()][pos.getX()] = 1;
	}
	
	public int[][] getPlayerTrail() {
		return playerTrail;
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
		
		setTrail(pos);
		return true;
	}
	
	public void setPosition(Position pos)
	{
		this.pos = new Position(pos.getX(), pos.getY());
	}
	
	public void setStartingPosition(Position startingPosition)
	{
		this.startingPosition = startingPosition;
		this.pos = new Position(startingPosition.getY(), startingPosition.getX());
		
		setTrail(pos);
	}
	
	public void setFixedStartingPosition()
	{
		this.startingPosition = new Position(3, 3);
		this.pos = new Position(3, 3);
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
		
		if (playerTrail == null)
			setTrail(size);

		this.startingPosition = new Position(randomCol, randomRow);
		this.pos = new Position(randomCol, randomRow);
		
		setTrail(pos);
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

	@Override
	public void update() 
	{
		playerTrail = (int[][]) topic.getUpdate(this);
	}

	@Override
	public void setSubject(Subject topic) 
	{
		this.topic = topic;
	}
}