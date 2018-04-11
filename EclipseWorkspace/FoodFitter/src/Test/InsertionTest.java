package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Food.Food;
import Food.Insertion;

public class InsertionTest {
	String[][] descriptions = {{"Cereal", "ready to eat", "Reese Puffs (Peanut butter)", "General Mills"}, {"Turkey", "all classes", "gizzard", "raw"}, {"Spices", "allspice", "ground"}, {"Peach", "canned halves or slices", "extra light syrup pack", "solids and liquid"}};
	Food[] foods = {new Food(descriptions[0]), new Food(descriptions[1]), new Food(descriptions[2]), new Food(descriptions[3])};
	Integer[] nutrientIDs = {203, 204, 208};
	Double[][] nutrientValues = {{6.93, 10.5, 419.0}, {18.8, 3.37, 111.0}, {6.09, 8.69, 263.0}, {0.4, 0.1, 42.0}};
	@Before
	public void setUp() throws Exception {
		for (int i = 0; i < foods.length; i++) {
			for (int j = 0; j < 3; j++) {
				foods[i].addNutr(nutrientIDs[j], nutrientValues[i][j]);
			}
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Insertion.sort(foods, 203, 4, 0, 3);
		assertTrue(foods[0].toString().equals("(Peach, canned halves or slices, extra light syrup pack, solids and liquid)"));
		assertTrue(foods[1].toString().equals("(Spices, allspice, ground)"));
		assertTrue(foods[2].toString().equals("(Cereal, ready to eat, Reese Puffs (Peanut butter), General Mills)"));
		assertTrue(foods[3].toString().equals("(Turkey, all classes, gizzard, raw)"));
		Insertion.sort(foods, 204, 4, 0, 3);
		assertTrue(foods[0].toString().equals("(Peach, canned halves or slices, extra light syrup pack, solids and liquid)"));
		assertTrue(foods[1].toString().equals("(Turkey, all classes, gizzard, raw)"));
		assertTrue(foods[2].toString().equals("(Spices, allspice, ground)"));
		assertTrue(foods[3].toString().equals("(Cereal, ready to eat, Reese Puffs (Peanut butter), General Mills)"));
		Insertion.sort(foods, 208, 4, 0, 3);
		assertTrue(foods[0].toString().equals("(Peach, canned halves or slices, extra light syrup pack, solids and liquid)"));
		assertTrue(foods[1].toString().equals("(Turkey, all classes, gizzard, raw)"));
		assertTrue(foods[2].toString().equals("(Spices, allspice, ground)"));
		assertTrue(foods[3].toString().equals("(Cereal, ready to eat, Reese Puffs (Peanut butter), General Mills)"));
	}

}
