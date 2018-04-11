package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Food.Food;
import Food.Meal;

public class MealTest {
	String[][] foodnames = {{"Cereal", "ready to eat", "Reese Puffs (Peanut butter)", "General Mills"}, {"Turkey", "all classes", "gizzard", "raw"}, {"Spices", "allspice", "ground"}, {"Peach", "canned halves or slices", "extra light syrup pack", "solids and liquid"}};
	Food[] food = {new Food(foodnames[0]), new Food(foodnames[1]), new Food(foodnames[2]), new Food(foodnames[3])};
	Meal meal1 = new Meal(food[0]);
	Meal meal2 = meal1.copy();
	Meal meal3 = new Meal(food[3]);
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void test() {
		assertTrue(meal1.getFoods().get(0).toString().equals(meal2.getFoods().get(0).toString()));
		assertTrue(meal1.get(0).toString().equals("(Cereal, ready to eat, Reese Puffs (Peanut butter), General Mills)"));
		meal1.add(food[1]);
		assertTrue(meal1.get(1).toString().equals("(Turkey, all classes, gizzard, raw)"));
		meal2.add(food[2]);
		assertTrue(meal2.get(1).toString().equals("(Spices, allspice, ground)"));
		Meal meal4 = meal3.mergeMeal(meal1);
		assertTrue(meal4.get(0).toString().equals("(Peach, canned halves or slices, extra light syrup pack, solids and liquid)"));
		assertTrue(meal4.get(1).toString().equals("(Cereal, ready to eat, Reese Puffs (Peanut butter), General Mills)"));
		assertTrue(meal4.get(2).toString().equals("(Turkey, all classes, gizzard, raw)"));
		meal4.remove(0);
		assertTrue(meal4.get(1).toString().equals("(Turkey, all classes, gizzard, raw)"));
	}

}
