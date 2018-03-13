package Food;
import java.util.ArrayList;

public class Startup {

    private static ArrayList<Food> foods;

    public static void getFoods() {
        foods = ReadCSV.readFile();
    }

    public static void main(String[] args) {
    	// Load in food
    	getFoods();
    	// Build knapsack
    	Knapsack knap = new Knapsack(foods);
    	// Example Query
    	System.out.println(knap.mealsInRange(600, 600).get(4).getFoods());
    	
    	//Create a prefix tree and load data into prefix tree
    	PrefixTree tree = new PrefixTree();
    	for(int i = 0; i < foods.size();i++) tree.insertFood(foods.get(i));
    	
    	
		//SEARCH FOR NODES BY ENTERING STRING INSIDE THE CALL TO getFood BELOW
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		Food[] foods = tree.getFood("HAMBURGERS");   
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		for(int i = 0; i < foods.length;i ++) {
			System.out.println(foods[i]);
		}
    	
    }
}