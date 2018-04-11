package Food;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FoodTest {
	String[] description = {"Cereal", "ready to eat", "Reese Puffs (Peanut butter)", "General Mills"};
	Food food1 = new Food(description);
	Integer[] nutrientIDs = {203, 204, 205};
	Double[] nutrientValues = {6.93, 10.5, 76.13};
	@Before
	public void setUp() throws Exception {
		for (int i = 0; i < 3; i++) {
			food1.addNutr(nutrientIDs[i], nutrientValues[i]);
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		for (int i = 0; i < 4; i++) {
			assertTrue(food1.getDescriptor()[i].equals(description[i]));
		}
		assertTrue(food1.allDescriptors().equals("Cereal, ready to eat, Reese Puffs (Peanut butter), General Mills"));
		assertTrue(food1.getNutr(203) - 6.93 < 1e-17);
		assertTrue(food1.getNutr(204) - 10.5 < 1e-17);
		assertTrue(food1.getNutr(205) - 76.13 < 1e-17);
		for (int i = 0; i < 3; i++) {
			assertTrue(food1.getNutrients().get(i).equals(nutrientIDs[i]));
		}
	    assertTrue(food1.toString().equals("(Cereal, ready to eat, Reese Puffs (Peanut butter), General Mills)"));	
	}

}
