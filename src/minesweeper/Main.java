package minesweeper;

import java.util.*;

public class Main {
	public static void main (String[] args) {
		
		int gridDimensions;
		boolean notGameOver = true;
		int x;
		int y;
		int[] input = new int[2];
		String count;
		
		System.out.println("Welcome to minesweeper.");
		Scanner s = new Scanner(System.in);
		
		while (true) {
			try {
				System.out.println("Enter a number to set the dimensions of the mine (min: 2): ");
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
		
		System.out.println("How to play:");
		System.out.println("1. Enter a pair of coordinates (row, column) to select an item on the grid.\n Rows run from top to bottom, starting at 1.\n Columns from left to right, starting at 1.\n");
		System.out.printf("2. There are %d hidden bombs in the grid. \nIf you select one of the bombs, game over. If you select a safe coordinate, the number revealed represents how many bombs are located in the immediate coordinates surrounding it.\n", gridDimensions);
		System.out.println("3. Try to reveal all coordinates without setting the bombs off. Happy playing.\n");
		
		//make grid
		Grid minesweeper = new Grid(gridDimensions);
		ArrayList<ArrayList<String>> grid = minesweeper.createGrid();
		
		//make bombs
		Bomb bombs = new Bomb(gridDimensions);
		ArrayList<ArrayList<Integer>> bombLocations = bombs.createBombLocations();
		
		while(bombs.hasDuplicates(bombLocations)==true) {
			bombLocations = bombs.createBombLocations();
		}
		
		while (notGameOver) {
			
			while (true) {
				try {
					System.out.println("Please enter a pair of coordinates, separated by a *space* and hit 'enter': ");
					x = s.nextInt();
					y = s.nextInt();
					break;
				}
				catch (InputMismatchException e) {
					System.out.println("One of your values was not a number, Please try again.");
					s.next();
					continue;
				}
			}
			
			while (x < 1 || x > gridDimensions || y < 1 || y > gridDimensions) {
				System.out.printf("One of your coordinates is out of range. \nCoordinate values should be from 1 to %d. \nPlease enter a pair of coordinates, separated by a *space* and hit 'enter': ", gridDimensions);
				x = s.nextInt();
				y = s.nextInt();
			}
			
			input[0] = x;
			input[1] = y;
			
			if (bombs.isBombLocation(bombLocations,input) == true) {
				System.out.println("Boom! \nGame Over. Better luck next time.");
				minesweeper.updateGrid(grid, input, "!");
				minesweeper.updateGridWithBombs(grid, bombLocations);
				minesweeper.printGrid(grid);
				notGameOver = false;
			} else {
				ArrayList<ArrayList<Integer>>surroundingCoords = minesweeper.getSurroundingCoordinates(input);
				count = minesweeper.checkForSurroundingBombs(surroundingCoords, bombLocations);
				minesweeper.updateGrid(grid, input, count);
				minesweeper.printGrid(grid);
			}
			
			if (bombs.allBombsFound(grid) == true) {
				System.out.println("Congratulations, you found all the bombs!");
				notGameOver = false;
			}
		}

		s.close();
	}
}
