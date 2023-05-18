package minesweeper;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

public class BombsTest {
	static Bombs bombs;

	
	@Test
	void createBombLocations_BombsArrayListHasDuplicates_ReturnsEqualLengthList() {
		bombs = new Bombs(10);
		assertEquals(10, bombs.getBombLocations().size());
	}
	


}
