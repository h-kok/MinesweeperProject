package minesweeper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class Bomb{	
	private int numOfBombs;
	private final int PAIR = 2;
	private final int MIN = 1;
	
	public Bomb() {
		this(10);
	}
	
	public Bomb(int numOfBombs) {
		this.numOfBombs = numOfBombs;
	}
	
	public ArrayList<ArrayList<Integer>> createBombLocations() {
		ArrayList<ArrayList<Integer>> bombLocations = new ArrayList<>();

		for(int i=0; i<numOfBombs; i++) {
			ArrayList<Integer> row = new ArrayList<Integer>();
			for(int j=0; j<PAIR; j++) {
				row.add(j);
				row.set(j, (int)(Math.random()*(numOfBombs-MIN+1))+MIN);
			}
			bombLocations.add(row);
		}
		
		return bombLocations;
	}
	
	public boolean hasDuplicates(ArrayList<ArrayList<Integer>> bombLocations) {
		Collection<ArrayList<Integer>> uniqueBombs = bombLocations.stream()
				.distinct()
				.collect(Collectors.toCollection(ArrayList::new));
		
		return (uniqueBombs.size() != bombLocations.size()) ? true : false;
	}
	
	public boolean isBombLocation(ArrayList<ArrayList<Integer>> bombLocations, int[] userInput) {
		ArrayList<Integer> temp = new ArrayList<>();
		temp.add(userInput[0]);
		temp.add(userInput[1]);
		
		return (bombLocations.contains(temp)) ? true : false;
	}
	
	public boolean allBombsFound(ArrayList<ArrayList<String>> grid) {
		return (grid.stream()
				.flatMap(row -> row.stream())
				.filter(el -> el == "?")
				.count() == numOfBombs) ? true : false;
	}
}