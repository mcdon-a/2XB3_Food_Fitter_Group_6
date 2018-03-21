

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Food class is an ADT that represents a food item by its id, descriptor, and nutritional values.
 * It accepts 3 arguments; the id (int), the descriptor (String of tags separated by commas),
 * and the nutritional values (
 *
 * Note that the inputed descriptor is converted to an array of its tags
 *
 * @author Thomas Armena
 *
 */

public class Food {

	private final String[] descriptor;			//stores an array of tags that describe the food
	private HashMap<Integer, Double> nutrVals;			//stores an array of nutritional values in respective order

	//constructor
	public Food(String[] descriptor) {
		this.descriptor = descriptor;
		nutrVals = new HashMap<>();
	}

	public String[] getDescriptor() {
		return this.descriptor;
	}

	public void addNutr(int nutrID, double val){
		nutrVals.put(nutrID,val);
	}

	public double getNutr(int nutrID){
		try {
			return nutrVals.get(nutrID);
		}catch (Exception e){
			return 0;
		}
	}

	public ArrayList<Integer> getNutrients() {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.addAll(nutrVals.keySet());
		return temp;
	}


	//String representation
	public String toString() {
		String descriptor = "";
		for(int i = 0; i < this.descriptor.length; i++) {
			descriptor += this.descriptor[i];
			if (i < this.descriptor.length-1)
				descriptor += ", ";
		}

		return "(" + descriptor + ")";
	}

	//Temporary main to test code
	public static void main(String[] args) {

		String[] descriptor = {"Fast foods", "sandwiches and burgers", "hamburger", "regular", "single patty", "plain"};
		Food food1 = new Food(descriptor);
		food1.addNutr(203, 19998);
		food1.addNutr(408, 8);

		//Testing the outputs
		System.out.println(food1);
		System.out.println(food1.getDescriptor()[1]);
		System.out.println(food1.getNutr(203));
		System.out.println(food1.getNutr(408));
	}

	public int getCal() {
		final int CALORIE_ID = 208;
		return (int)getNutr(CALORIE_ID);
	}

}
