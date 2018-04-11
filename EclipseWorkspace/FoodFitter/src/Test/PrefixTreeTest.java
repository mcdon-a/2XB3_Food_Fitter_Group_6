package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Food.Food;
import Food.PrefixTree;

public class PrefixTreeTest {
	private PrefixTree tree;
	private String[] desc1 = {"pizza","pineapple","large"};
	private String[] desc2 = {"pizza","pineapple","small"};
	private String[] desc3 = {"hotdog","bun","ketchup"};
	private String[] desc4 = {"hamburger","ketchup"};
	private String[] desc5 = {"tide pods","detergent"};
	private Food food1 = new Food(desc1);
	private Food food2 = new Food(desc2);
	private Food food3 = new Food(desc3);
	private Food food4 = new Food(desc4);
	private Food food5 = new Food(desc5);
	

	@Before
	public void setup(){
		ArrayList<Food> foods = new ArrayList<Food>();
		foods.add(food1);
		foods.add(food2);
		foods.add(food3);
		foods.add(food4);
		foods.add(food5);
		tree = new PrefixTree(foods);
	}
	
	@Test
	public void testGetFood() {
		Food[] results1 = tree.getFood("pizza");
		Food[] results2 = tree.getFood("h");
		
		//test that the result of "pizza" has 2 items that are food1 and food2
		assertEquals(results1.length,2);
		assertEquals(results1[0],food1);
		assertEquals(results1[1],food2);
		
		//test that the result of "h" has 2 items that are food3 and food4
		assertEquals(results2.length,2);
		assertEquals(results2[0],food3);
		assertEquals(results2[1],food4);
	}
	
	@Test
	public void testInsertTag(){
		//insert a new tag and test that searching for that tag returns a result
		tree.insertTag(food1, "testTag");
		assertEquals(tree.getFood("testTag")[0],food1);
	}
	
	@Test
	public void testInsertFood(){
		//create a test food object and insert the food into the tree
		String[] newDesc = {"testTag1","testTag2"};
		Food newFood = new Food(newDesc);
		tree.insertFood(newFood);
		
		//check if searching for the test tags returns the new test food object
		assertEquals(tree.getFood("testTag1")[0],newFood);
		assertEquals(tree.getFood("testTag2")[0],newFood);

	}

}
