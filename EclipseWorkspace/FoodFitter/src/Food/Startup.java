package Food;
import java.util.ArrayList;
import Food.json.*;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;

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
    	ArrayList<Food> foodList = knap.mealsInRange(600, 600).get(1).getFoods();
    	Map m = new HashMap<String,ArrayList<Food>>();
    	m.put("foods", foodList);
    	//return nutrients
    	NutrientInfo.init_info();
    	System.out.println(NutrientInfo.get(652));
    	Map<String,Double> nutrientList = new HashMap<>();
    	for (Food f : foodList) {
    		ArrayList<Integer> nutrients = f.getNutrients();
    		for (int i : nutrients) {
    			double val = f.getNutr(i);
    			System.out.println(i);
    			String str = NutrientInfo.get(i).getName();
    			if (nutrientList.containsKey(str)) {
    				val += nutrientList.get(str);
    			}
    			nutrientList.put(str, val);
    		}
    	}
    	m.put("nutrients", nutrientList);
    	
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