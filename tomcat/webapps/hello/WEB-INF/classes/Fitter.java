
import java.util.ArrayList;

public class Fitter {
	private final static int search_radius = 10;

	/*
	 * Return meal with minimal squared error
	 */
	public static Meal fitNutrTar(Knapsack knap, NutrientTarget nutrTar) {
		int left_bound  = nutrTar.getCal()-search_radius;
		int right_bound = nutrTar.getCal()+search_radius;
		ArrayList<Meal> meals = knap.mealsInRange(left_bound,right_bound);
		return bestMealForTar(meals, nutrTar);
	}

	/*
	 * Return best meal that includes existing meal
	 */
	public static Meal fitNutrTarFromMeal(Knapsack knap, NutrientTarget nutrTar, Meal include) {
		// Find total calories in provided meal
		int cal_include = 0;
		for(Food food: include.getFoods()) {
			cal_include += food.getCal();
		}
		// Find meals matching remaining calories
		int left_bound  = nutrTar.getCal()-search_radius;
		int right_bound = nutrTar.getCal()+search_radius;
		ArrayList<Meal> meals = knap.mealsInRange(left_bound,right_bound);
		// Merge meals with existing meal
		for(int i = 0; i < meals.size(); i++) {
			Meal temp = meals.get(i);
			meals.set(i, temp.mergeMeal(include));
		}
		// Find optimal meal
		return bestMealForTar(meals, nutrTar);
	}
	/*
	 * Returns meal that best fits nutrient target from list of meals
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
