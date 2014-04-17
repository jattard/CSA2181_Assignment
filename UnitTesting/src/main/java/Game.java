package main.java;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import main.java.mapfactory.Map;
import main.java.mapfactory.MapCreator;
import main.java.observer.Observer;
import main.java.observer.Team;

public class Game {
	
	private Team[] teams;
	private static Player[] players;
	private int turn;
	private int size;
	private ArrayList<Integer> winners = new ArrayList<Integer>();
	
	public static Player[] getPlayers() {
		return players;
	}

	public Team[] getTeams() {
		return teams;
	}
	
	public void setPlayers(Player[] players) {
		Game.players = players;
	}
	
	public void initStartingPos()
	{
		for (int i=0; i < players.length; i++)
		{
			players[i].setStartingPosition(size);
			
			if (teams != null && teams.length != 0)
			{
				int teamNo = getTeam(players[i]);
				teams[teamNo].setTeamTrail(players[i].getPlayerTrail());
			}
		}
	}
	
	public boolean setNumPlayers(int numOfPlayers) {
		
		if (numOfPlayers < 2 || numOfPlayers > 8)
		{
			return false;
		}
		else
		{
			players = new Player[numOfPlayers];
			
			// initialize players
			for (int i=0; i < numOfPlayers; i++)
			{
				players[i] = new Player();
			}
			return true;
		}
	}
	
	public void initGame(int size, int mapType)
	{
		// set map size, generate map colors and set starting positions
		this.size = size;
		
		MapCreator creator = new MapCreator();
		creator.createMap(mapType, size, size);
		
		initStartingPos();
		turn = 0;
	}
	
	public boolean winGame(Color currentTileColor)
	{
		boolean winGame = false;

		if (currentTileColor == Color.YELLOW)
		{
			winners.add(turn);
			winGame = true;
		}
		
		return winGame;
	}
	
	public int getTeam(Player player) 
	{
		for (int i=0; i < teams.length; i++) {
			
			List<Observer> players = teams[i].getPlayers();
			
			for (int j=0; j < players.size(); j++) {
				
				if (players.get(j).equals(player))
					return i;
			}
		}
		return -1;
	}
	
	public void startGame()
	{
		Scanner kb = new Scanner(System.in);
		
		Position currentPos = null; 
		Color color = null;
		boolean gameEnded = false;

		try
		{
			do
			{
				int player = turn;
				char direction;
				
				int teamNo = getTeam(players[turn]);
				
				do {
					
					if (teams != null && teams.length != 0)
					{
						System.out.print("Team " + teamNo + ", ");  
					}
					
					System.out.println("Player " + (player + 1) + " where do you want to move? (U/D/L/R): ");
					direction = kb.next().charAt(0); 
				
				} while (!players[turn].move(direction, size)); // until player enters valid move char
								
				teams[teamNo].setTeamTrail(players[turn].getPlayerTrail());
				
				currentPos = players[turn].getPos();
				color = Map.getTiles()[currentPos.getY()][currentPos.getX()];
				
				if (color == Color.BLUE)
				{
					players[turn].setPosition(players[turn].getStartingPosition());
					System.out.println("WHOOOA! Player " + (player + 1) + " you drowned! :( \n");
				}
				
				generateHTMLFiles();
				boolean winGame = winGame(color);
				
				if (turn < (players.length - 1))
				{
					turn++;
				}
				else
				{
					turn = 0;
					
					if (winGame) 
						gameEnded = true;
				}
			}
			while (!gameEnded);
			
			for (int i=0; i < winners.size(); i++)
			{
				System.out.println("Congratulations! Player " + (winners.get(i) + 1) + " YOU WIN!!! :) ");
			}
		} 
		finally
		{
			kb.close();
		}
	}
	
	public String toHexString(Color colour) 
	{
		String hexColour = Integer.toHexString(colour.getRGB() & 0xffffff);
		  
		if (hexColour.length() < 6) {
			hexColour = "000000".substring(0, 6 - hexColour.length()) + hexColour;
		}
		  
		return "#" + hexColour;
	}
	
