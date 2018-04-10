package Food;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Contains information about goals for nutrient values as and ADT.
 * Provides an interface to support addition/deletion of nutrients,
 * access of stored goals, and squared error computation for an
 * arbitrary meal.
 * 
 * @author Wyatt Wismer
 *
 */

public class NutrientTarget {
	/// Hash map to store amount
	private HashMap<Integer, Double> nutrient_amounts;
	/// Number of calories for target
	private int calories;
	/// Number to normalize all amounts to
	private final double normalized_amt = 1.0;
	
	/**
	 * Construct a new Nutrient target from calorie amount
	 * @param cal Calorie amount for new Nutrient Target
	 */
	public NutrientTarget(int cal) {
		nutrient_amounts = new HashMap<Integer, Double>();
		calories = cal;
	}
	
	/**
	 * Add nutrient to target
	 * @param nutrID ID of nutrient to add
	 * @param amt Amount of nutrient to add
	 */
	public void addNutrient(int nutrID, double amt) {
		nutrient_amounts.put(nutrID, amt);
	}
	
	/**
	 * Get amount of nutrient with ID
	 * @param nutrID Nutrient ID to get amount of
	 * @return Amount of nutrient in target with ID
	 */
	public double getAmt(int nutrID) {
		return nutrient_amounts.get(nutrID);
	}
	
	/**
	 * Remove nutrient with ID
	 * @param nutrID ID of nutrient to remove
	 */
	public void removeNutrient(int nutrID) {
		nutrient_amounts.remove(nutrID);
	}
	
	/**
	 * Set number of calories in target
	 * @param cals New number of calories
	 */
	public void setCal(int cals) {
		calories = cals;
	}
	
	/**
	 * Get number of calories in target
	 * @return Number of calories in target
	 */
	public int getCal() {
		return calories;
	}
	
	/**
	 * Get collection of all IDs of nutrients in target
	 * @return ArrayList of all Nutrient IDs in target
	 */
	public ArrayList<Integer> getNutrients() {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.addAll(nutrient_amounts.keySet());
		return temp;
	}

	/**
	 * Find total squared normalized error between meal and target for calories and all nutrients
	 * @param meal Meal to find squared error against
	 * @return Total squared normalized error between meal and target for calories and all nutrients
	 */
	public double sqrdErr(Meal meal) {
		// Grab foods
		ArrayList<Food> foods = meal.getFoods();
		double error = 0.0;
		double normTarAmt;
		double normFoodAmt;
		ArrayList<Integer> tarNutrients = getNutrients();
		// Compute squared error of nutrients
		for (Integer nutrID : tarNutrients) {
			// compute aggregate nutrient amount
			double tempAmt = 0.0;
			for(Food food: foods) {
				tempAmt += food.getNutr(nutrID);
			}
			// compute error for nutrient
			normTarAmt = normalized_amt;
			normFoodAmt = (tempAmt/nutrient_amounts.get(nutrID))*normalized_amt;
			error += Math.pow(normTarAmt-normFoodAmt, 2);
		}
		
		// Compute squared error from calories
		for (Food food: foods) {
			// Compute squared error from calories
			normTarAmt = normalized_amt;
			normFoodAmt = food.getCal()/calories;
			error += Math.pow(calories-food.getCal(), 2);
		}

		return error;
	}
}
