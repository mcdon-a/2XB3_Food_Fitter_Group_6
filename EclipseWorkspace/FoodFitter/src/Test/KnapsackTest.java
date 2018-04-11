package Test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Food.ReadCSV;
import Food.Knapsack;
import Food.Meal;

public class KnapsackTest {
	Knapsack sac = new Knapsack(ReadCSV.readFile());
	List<Meal> idealmeals = sac.mealsInRange(500, 700, 100);
	List<Meal> idealmeals2 = sac.mealsInRange(300, 500, 100);
	boolean[] range1 = {false, false};
	boolean[] range2 = {false, false};
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertTrue(idealmeals.size() == 1005);
		assertTrue(idealmeals2.size() == 1005);
		int currentindex = 0;
		for (int i = 0; i < idealmeals.size() - 1; i++) {
                if (idealmeals.get(i).getFoods().size() == 1 && idealmeals.get(i).getFoods().get(0).toString().equals("(Snacks, popcorn, oil-popped, white popcorn, with salt)")) {
					range1[currentindex] = true;
					currentindex++;
				}
				else if (idealmeals.get(i).getFoods().size() == 2 && idealmeals.get(i).getFoods().get(0).toString().equals("(Coffee, instant, decaffeinated, powder, water added)") && idealmeals.get(i).getFoods().get(1).toString().equals("(Snacks, potato chips, dried potatoes, plain)")) {
					range1[currentindex] = true;
					currentindex++;
				}
		}
		assertTrue(range1[0] && range1[1]);
		currentindex = 0;
		for (int i = 0; i < idealmeals2.size() - 1; i++) {
                if (idealmeals2.get(i).getFoods().size() == 1 && idealmeals2.get(i).getFoods().get(0).toString().equals("(Chicken, broiler, skin only, roasted)")) {
					range2[currentindex] = true;
					currentindex++;
				}
				else if (idealmeals2.get(i).getFoods().size() == 2 && idealmeals2.get(i).getFoods().get(0).toString().equals("(Tea, brewed)") && idealmeals2.get(i).getFoods().get(1).toString().equals("(Soup, NISSIN, OODLES OF NOODLES TOP RAMEN, ramen noodles, oriental flavour, dry)")) {
					range2[currentindex] = true;
					currentindex++;
				}
		}
		assertTrue(range2[0] && range2[1]);
	}

}
