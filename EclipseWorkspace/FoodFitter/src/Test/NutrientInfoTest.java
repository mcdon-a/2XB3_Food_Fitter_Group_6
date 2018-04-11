package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Food.NutrientInfo;

public class NutrientInfoTest {

	@Before
	public void setUp() throws Exception {
		NutrientInfo.init_info();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertTrue(NutrientInfo.get(211).getName().equals("GLUCOSE"));
		assertTrue(NutrientInfo.get(205).getName().equals("CARBOHYDRATE, TOTAL (BY DIFFERENCE)"));
		assertTrue(NutrientInfo.get(208).getName().equals("ENERGY (KILOCALORIES)"));
		assertTrue(NutrientInfo.get(203).getName().equals("PROTEIN"));
	}

}
