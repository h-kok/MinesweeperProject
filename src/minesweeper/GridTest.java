package minesweeper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GridTest {
	static Grid grid;
	static final String ANSI_RESET = "\u001B[0m";
	static final String ANSI_CYAN = "\u001B[36m";
	
	@BeforeEach
	void setUpGrid() {
		grid = new Grid(5);
	}
	
	@Test
	void createGrid_GridWithFiveRowsAndColumns_ReturnsEqualLengthArrayList() {
		assertEquals(5, grid.createGrid().size());
	}
	
	@Test
	void createGrid_ValueAtAnyRowOrColumn_ReturnsQuestionMark() {
		assertEquals("?", grid.createGrid().get(0).get(0));
		assertEquals("?", grid.createGrid().get(2).get(3));
	}
	@Test 
	void updateGrid_UpdateGridWithValidInputs_ReturnsEqualLengthArrayList(){
		int[] coord = {1,1};
		String count = "7";
		ArrayList<ArrayList<String>> updatedGrid = grid.updateGrid(count, coord);
		assertEquals(5, updatedGrid.size());
	}
	
	@Test
	void updateGrid_UpdateWithValidInputs_ReturnsSpecifiedCountAtSpecifiedCoord() {
		int[] coord = {1,2};
		String count = "7";
		ArrayList<ArrayList<String>> updatedGrid = grid.updateGrid(count, coord);
		assertEquals("7", updatedGrid.get(0).get(1));
	}
	
	@Test
	void allBombsFound_25Bombs_ReturnsTrue(){
		assertTrue(grid.allBombsFound(25));
	}
	
	@Test
	void allBombsFound_5Bombs_ReturnsFalse(){
		assertFalse(grid.allBombsFound(5));
	}
	
	@Test
	void getSurroundingCoordinates_ValidInput_ReturnsArrayListOfLength8() {
		int[] coord = {1,1};
		assertEquals(8, grid.getSurroundingCoordinates(coord).size());
	}
	
	@Test
	void updateGridWithBombs_ValidInput_ReturnsExclamationMarkAtBombLocation() {
		ArrayList<ArrayList<Integer>> bombLocations = new ArrayList<ArrayList<Integer>>();
		bombLocations.add(0, new ArrayList<Integer>(Arrays.asList(1,1)));
		bombLocations.add(1, new ArrayList<Integer>(Arrays.asList(2,2)));
		assertEquals("!", grid.updateGridWithBombs(bombLocations).get(1).get(1));
	}
	
}
