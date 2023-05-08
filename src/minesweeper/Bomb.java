package minesweeper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class Bomb{	
	private int numOfBombs;
	private final int PAIR = 2;
	private final int MIN = 1;
	private ArrayList<ArrayList<Integer>> bombLocations;
	
//	public Bomb() {
//		this(10);
//	}
	
	public Bomb(int numOfBombs) {
		this.numOfBombs = numOfBombs;
		this.bombLocations = createBombLocations();
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
	
	public boolean hasDuplicates() {
		Collection<ArrayList<Integer>> uniqueBombs = bombLocations.stream()
				.distinct()
				.collect(Collectors.toCollection(ArrayList::new));
		
		return (uniqueBombs.size() != bombLocations.size()) ? true : false;
	}
	
	public boolean isBombLocation( int[] userInput) {
		ArrayList<Integer> temp = new ArrayList<>();
		temp.add(userInput[0]);
		temp.add(userInput[1]);
		
		return (bombLocations.contains(temp)) ? true : false;
	}

	public ArrayList<ArrayList<Integer>> getBombLocations() {
		return bombLocations;
	}

	public void setBombLocations() {
		this.bombLocations = createBombLocations();
	}
	
}
