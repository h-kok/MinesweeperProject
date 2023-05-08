package minesweeper;

import java.util.*;

public class Main {
	public static void main (String[] args) {
		
		int gridDimensions = 0;
		boolean notGameOver = true;
		int x = 0;
		int y = 0;
		int[] input = new int[] {x,y};

		UserInput userInput = new UserInput(input);
		
		String count;
		
		System.out.println("Welcome to minesweeper.");
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
		
		System.out.println("\nHow to play:");
		System.out.println("1. Enter a pair of coordinates (row, column) to select an item on the grid.\n Rows run from top to bottom, starting at 1.\n Columns run from left to right, starting at 1.\n");
		System.out.printf("2. There are %d hidden bombs in the grid. \nIf you select one of the bombs, game over. If you select a safe coordinate, the number revealed represents how many bombs are located in the immediate coordinates surrounding it.\n", gridDimensions);
		System.out.println("\n3. Try to reveal all coordinates without setting the bombs off. Happy playing.\n");
		
		Grid minesweeper = new Grid(gridDimensions, input);
		minesweeper.printGrid();

		Bomb bombs = new Bomb(gridDimensions);
		
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
			
//			userInput.setUserInput(input); 
//			System.out.println(Arrays.toString(input));
			
////			while (true ) {
//				try {
//					System.out.println("\nPlease enter a row number and hit 'enter': ");
//					x = s.nextInt();
////					y = s.nextInt();
//					while (x < 1 || x > gridDimensions) {
//						System.out.printf("Your value is out of range. \nColumn value should be from 1 to %d. \n please enter a row number:", gridDimensions);
//						x = s.nextInt();
//					}
//					input[0] = x;
////					input[1] = y;
////					userInput.setUserInput(input);
////					break;
//				} catch (IndexOutOfBoundsException | InputMismatchException e) {
//					System.out.printf("Your value is not a number. \nRow value should be from 1 to %d.", gridDimensions);
////					s.next();
////					continue;
//				}
//				
////			}
//			
////			while ( true) {
//				try {
//					System.out.println("\nPlease enter a column number and hit 'enter': ");
////					x = s.nextInt();
//					y = s.nextInt();
//					while ( y < 1 || y > gridDimensions) {
//						System.out.printf("Your value is out of range or not a number. \nColumn value should be from 1 to %d. \nPlease enter a column number:", gridDimensions);
//						y = s.nextInt();
////						break;
//					}
////					input[0] = x;
//					input[1] = y;
//					
////					break;
//				} catch (IndexOutOfBoundsException | InputMismatchException e) {
//					System.out.printf("Your value is out of range or not a number. \nColumn value should be from 1 to %d.", gridDimensions);
////					s.next();
////					continue;
//				}
//				
////			}
			
			while (x < 1 || x > gridDimensions || y < 1 || y > gridDimensions) {
				System.out.printf("Your value is out of range or not a number. \nColumn value should be from 1 to %d.", gridDimensions);
				x = s.nextInt();
				y = s.nextInt();
			}
			input[0] = x;
			input[1] = y;
			userInput.setUserInput(input);

			if (bombs.isBombLocation(userInput.getUserInput()) == true) {
				System.out.println("Boom! \nGame Over. Better luck next time.");
				minesweeper.updateGrid("!");
				minesweeper.updateGridWithBombs(bombs.getBombLocations());
				minesweeper.printGrid();
				notGameOver = false;
			} else {
				count = minesweeper.checkForSurroundingBombs(bombs.getBombLocations());
				minesweeper.updateGrid(count);
				minesweeper.printGrid();
			}
			
			if (minesweeper.allBombsFound(gridDimensions) == true) {
				System.out.println("Congratulations, you found all the bombs!");
				notGameOver = false;
			}
		}

		s.close();
	}
}
