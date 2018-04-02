package Food;
import java.util.ArrayList;
import Food.json.*;
import java.util.Map;
import java.util.HashMap;

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
    	Map m = new HashMap<String,ArrayList<Food>>();
    	m.put("foods", knap.mealsInRange(600, 600).get(1).getFoods());
    	
    	//JSONObject json = new JSONObject(m);
    	//System.out.println(json.toString());
    	
    	// Build prefix tree
    	PrefixTree tree = new PrefixTree(foods);
    	
		// Example Query
		Food[] search = tree.getFood("chicken");   
		Food[] ordered = foods.toArray(new Food[foods.size()]);
		
		TimSort.sortMerge(ordered, 203);
		for(int i = 0; i < 10;i ++) {
			System.out.println(ordered[i] + "nutr val: " + ordered[i].getNutr(203));
			
		}
    }
}