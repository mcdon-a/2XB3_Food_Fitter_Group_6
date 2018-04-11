package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Food.Knapsack;
import Food.Meal;
import Food.NutrientTarget;
import Food.ReadCSV;
import Food.Fitter;
import Food.Food;

public class FitterTest {
	Knapsack sac = new Knapsack(ReadCSV.readFile());
	NutrientTarget target = new NutrientTarget(2000);
	String[] foodarray = {"Cereal", "ready to eat", "Reese Puffs (Peanut butter)", "General Mills"};
	String[] foodfits = {"(Turkey, all classes, dark meat only, roasted)",  "(Animal fat, beef tallow)", "(Animal fat, beef tallow)"};
	String[] foodfits1 = {"(Turkey, all classes, dark meat only, roasted)",  "(Animal fat, beef tallow)", "(Animal fat, beef tallow)", "(Cereal, ready to eat, Reese Puffs (Peanut butter), General Mills)"};
	Meal meal1 = new Meal(new Food(foodarray));
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void test() {
		for (int i = 0; i < Fitter.fitNutrTar(sac, target).getFoods().size(); i++) {
			assertTrue(foodfits[i].equals(Fitter.fitNutrTar(sac, target).getFoods().get(i).toString()));
		}
		for (int i = 0; i < Fitter.fitNutrTarFromMeal(sac, target, meal1).getFoods().size(); i++) {
			assertTrue(foodfits1[i].equals(Fitter.fitNutrTarFromMeal(sac, target, meal1).getFoods().get(i).toString()));
		}
	}
}
