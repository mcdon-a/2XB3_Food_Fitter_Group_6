package Food;

import java.util.ArrayList;

/**
 * Handles construction of and exported method for Meal ADT.
 * Effectively acts as wrapper for cleanly handling an ArrayList of Foods
 * and common methods that will need to be applied to such a selection.
 * 
 * @author Wyatt Wismer
 *
 */

public class Meal {
	/// Foods held by meal
	private ArrayList<Food> foods;
	
	/**
	 * Constructs a meal object with no foods
	 */
	public Meal() {
		foods = new ArrayList<Food>();
	}
	
	/**
	 * Creates a meal object from a food
	 * @param initial_food Food to start meal with
	 */
	public Meal(Food initial_food) {
		foods = new ArrayList<Food>();
		foods.add(initial_food);
	}
	
	/**
	 * Create meal object from a collection of foods
	 * @param initial_foods Foods to build meal with
	 */
	public Meal(ArrayList<Food> initial_foods) {
		foods = new ArrayList<Food>();
		for (Food food: initial_foods)
			foods.add(food);
	}
	
	/**
	 * Creates copy of current meal object
	 * @return Meal object that is a copy of current object
	 */
	public Meal copy() {
		ArrayList<Food> tempFoods = new ArrayList<Food>();
		for (Food food: foods)
			tempFoods.add(food);
		return new Meal(tempFoods);
	}
	
	/**
	 * Add food to meal
	 * @param food Food to be added to meal
	 */
	public void add(Food food) {
		foods.add(food);
	}
	
	/**
	 * Get food at supplied index in meal
	 * @param index Index to select food at
	 * @return Food at provided index
	 */
	public Food get(int index) {
		return foods.get(index);
	}
	
	/**
	 * Remove food at supplied index in meal
	 * @param index Index to remove food at
	 */
	public void remove(int index) {
		foods.remove(index);
	}

	/**
	 * Create Meal that is a combination of current and supplied meal
	 * @param meal Meal to merge with current meal
	 * @return New Meal that is a combination of current and supplied meal
	 */
	public Meal mergeMeal(Meal meal) {
		Meal result = new Meal(foods);
		ArrayList<Food> new_foods = meal.getFoods();
		for(Food food: new_foods) {
			result.add(food);
		}
		return result;
	}
	
	/**
	 * Get foods in meal
	 * @return All foods present in current meal
	 */
	public ArrayList<Food> getFoods(){
		return foods;
	}
}
