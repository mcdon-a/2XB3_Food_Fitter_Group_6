package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Food.GroupInfo;

public class GroupInfoTest {
	int groupChoice = 12;
	@Before
	public void setUp() throws Exception {
		GroupInfo.init_info();
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void test() {
		assertTrue(GroupInfo.get(groupChoice).getName().equals("Meat and alternatives"));
	}

}
