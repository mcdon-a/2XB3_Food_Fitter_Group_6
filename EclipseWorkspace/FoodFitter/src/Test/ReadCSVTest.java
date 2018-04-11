package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Food.NutrientInfo;
import Food.ReadCSV;

public class ReadCSVTest {
	int food = 500;
	int nutrient = 306;
	@Before
	public void setUp() throws Exception {
		NutrientInfo.init_info();
		ReadCSV.readFile();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertTrue(ReadCSV.readFile().get(food).toString().equals("(Chicken, broiler, back, meat and skin, roasted)"));
		assertTrue(ReadCSV.readFile().get(food).getNutr(nutrient) - 210.0 < 1e-17);
		assertTrue(NutrientInfo.get(nutrient).getUnit().equals("mg"));
		assertTrue(NutrientInfo.get(nutrient).getName().equals("POTASSIUM"));
	}

}
