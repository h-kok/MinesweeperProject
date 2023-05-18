package minesweeper;

import java.util.*;

public class Main {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_BRIGHT_YELLOW = "\u001B[33;1m";
	public static final String ANSI_BRIGHT_GREEN = "\u001B[32;1m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_BRIGHT_MAGENTA = "\u001B[35;1m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_BRIGHT_CYAN = "\u001B[36;1m";
	public static final String ANSI_BRIGHT_WHITE = "\u001B[37;1m";
	
	public static void main (String[] args) {
		
		
		int gridDimensions = 0;
		boolean notGameOver = true;
		String count;
		int x = 0;
		int y = 0;
		int[] input = new int[] {x,y};

		UserInput userInput = new UserInput(input);
		
		System.out.println(ANSI_BRIGHT_WHITE+"Welcome to minesweeper."+ANSI_RESET);

		Scanner s = new Scanner(System.in);
		
			
			while (true) {
			try {
				System.out.println("\nEnter a number to set the dimensions of the mine (min: 2): ");
				gridDimensions = s.nextInt();
				break;
			}
			catch (InputMismatchException e) {
				System.out.println("That was not a valid input.");
				s.next();
				continue;
			}
		}

		while (gridDimensions <=1) {		
			System.out.println("The minimum possible dimension is 2x2. \nEnter a number to set the dimensions of the mine (min: 2): ");
			gridDimensions = s.nextInt();
		}
		
		System.out.println(ANSI_BRIGHT_MAGENTA+"\nHow to play:"+ANSI_RESET);
		System.out.println(ANSI_RED+"1. Enter a pair of coordinates (row, column) to select an item on the grid."+ANSI_BRIGHT_YELLOW+"\n Rows run from top to bottom, starting at 1."+ANSI_BRIGHT_GREEN+"\n Columns run from left to right, starting at 1.\n"+ANSI_RESET);
		System.out.printf(ANSI_GREEN+"2. There are %d hidden bombs in the grid."+ ANSI_BRIGHT_CYAN+"\nIf you select one of the bombs, game over. If you select a safe coordinate, the number revealed represents how many bombs are located in the immediate coordinates surrounding it.\n"+ANSI_RESET, gridDimensions);
		System.out.println(ANSI_BRIGHT_MAGENTA+"\n3. Try to reveal all coordinates without setting the bombs off. Happy playing.\n"+ANSI_RESET);
		
		Grid minesweeper = new Grid(gridDimensions);
		minesweeper.printGrid();

		Bombs bombs = new Bombs(gridDimensions);
		
		while(bombs.hasDuplicates()==true) {
			bombs.setBombLocations();
		}
		
		while (notGameOver) {
			
			while (true) {
				try {
					System.out.println("\nPlease enter a pair of coordinates, separated by a *space* and hit 'enter': ");
					x = s.nextInt();
					y = s.nextInt();
					input[0] = x;
					input[1] = y;
					break;
				}
				catch (InputMismatchException e) {
					System.out.printf("%d, %d", x, y);
					System.out.println("One of your values was not a number, Please try again.");
					s.next();
					continue;
				}
			}
			
			while (x < 1 || x > gridDimensions || y < 1 || y > gridDimensions) {
				System.out.printf("Your value is out of range or not a number. \nColumn value should be from 1 to %d.", gridDimensions);
				x = s.nextInt();
				y = s.nextInt();
			}
			input[0] = x;
			input[1] = y;
			userInput.setUserInput(input);
			
			
			if (minesweeper.allBombsFound(gridDimensions) == true) {
				System.out.println("Congratulations, you found all the bombs!");
				notGameOver = false;
			}
			else if (bombs.isBombLocation(userInput.getUserInput()) == true) {
				System.out.println(ANSI_BRIGHT_YELLOW+"Boom!"+ANSI_RESET+"\nGame Over. Better luck next time.");
				minesweeper.updateGrid("!", userInput.getUserInput());
				minesweeper.updateGridWithBombs(bombs.getBombLocations());
				minesweeper.printGrid();
				notGameOver = false;
			} else {
				count = ANSI_CYAN+minesweeper.checkForSurroundingBombs(bombs.getBombLocations(), userInput.getUserInput())+ANSI_RESET;
				minesweeper.updateGrid(count, userInput.getUserInput());
				System.out.println(minesweeper.updateGrid(count, userInput.getUserInput()).get(0).get(0));
				minesweeper.printGrid();
			}

		}
		
		s.close();
	}
}
