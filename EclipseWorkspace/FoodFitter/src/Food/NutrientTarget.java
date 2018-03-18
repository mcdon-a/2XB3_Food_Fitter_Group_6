package Food;

import java.util.ArrayList;
import java.util.HashMap;

public class NutrientTarget {
	private HashMap<Integer, Double> nutrient_amounts;
	public NutrientTarget() {
		nutrient_amounts = new HashMap<Integer, Double>();
	}
	public void addNutrient(int nutrID, double amt) {
		nutrient_amounts.put(nutrID, amt);
	}
	public void removeNutrient(int nutrID) {
		nutrient_amounts.remove(nutrID);
	}
	public double sqrdErr(Meal meal) {
		ArrayList<Food> foods = meal.getFoods();
		double error = 0.0;
		for (Food food : foods) {
			ArrayList<Integer> nutrients = food.getNutrients();
			for(Integer nutrID : nutrients) {
				error += Math.pow(food.getNutr(nutrID)-nutrient_amounts.get(nutrID), 2);
			}
		}
		return error;
	}
}
