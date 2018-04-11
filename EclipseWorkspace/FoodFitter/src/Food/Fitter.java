package Food;

import java.util.ArrayList;

/**
 * Provides methods to compute best fitting meal given a knapsack and nutrient target.
 * Additionally supports best meal fitting off of a prexisting meal.
 * 
 * @author Wyatt Wismer
 *
 */

public class Fitter {
	/// Radius to search in
	private final static int search_radius = 100;
	/*
	 * Return meal with minimal squared error
	 */
	/**
	 * Get best fitting meal for Nutrient target
	 * @param knap Knapsack used to lookup meals
	 * @param nutrTar Nutrient Target to attempt to match
	 * @return Best fitting meal found in range
	 */
	public static Meal fitNutrTar(Knapsack knap, NutrientTarget nutrTar) {
		int left_bound  = nutrTar.getCal()-search_radius;
		int right_bound = nutrTar.getCal()+search_radius;
		ArrayList<Meal> meals = knap.mealsInRange(left_bound,right_bound,search_radius);
		return bestMealForTar(meals, nutrTar);
	}
	
	/**
	 * Get best fitting meal from existing meal
	 * @param knap Knapsack used to lookup meals
	 * @param nutrTar Nutrient Target to attempt to match
	 * @param include Prexisting meal to build off of
	 * @return Best fitting meal that includes prexisting meal
	 */
	public static Meal fitNutrTarFromMeal(Knapsack knap, NutrientTarget nutrTar, Meal include) {
		// Find total calories in provided meal
		int cal_include = 0;
		for(Food food: include.getFoods()) {
			cal_include += food.getCal();
		}
		// Find meals matching remaining calories
		int left_bound  = nutrTar.getCal()-cal_include-search_radius;
		int right_bound = nutrTar.getCal()-cal_include+search_radius;
		ArrayList<Meal> meals = knap.mealsInRange(left_bound,right_bound,search_radius);
		// Merge meals with existing meal
		for(int i = 0; i < meals.size(); i++) {
			Meal temp = meals.get(i);
			meals.set(i, temp.mergeMeal(include));
		}
		// Add old existing meal
		meals.add(include.copy());
		// Find optimal meal
		return bestMealForTar(meals, nutrTar);
	}

	/**
	 * Finds meal closest to Nutrient Target in selection
	 * @param meals Selection of meals to look in
	 * @param nutrTar Nutrient Target to attempt to match
	 * @return Closest meal to Nutrient target in selection
	 */
	private static Meal bestMealForTar(ArrayList<Meal> meals, NutrientTarget nutrTar) {
		Meal best = null;
		double min_err = 9999999999.0;
		double temp;
		for (Meal meal: meals) {
			temp = nutrTar.sqrdErr(meal);
			if (temp < min_err) {
				best = meal;
				min_err = temp;
			}
		}
		return best;
	}
	
}
