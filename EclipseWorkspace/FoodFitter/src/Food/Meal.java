package Food;

import java.util.ArrayList;

public class Meal {
	private ArrayList<Food> foods;
	// Create empty meal
	public Meal() {
		foods = new ArrayList<Food>();
	}
	// Create meal from food
	public Meal(Food initial_food) {
		foods = new ArrayList<Food>();
		foods.add(initial_food);
	}
	// Create meal from ArrayList of foods
	public Meal(ArrayList<Food> initial_foods) {
		foods = new ArrayList<Food>();
		for (Food food: initial_foods)
			foods.add(food);
	}
	// Copy meal
	public Meal copy() {
		ArrayList<Food> tempFoods = new ArrayList<Food>();
		for (Food food: foods)
			tempFoods.add(food);
		return new Meal(tempFoods);
	}
	// Add food to meal
	public void add(Food food) {
		foods.add(food);
	}
	// Get food at index
	public Food get(int index) {
		return foods.get(index);
	}
	// Remove food at index
	public void remove(int index) {
		foods.remove(index);
	}
	// Merge additional meal into this meal
	public Meal mergeMeal(Meal meal) {
		Meal result = new Meal(foods);
		ArrayList<Food> new_foods = meal.getFoods();
		for(Food food: new_foods) {
			result.add(food);
		}
		return result;
	}
	// Get foods
	public ArrayList<Food> getFoods(){
		return foods;
	}
}
