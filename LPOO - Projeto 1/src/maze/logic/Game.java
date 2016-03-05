package maze.logic;

import java.util.Scanner;
import java.util.Random;

public class Game {
	
	/****ATRIBUTOS****/
	
	private Maze maze = new Maze();
	private Hero hero = new Hero();
	private Dragon dragon = new Dragon();
	private Sword sword = new Sword();
	private boolean end = false;

	
	/****METODOS****/ 
	
	public char charAt(Position pos)
	{
		char aux = '0';
		
		for (int i = 0; i < maze.symbols.length; i++)
		{
			for (int j = 0; j < maze.symbols[i].length; j++)
			{
				if((i == pos.y )&&( j == pos.x))
				{
					aux = maze.symbols[i][j];
				}
			}
		}
		
		return aux;
	}
	
	
	public void getNewHeroPosition()
	{
		
		Position temp = new Position();
		temp.x = hero.pos.x;
		temp.y = hero.pos.y;

		Scanner in = new Scanner(System.in);
		char move;
		
		System.out.println("Move: ");
		move = in.next().charAt(0);
		
		if (!(move == 'w' || move == 'a'|| move == 's'|| move == 'd'))
			System.out.println("Error!");
		
		
	
			switch (move)
			{
			case 'w':
				temp.y = temp.y - 1;
				if(analiseNewHeroPosition(charAt(temp)))
				hero.pos.y = hero.pos.y - 1;			
				break;
			case 'a': 
				temp.x = temp.x - 1;
				if(analiseNewHeroPosition(charAt(temp)))
				hero.pos.x = hero.pos.x - 1;
				break;
			case 's': 
				temp.y = temp.y + 1;
				if(analiseNewHeroPosition(charAt(temp)))
				hero.pos.y = hero.pos.y + 1;
				break;
			case 'd': 
				temp.x = temp.x + 1;
				if(analiseNewHeroPosition(charAt(temp)))
				hero.pos.x = hero.pos.x + 1;
				break;
			}

	}
		
	public boolean analiseNewHeroPosition(char letra)
	{
		if(!(hero.getArmed()))
		{
			switch(letra)
			{
			case 'X': 
				return false;
				//break;
			case '0':
				return true;
				//break;
			case 'E': 
				hero.updateArmed(true);
				hero.updateLetter('A');
				return true;
				//break;
			case 'D':
				hero.dead = true;
				return true;
				//break;
			case 'S':
				System.out.println("The Dragon is still alive!");
				return false;
				//break;				
			}
		}
		else
		{
			switch(letra)
			{
			case 'X': 
				return false;
				//break;
			case '0':
				return true;
				//break;			
			case 'D':
				dragon.updateDeathStatus(true);
				dragon.updateLetter('0');
				return true;
				//break;
			case 'S':
				if(dragon.getDeathStatus())
					{	
						
						return true;
						//break;
					}
				else
					{System.out.println("The Dragon is still alive!");
						return false;
						//break;
					}
			}
		}
		return false;

	}
	
	public void getNewDragonPosition()
	{
		dragon.prePos = dragon.pos;
		
		Random in = new Random();
		int move = in.nextInt(4);
		

		switch (move)
		{
		case '0': dragon.pos.y = dragon.pos.y - 1;
		break;
		case '1': dragon.pos.x = dragon.pos.x - 1;
		break;
		case '2': dragon.pos.y = dragon.pos.y + 1;
		break;
		case '3': dragon.pos.x = dragon.pos.x + 1;
		break;
		
		}
		
	}
	
	public void analiseNewDragonPosition(char letra)
	{
		switch(letra)
			{
			case 'X': 
				dragon.pos = dragon.prePos;
				break;
			case 'S':
				dragon.pos = dragon.prePos;
				break;
			case '0':
				dragon.updateLetter('D');
				break;
			case 'E': 
				dragon.updateLetter('F');
				break;
			case 'H':
				hero.updateDeathStatus(true);
				break;
			case 'A':
				dragon.updateDeathStatus(true);
				break;
			}

	}
	
	public static void main(String[] args) 
	{
		Game game = new Game();
		
		game.initGame();
		

		game.maze.printMaze();
		
		while(!(game.end)){
		game.maze.updateMaze(game.hero.getPosition(), '0');
		game.maze.updateMaze(game.dragon.getPosition(), '0');
			
		game.getNewHeroPosition();
		//game.analiseNewHeroPosition(game.hero.getLetter());


		game.getNewDragonPosition();
		//game.analiseNewHeroPosition(game.dragon.getLetter());
		
		game.maze.updateMaze(game.hero.getPosition(), game.hero.getLetter());
		game.maze.updateMaze(game.dragon.getPosition(), game.dragon.getLetter());
		
		game.maze.printMaze();
		}
	}
	
	public void initGame()
	{
		
	hero.updatePosition(maze.findPos(hero.getLetter())); // initial Position of the "H" letter
	dragon.updatePosition(maze.findPos(dragon.getLetter()));
	sword.updatePosition(maze.findPos(sword.getLetter()));
	

	}
	
	
}
