package Food;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NutrientTargetTest {
	NutrientTarget target1 = new NutrientTarget(900);
	String[][] foodnames = {{"Cereal", "ready to eat", "Reese Puffs (Peanut butter)", "General Mills"}, {"Turkey", "all classes", "gizzard", "raw"}, {"Spices", "allspice", "ground"}, {"Peach", "canned halves or slices", "extra light syrup pack", "solids and liquid"}};
	Food[] food = {new Food(foodnames[0]), new Food(foodnames[1]), new Food(foodnames[2]), new Food(foodnames[3])};
	Meal meal1 = new Meal(food[0]);
	@Before
	public void setUp() throws Exception {
		target1.addNutrient(203,35.22);
		target1.addNutrient(204, 28.66);
		meal1.add(food[1]);
		meal1.add(food[2]);
		meal1.add(food[3]);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertTrue(target1.getAmt(203) == 35.22);
		assertTrue(target1.getAmt(204) == 28.66);
		assertTrue(target1.getNutrients().size() == 2);
		assertTrue(target1.getNutrients().get(0) == 203);
		assertTrue(target1.getNutrients().get(1) == 204);
		target1.removeNutrient(203);
		assertTrue(target1.getNutrients().size() == 1);
		target1.addNutrient(204, 28.66);
		assertTrue(target1.sqrdErr(meal1) - 3.0 < 1e-17);
	}

}
