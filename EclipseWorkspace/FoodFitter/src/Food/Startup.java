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
    	
    	
    }
}