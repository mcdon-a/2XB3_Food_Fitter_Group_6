package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Food.Food;
import Food.TimSort;

public class TimSortTest {
	
	Food[] toSort;
	public final int NUTR_ID = 1;
	private final int ARRAY_SIZE = 100;

	@Before
	public void setUp() throws Exception {
		toSort = new Food[ARRAY_SIZE];
		for (int i = 0; i < ARRAY_SIZE; i++) {
			toSort[i] = new Food(new String[] {});
			toSort[i].addNutr(NUTR_ID, (int)(Math.random()*1000));
		}
		TimSort.sortMerge(toSort, NUTR_ID);
	}

	@Test
	public void testSortMerge() {
		for (int i = 0; i < ARRAY_SIZE - 1; i++) {
			assertTrue(toSort[i].getNutr(NUTR_ID) <= toSort[i+1].getNutr(NUTR_ID));
		}
	}

}
