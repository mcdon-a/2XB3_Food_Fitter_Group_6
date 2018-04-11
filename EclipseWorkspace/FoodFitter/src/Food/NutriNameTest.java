package Food;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NutriNameTest {
	NutriName nutri1 = new NutriName(203, 203, "PROT", "g", "PROTEIN", "PROTÉINES", "PROCNT", 2);
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertTrue(nutri1.getID() == 203);
		assertTrue(nutri1.getCode() == 203);
		assertTrue(nutri1.getSymbol().equals("PROT"));
		assertTrue(nutri1.getUnit().equals("g"));
		assertTrue(nutri1.getName().equals("PROTEIN"));
		assertTrue(nutri1.getNameF().equals("PROTÉINES"));
		assertTrue(nutri1.getTagname().equals("PROCNT"));
		assertTrue(nutri1.getDecimals() == 2);
	}

}
