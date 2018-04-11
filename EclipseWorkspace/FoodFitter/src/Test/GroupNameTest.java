package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Food.GroupName;

public class GroupNameTest {
	
	GroupName group1 = new GroupName(4, "Oils");
	GroupName group2 = new GroupName(40, "Bad group");
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertTrue(group1.getFullName().equals("Oils"));
		assertTrue(group1.getName().equals("Fats and oils"));
		assertTrue(group2.getFullName().equals("Bad group"));
		assertTrue(group2.getName().equals("Invalid group"));
		
	}

}
