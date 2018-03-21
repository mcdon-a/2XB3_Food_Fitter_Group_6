
import java.util.ArrayList;
import java.util.HashMap;

public class NutrientTarget {
	private HashMap<Integer, Double> nutrient_amounts;
	private int calories;
	private final double normalized_amt = 1.0;
	public NutrientTarget(int cal) {
		nutrient_amounts = new HashMap<Integer, Double>();
		calories = cal;
	}
	public void addNutrient(int nutrID, double amt) {
		nutrient_amounts.put(nutrID, amt);
	}
	public void removeNutrient(int nutrID) {
		nutrient_amounts.remove(nutrID);
	}
	public int getCal() {
		return calories;
	}
	public ArrayList<Integer> getNutrients() {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.addAll(nutrient_amounts.keySet());
		return temp;
	}
	/*
	 * Find total squared error between meal and target for calories and all nutrients
	 */
	public double sqrdErr(Meal meal) {
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