	public void generateHTMLFiles() 
	{
		File file = null;
		
		try
		{
			for (int i=0; i < players.length; i++)
			{
				file = new File("map_player_" + (i+1) + ".html");
				PrintWriter writer = new PrintWriter(file.getPath(), "UTF-8");
			
				writer.println("<html>");
				writer.println("<body>");
				writer.println("<table style=\"border-collapse: collapse;\">");
				writer.println("<tbody>");
				
				Color[][] tiles = Map.getTiles();
				
				for (int j=0; j < tiles.length; j++)
				{
					writer.println("<tr>");
					for (int k=0; k < tiles[j].length; k++)
					{
						if (players[i].getPlayerTrail()[j][k] == 1)
						{
							Color color = tiles[j][k];
							
							writer.println("<td style=\"background-color:" + toHexString(color) + 
									";width:50px;height:50px;border:1px solid black;" + "\">");
							
							if (color == Color.YELLOW)
							{
								writer.println("<img src=\"images/treasure.png\" width=\"50px\" height=\"50px\" style=\"opacity:0.7\">");
							}			
						}
						else
						{
		
							writer.println("<td style=\"background-color:#E8E8E8;width:50px;height:50px;border:1px solid black;" + "\">");
						}
						
						Position currentPosition = players[i].getPos();
						
						if (j == currentPosition.getY() && k == currentPosition.getX())
						{
							int pixels = (50 * j) + 10;
							writer.println("<img src=\"images/pirate.png\" width=\"50px\" height=\"50px\" style=\"z-index:2;position:absolute;top:" + pixels + "px;\">");
						}
						
						writer.println("</td>");

					}
					writer.println("</tr>");	
				}
				
				writer.println("</tbody>");
				writer.println("</table>");
				writer.println("</body>");
				writer.print("</html>");
				
				writer.flush();
				writer.close();
			}
		}
		catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		}
		catch (UnsupportedEncodingException e2)
		{
			e2.printStackTrace();
		}
	}
	
	public void initTeams(int noOfTeams) {
		
		teams = new Team[noOfTeams];
		
		for (int i=0; i < teams.length; i++) {
			teams[i] = new Team();
		}
	}
	
	public void assignTeams() {
		
		int teamno = -1;
		for (int i = 0; i < Game.getPlayers().length; i++) {
			
			if (i <= teams.length - 1) 
			{
				teams[i].register(Game.getPlayers()[i]);
				teamno = i;
			}
			else 
			{
				Random rand = new Random();
				int random = rand.nextInt(teams.length); // [0, teams.length)
				
				teams[random].register(Game.getPlayers()[i]);
				teamno = random;
			}
			
			Game.getPlayers()[i].setSubject(teams[teamno]);
		}
	}
	
	public static void main(String[] args) {
		
		Scanner kb = new Scanner(System.in);
		
		try
		{
			Game game = new Game();
			
			System.out.println("Enter Number of Teams (0 if no teams): ");
			int noOfTeams = kb.nextInt();
			game.initTeams(noOfTeams);
			
			int noOfPlayers;
			
			do 
			{
				System.out.println("Enter Number of Players (2-8): ");
				noOfPlayers = kb.nextInt();
				
			} while (!game.setNumPlayers(noOfPlayers));
			
			if (noOfTeams != 0)
				game.assignTeams();
			
			int size;
			do 
			{
				System.out.println("Enter map size: ");
				size = kb.nextInt();
				
			} while (!Map.setMapSize(size, size));
			
			System.out.println("Choose map type: (1) Safe (2) Hazardous: ");
			int mapType = kb.nextInt();
			
			game.initGame(size, mapType);
			game.generateHTMLFiles();
			game.startGame();
			
		}
		finally
		{
			kb.close();
		}
	}
}
