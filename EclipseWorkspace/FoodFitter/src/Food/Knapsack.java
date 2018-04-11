package Food;

import java.util.ArrayList;

/**
 * Module to handle construction of, and interface with, Knapsack object.
 * After setup is complete supports calls in O(BUCKET_SIZE) time to get
 * all meals at a calorie level.
 * 
 * @author Wyatt Wismer
 *
 */

public class Knapsack {
	/// Holds meals at each calorie level
	private ArrayList<ArrayList<Meal>> buckets; 
	/// Number of meals in each bucket
	private int BUCKET_SIZE = 5; 
	/// Maximum allowed numer of calories
	private int MAX_CAL;
	/// Minimum number of calories to build buckets for
	private final int CALORIE_LOWER_BOUND = 3000;
	
	/**
	 * Computes value for max calories by finding max of food item calories and CALORIE_LOWER_BOUND
	 * @param foods Foods present in knapsack
	 * @return Maximum number of calories to build buckets for
	 */
	private int max_cal(ArrayList<Food> foods){
		// Max cal must be called with a selection of foods
		if (foods.size() == 0) throw new IllegalArgumentException();
		// Get calories of first food
		int mx = foods.get(0).getCal();
		// Update mx if num calories found
		for (Food x: foods)
			if (x.getCal() > mx)
				mx = x.getCal();
		// Final max is either max of foods, or the lower bound on calories
		return Math.max(mx, CALORIE_LOWER_BOUND);
	}
	
	/**
	 * Gets all valid meals in the inclusive range from l to r calories
	 * @param l Inclusive lower bound of range
	 * @param r Inclusive upper bound of range
	 * @param out_query range to query from when normal query is out of bounds
	 * @return Meals in range
	 */
	public ArrayList<Meal> mealsInRange(int l,int r,int out_query) {
		// Set up ArrayList to hold result
		ArrayList<Meal> res = new ArrayList<Meal>();
		// Iterate through range
		l = Math.max(0, l);
		r = Math.min(buckets.size()-1, r);
		// Out of bounds left -> give minimum query
		if (l == 0 && r <= l) {
			r = out_query;
		}
		// out of bounds right -> give maximal query
		if (r == buckets.size()-1 && l >= r) {
			l = r - out_query;
		}
		for (int i = l; i <= r; i++) {
			// Iterate through items in bucket
			for (int j = 0; j < buckets.get(i).size(); j++) {
				Meal meal = buckets.get(i).get(j);
				res.add(meal);
			}
		}
		return res;
	}
	
	/**
	 * Constructs a knapsack object from supplied foods
	 * @param foods Foods to be used in knapsack construction
	 */
	public Knapsack(ArrayList<Food> foods) {
		// find max number of calories for meal
		MAX_CAL = max_cal(foods);
		// set up buckets
		buckets = new ArrayList<ArrayList<Meal>>(MAX_CAL+1);
		for (int i = 0; i <= MAX_CAL; i++)
			buckets.add(new ArrayList<Meal>());
		// Add meals to buckets
		fill_buckets(foods);
	}
	
	/**
	 * Fill buckets with possible meals
	 * @param foods Foods to create meals from
	 */
	public void fill_buckets(ArrayList<Food> foods) {
		// Insert single foods
		for (Food food: foods) {
			// Break on calorie overflow
			if (food.getCal()>MAX_CAL) break;
			// Ignore item on full bucket
			if (buckets.get(food.getCal()).size() == BUCKET_SIZE) continue;
			// Add to bucket
			buckets.get(food.getCal()).add(new Meal(food));
		}
		// Fill buckets using knapsack approach
		for(int i = 1; i <= MAX_CAL; i++) {
			for(int j = 0; j < buckets.get(i).size(); j++) {
				// Get jth meal from current bucket
				Meal meal = buckets.get(i).get(j);
				// Iterate through foods
				for (Food food: foods) {
					int new_cal = i+food.getCal();
					// break on overflow
					if (new_cal>MAX_CAL) break;
					// Ignore item on full bucket
					if (buckets.get(new_cal).size() == BUCKET_SIZE)
						continue;
					// Create new meal
					Meal new_meal = meal.copy();
					new_meal.add(food);
					// Extend to higher buckets
					buckets.get(new_cal).add(new_meal);
				}
			}
		}
	}


}
