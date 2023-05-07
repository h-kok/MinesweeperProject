package minesweeper;

import java.util.ArrayList;
import java.util.Arrays;

public class Grid {
	private int gridDimensions;
//	private <ArrayList<ArrayList<String>> grid;
	
	public Grid() {
		this(10);
	}
	
	public Grid(int gridDimensions) {
		this.gridDimensions = gridDimensions;
//		this.createGrid();
//		this.grid = grid;
	}
	
	public ArrayList<ArrayList<String>> createGrid() {
		ArrayList<ArrayList<String>> grid = new ArrayList<ArrayList<String>>();
		for (int i=0; i<gridDimensions; i++) {
			grid.add(new ArrayList<String>());
			
			for (int j=0; j<gridDimensions; j++) {
				grid.get(i).add("?");
			}
//			System.out.println(grid.get(i));
		}
		return grid;
	}
	
//	public void setGrid() {
//		this.updateGrid();
//	}
	
	public ArrayList<ArrayList<String>> updateGrid(ArrayList<ArrayList<String>> grid, int[] userInput, String count) {
//		ArrayList<ArrayList<String>> grid = this.createGrid();
		
		ArrayList<Integer> temp = new ArrayList<>();
		temp.add(userInput[0]);
		temp.add(userInput[1]);
		
		grid.get(temp.get(0)-1).set(temp.get(1)-1, count);
		
		return grid;
	}
	
	public ArrayList<ArrayList<Integer>> getSurroundingCoordinates(int[] userInput) {
		ArrayList<ArrayList<Integer>> surroundingCoords = new ArrayList<>();
		
		surroundingCoords.add(new ArrayList<Integer>(Arrays.asList(userInput[0]-1, userInput[1]-1)));
		surroundingCoords.add(new ArrayList<Integer>(Arrays.asList(userInput[0]-1, userInput[1])));
		surroundingCoords.add(new ArrayList<Integer>(Arrays.asList(userInput[0]-1, userInput[1]+1)));
		surroundingCoords.add(new ArrayList<Integer>(Arrays.asList(userInput[0], userInput[1]-1)));
		surroundingCoords.add(new ArrayList<Integer>(Arrays.asList(userInput[0], userInput[1]+1)));
		surroundingCoords.add(new ArrayList<Integer>(Arrays.asList(userInput[0]+1, userInput[1]-1)));
		surroundingCoords.add(new ArrayList<Integer>(Arrays.asList(userInput[0]+1, userInput[1])));
		surroundingCoords.add(new ArrayList<Integer>(Arrays.asList(userInput[0]+1, userInput[1]+1)));
		
		return surroundingCoords;
	}
	
	public String checkForSurroundingBombs(ArrayList<ArrayList<Integer>>surroundingCoords, ArrayList<ArrayList<Integer>>bombLocations) {
		
		int count = 0;
		
		for(ArrayList<Integer> coord : surroundingCoords) {
			if (bombLocations.contains(coord)) {
				count+=1;
			}
		}
		return Integer.toString(count);
	}
	
	public ArrayList<ArrayList<String>> updateGridWithBombs(ArrayList<ArrayList<String>> grid, ArrayList<ArrayList<Integer>> bombLocations){
		
		for (int i=0; i<bombLocations.size(); i++) {
			int row = bombLocations.get(i).get(0);
			int column = bombLocations.get(i).get(1);
			grid.get(row-1).set(column-1,"!");
		}
		return grid;
	}
	
	public void printGrid(ArrayList<ArrayList<String>> grid) {
//		ArrayList<ArrayList<String>> grid = this.createGrid();
		
		for (ArrayList<String> row : grid) {
			for (String el : row) {
				System.out.print("|"+el+"|");
			}
			System.out.println();
		}
	}
	
	// cascade function
	// if check for surrounding bombs count = 0
	// filter the surrounding coordinates, none should have value of <1 or > grid dimensions, return array
	// apply check surrounding bombs to those coordinates
	// filter surrounding coordinates 
	// if count = 0, repeat process.
}
